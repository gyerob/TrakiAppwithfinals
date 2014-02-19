package hu.gyerob.trakiapp;

import gallery.ImageItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import network.ImageUp;
import adapter.GridViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import application.App;
import datastorage.DbConstants;
import datastorage.DbLoader;

public class GalleryActivity extends Activity {

	private GridView gridView;
	private GridViewAdapter customGridAdapter;
	private int numberofimages = 0;
	private ArrayList<Integer> ids;
	private static final int SELECT_PHOTO = 100;
	private ImageView iv;
	private File filesDir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		filesDir = Environment.getExternalStorageDirectory();
		filesDir = new File(filesDir.getAbsolutePath() + File.separator
				+ "Traktorverseny");

		iv = (ImageView) findViewById(R.id.galleryiv);
		gridView = (GridView) findViewById(R.id.gridview);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid,
				getData());
		gridView.setAdapter(customGridAdapter);

		ImageCounter ic = new ImageCounter();
		ic.execute();

		iv.setImageDrawable(getResources().getDrawable(R.drawable.icon));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Cursor c = App.getDbLoader().getImageById(position + 1);
				c.moveToNext();
				String fnev = c.getString(c
						.getColumnIndex(DbConstants.Kepek.Key_Nev))
						+ "."
						+ c.getString(c
								.getColumnIndex(DbConstants.Kepek.Key_Ext));

				Bitmap bitmap = BitmapFactory.decodeFile(filesDir
						+ File.separator + fnev);

				iv.setImageBitmap(bitmap);
			}
		});

	}

	private class ImageCounter extends AsyncTask<Void, Void, Void> {

		private String SERVER_IP;
		private int SERVERPORT;
		private Socket socket;

		public ImageCounter() {
			SERVER_IP = App.getIP();
			SERVERPORT = App.getPort();
		}

		@Override
		protected Void doInBackground(Void... params) {
			InetAddress serverAddr = null;

			try {
				serverAddr = InetAddress.getByName(SERVER_IP);

				socket = new Socket(serverAddr, SERVERPORT);
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);

				out.println("countimage");

				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String read = in.readLine();
				numberofimages = Integer.parseInt(read);
				Log.d("letoltendokepekszama", Integer.toString(numberofimages));
				String azon = in.readLine();
				ids = new ArrayList<Integer>();
				String[] azontomb = azon.split("\\,");
				for (int i = 0; i < numberofimages; i++) {
					ids.add(Integer.parseInt(azontomb[i]));
				}

				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			customGridAdapter.clear();
			customGridAdapter.addAll(getData());
			customGridAdapter.notifyDataSetChanged();
		}

	}

	private ArrayList<ImageItem> getData() {
		final ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>(0);

		if (ids != null) {
			int i = 0;
			Log.d("idshossz:", Integer.toString(ids.size()));
			while (i < ids.size()) {
				Log.d("kepidlistaállapot", Integer.toString(i));
				imageItems.add(new ImageItem(ids.get(i)));
				i++;
			}
		} else {
			DbLoader dbloader = App.getDbLoader();
			Cursor c = dbloader.getAllImage();

			if (c.getCount() > 0) {
				ids = new ArrayList<Integer>();
				while (c.moveToNext()) {
					int id = c.getInt(c.getColumnIndex(DbConstants.Key_ID));

					ids.add(id);
					imageItems.add(new ImageItem(id));
				}
			}
		}

		return imageItems;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		switch (requestCode) {
		case SELECT_PHOTO:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = imageReturnedIntent.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String filePath = cursor.getString(columnIndex);
				File f = new File(filePath);
				Log.d("választottképút:", filePath + " " + f.getName());
				cursor.close();

				FileInputStream fis;
				File tomoritett = null;
				try {
					fis = new FileInputStream(f);

					BitmapFactory.Options o = new BitmapFactory.Options();
					o.inJustDecodeBounds = true;
					BitmapFactory.decodeStream(fis, null, o);

					// The new size we want to scale to
					final int REQUIRED_SIZE = 800;

					// Find the correct scale value. It should be the power of
					// 2.
					int scale = 1;
					Log.d("skála",
							Integer.toString(o.outHeight)
									+ Integer.toString(o.outWidth));
					while (o.outWidth / scale / 2 >= REQUIRED_SIZE
							|| o.outHeight / scale / 2 >= REQUIRED_SIZE) {
						scale *= 2;
					}

					// Decode with inSampleSize
					BitmapFactory.Options o2 = new BitmapFactory.Options();
					o2.inSampleSize = 2;
					Bitmap bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
							o2);

					DbLoader dbloader = App.getDbLoader();
					Cursor c = dbloader.getAllImage();
					c.moveToLast();
					int lastrow = c
							.getInt(c.getColumnIndex(DbConstants.Key_ID)) + 1;
					tomoritett = new File(filesDir + Integer.toString(lastrow)
							+ ".jpg");
					FileOutputStream fos2 = new FileOutputStream(tomoritett);
					bm.compress(Bitmap.CompressFormat.JPEG, 100, fos2);
					ids.add(lastrow);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				filePath = tomoritett.getAbsolutePath();
				Log.d("feltöltendõ útja", filePath);

				ImageUp iu = new ImageUp();
				iu.execute(filePath);

				customGridAdapter.clear();
				customGridAdapter.addAll(getData());
				customGridAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gallery, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.itemImageUpload) {
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, SELECT_PHOTO);
		}
		return super.onOptionsItemSelected(item);
	}
}
