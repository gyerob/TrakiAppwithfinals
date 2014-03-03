package application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

public class App extends Application {

	private static String SERVER_IP = "gyerob.no-ip.biz";
	private static int SERVERPORT = 80;
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
				Integer.parseInt(prefs.getString("editTextPort", "80")));
	}
}
