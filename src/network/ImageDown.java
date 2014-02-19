package network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import application.App;
import datastorage.DbConstants;

public class ImageDown extends AsyncTask<Integer, Void, Bitmap> {

	private String SERVER_IP;
	private int SERVERPORT;
	private Socket socket;
	private File filesDir;
	private final WeakReference<ImageView> imageViewReference;

	public ImageDown(ImageView iv) {
		imageViewReference = new WeakReference<ImageView>(iv);
		SERVER_IP = App.getIP();
		SERVERPORT = App.getPort();
		filesDir = Environment.getExternalStorageDirectory();
		filesDir = new File(filesDir.getAbsolutePath() + File.separator
				+ "Traktorverseny");
		filesDir.mkdir();
	}

	@Override
	protected Bitmap doInBackground(Integer... params) {
		String fnev = null;
		InetAddress serverAddr = null;
		Cursor c = App.getDbLoader().getImageById(params[0]);
		c.moveToNext();
		if (c.getCount() < 1) {
			try {
				serverAddr = InetAddress.getByName(SERVER_IP);

				socket = new Socket(serverAddr, SERVERPORT);

				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);

				out.println("getimage:" + params[0]);

				ObjectInputStream ois = new ObjectInputStream(
						socket.getInputStream());
				String filedata = (String) ois.readObject();
				String[] fdata = filedata.split("\\|");
				Log.d("filedata", filedata + " " + fdata[1]);

				FileOutputStream outStream = new FileOutputStream(filesDir
						+ File.separator + fdata[1]);
				byte[] buffer = new byte[200000];
				int bytesRead = 0; int counter = 0;

				while (bytesRead >= 0) {
					bytesRead = ois.read(buffer);
					if (bytesRead >= 0) {
						outStream.write(buffer, 0, bytesRead);
					}
					if (bytesRead < 1024) {
						outStream.flush();
						break;
					}
					counter += bytesRead;
					System.out.println(counter);
				}

				String[] filenev = fdata[1].split("\\.");
				App.getDbLoader().addImage(filenev[0], filenev[1]);

				outStream.close();
				socket.close();

				fnev = fdata[1];
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			fnev = c.getString(c.getColumnIndex(DbConstants.Kepek.Key_Nev))
					+ "."
					+ c.getString(c.getColumnIndex(DbConstants.Kepek.Key_Ext));
		}
		
		final Bitmap bitmap = BitmapFactory.decodeFile(filesDir
				+ File.separator + fnev);
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if (isCancelled()) {
			bitmap = null;
		}

		if (imageViewReference != null) {
			ImageView imageView = imageViewReference.get();
			if (imageView != null) {

				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
				}
			}

		}
	}

}
