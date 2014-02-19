package application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import datastorage.DbLoader;

public class App extends Application {
	private static DbLoader dbLoader;

	private static String SERVER_IP = "gyerob.no-ip.biz";
	private static int SERVERPORT = 8080;
	private static boolean dragloaded = false;
	private static boolean slalomloaded = false;

	public static DbLoader getDbLoader() {
		return dbLoader;
	}

	public static int getPort() {
		return SERVERPORT;
	}

	public static String getIP() {
		return SERVER_IP;
	}

	public static void setServer(String ip, int port) {
		SERVER_IP = ip;
		SERVERPORT = port;

		Log.d("szervermódosítás:", SERVER_IP + " " + SERVERPORT);
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		setServer(prefs.getString("editTextIP", "gyerob.no-ip.biz"),
				Integer.parseInt(prefs.getString("editTextPort", "8080")));
		dbLoader = new DbLoader(this);
		dbLoader.open();
		dbLoader.loadImages();
	}

	@Override
	public void onTerminate() {
		// Close the internal db
		dbLoader.close();

		super.onTerminate();
	}

	public static boolean isSlalomloaded() {
		return slalomloaded;
	}

	public static void setSlalomloaded(boolean slalomloaded) {
		App.slalomloaded = slalomloaded;
	}

	public static boolean isDragloaded() {
		return dragloaded;
	}

	public static void setDragloaded(boolean dragloaded) {
		App.dragloaded = dragloaded;
	}
}
