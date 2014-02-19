package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.util.Log;
import application.App;

import com.google.gson.Gson;

import data.Racer;
import datastorage.DbLoader;

public class GetRacer extends AsyncTask<Void, Void, Void> {

	private String SERVER_IP;
	private int SERVERPORT;
	private Socket socket;
	Gson gson;
	int id;

	public GetRacer(int id) {
		SERVER_IP = App.getIP();
		SERVERPORT = App.getPort();
		gson = new Gson();
		this.id = id;
	}

	@Override
	protected Void doInBackground(Void... params) {
		InetAddress serverAddr = null;
		DbLoader dbloader = App.getDbLoader();
		try {
			serverAddr = InetAddress.getByName(SERVER_IP);
			socket = new Socket(serverAddr, SERVERPORT);

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			
			out.println("getracerid:"+id);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "ISO-8859-1"));
			
			String json = in.readLine();
			Log.d("válasz", json);
			
			Racer r = gson.fromJson(json, Racer.class);
			dbloader.inputRacer(r);
			
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.onPostExecute(result);
	}
}
