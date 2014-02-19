package network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import application.App;

public class DeleteSlalom extends AsyncTask<Void, Void, Void>{
	private String SERVER_IP;
	private int SERVERPORT;
	int id;
	private Socket socket;

	public DeleteSlalom(int id) {
		SERVER_IP = App.getIP();
		SERVERPORT = App.getPort();
		this.id = id;
	}

	@Override
	protected Void doInBackground(Void... params) {
		InetAddress serverAddr = null;
		try {
			serverAddr = InetAddress.getByName(SERVER_IP);

			socket = new Socket(serverAddr, SERVERPORT);

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);

			out.println("deleteslalomid:" + id);
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
