package network;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.util.Log;
import application.App;

public class ImageUp extends AsyncTask<String, Void, Void> {

	private String SERVER_IP;
	private int SERVERPORT;
	private Socket socket;

	public ImageUp() {
		SERVER_IP = App.getIP();
		SERVERPORT = App.getPort();
	}

	@Override
	protected Void doInBackground(String... params) {
		InetAddress serverAddr = null;

		try {
			serverAddr = InetAddress.getByName(SERVER_IP);

			socket = new Socket(serverAddr, SERVERPORT);

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);

			out.println("postimage");

			File img = new File(params[0]);
			FileInputStream fis = new FileInputStream(img);
			Log.d("küldendõ", img.getAbsolutePath() + " neve " + img.getName()
					+ " mérete " + img.length());		

			BufferedOutputStream dos = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, bytesRead);
			}
			dos.flush();
			fis.close();
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
}
