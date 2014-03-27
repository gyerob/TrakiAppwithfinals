package hu.gyerob.trakiapp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jsonParser.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.GridViewAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryActivity extends Activity {

	// private static final int SELECT_PHOTO = 100;
	private static String url_upload_img = "http://gyerob.no-ip.biz/trakiweb/img_up.php";
	private static String url_get_all_image = "http://gyerob.no-ip.biz/trakiweb/get_all_image.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "images";

	private boolean webview = true;

	private JSONParser jsonParser = new JSONParser();;
	// Progress Dialog
	private ProgressDialog pDialog;

	private ImageView iv;
	private GridView gridView;
	private GridViewAdapter gridAdapter;
	private ArrayList<String> picturenames;
	private JSONArray images = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);

		/*
		 * webView = (WebView) findViewById(R.id.webView1); +
		 * webView.setWebViewClient(new WebViewClient()); +
		 * webView.getSettings().setJavaScriptEnabled(true); +
		 * webView.loadUrl("httP://gyerob.no-ip.biz/trakiweb/pics");
		 */

		picturenames = new ArrayList<String>();

		gridView = (GridView) findViewById(R.id.gridview);
		gridAdapter = new GridViewAdapter(GalleryActivity.this,
				R.layout.row_grid, picturenames);
		gridView.setAdapter(gridAdapter);

		iv = (ImageView) findViewById(R.id.galleryiv);
		iv.setImageDrawable(getResources().getDrawable(R.drawable.icon));

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Bitmap bm = ((GridViewAdapter) gridView.getAdapter())
						.getBitmap(position);
				if (bm != null)
					iv.setImageBitmap(bm);
			}
		});

		new GetImages().execute();
	}

	public class GetImages extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... param) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(url_get_all_image,
					"GET", params);

			picturenames = new ArrayList<String>();

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					images = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < images.length(); i++) {
						JSONObject c = images.getJSONObject(i);

						// Storing each json item in variable
						String nev = c.getString("nev");

						picturenames.add(nev);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			gridAdapter.clear();
			gridAdapter.addAll(picturenames);
			gridAdapter.notifyDataSetChanged();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				Uri imgUri = imageReturnedIntent.getData();

				new ImgUpload().execute(imgUri);
			}
		}
	}

	private class ImgUpload extends AsyncTask<Uri, Bitmap, Bitmap> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GalleryActivity.this);
			pDialog.setMessage("Kép feltöltése folyamatban...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Bitmap doInBackground(Uri... params) {
			Bitmap bm = null;
			for (Uri param : params) {
				try {
					bm = MediaStore.Images.Media.getBitmap(
							GalleryActivity.this.getContentResolver(), param);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();

					// int width = bm.getWidth();
					// int height = bm.getHeight();
					// double ratio = 400/width;
					// int newheight = (int)(ratio*height);

					// bm = Bitmap.createScaledBitmap(bm, 800, newheight, true);

					bm.compress(Bitmap.CompressFormat.JPEG, 95, baos);
					byte[] bytearray = baos.toByteArray();
					String img = Base64.encodeToString(bytearray, 0);

					List<NameValuePair> data = new ArrayList<NameValuePair>();
					data.add(new BasicNameValuePair("image", img));

					JSONObject json = jsonParser.makeHttpRequest(
							url_upload_img, "POSTIMG", data);

					// check log cat fro response
					Log.d("Create Response", json.toString());

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return bm;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			iv.setImageBitmap(result);
			// dismiss the dialog once done
			pDialog.dismiss();
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
			Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
			photoPickerIntent.setType("image/*");
			startActivityForResult(
					Intent.createChooser(photoPickerIntent, "SELECT PICTURE"),
					1);
		}
		if (item.getItemId() == R.id.switchgalleryview) {

		}
		return super.onOptionsItemSelected(item);
	}
}
