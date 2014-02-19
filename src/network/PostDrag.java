package network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import application.App;

import com.google.gson.Gson;

import data.Drag;
import datastorage.DbLoader;

public class PostDrag extends AsyncTask<Void, Void, Void> {

	private String SERVER_IP;
	private int SERVERPORT;
	private Socket socket;
	Gson gson;
	int id;

	public PostDrag(int id) {
		SERVER_IP = App.getIP();
		SERVERPORT = App.getPort();
		gson = new Gson();
		this.id = id;
	}

	@Override
	protected Void doInBackground(Void... params) {
		InetAddress serverAddr = null;

		DbLoader dbloader = App.getDbLoader();

		Cursor c = dbloader.getDrag(id);
		c.moveToNext();

		Drag drag = DbLoader.getDragByCursor(c);

		String json = gson.toJson(drag);
		Log.d("json", json);

		try {
			serverAddr = InetAddress.getByName(SERVER_IP);

			socket = new Socket(serverAddr, SERVERPORT);

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);

			out.println("senddrag");
			out.println(json);

			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
