package hu.gyerob.trakiapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import application.App;

public class PreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

	@Override
	protected void onDestroy() {
		updateServer();
		super.onDestroy();
	}

	public void updateServer() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		
		String ip = prefs.getString("editTextIP", "gyerob.no-ip.biz");
		int port = Integer.parseInt(prefs.getString("editTextPort", "8080"));
		
		App.setServer(ip, port);
	}
}
