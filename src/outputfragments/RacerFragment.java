package outputfragments;

import hu.gyerob.trakiapp.R;

import java.util.ArrayList;
import java.util.List;

import jsonParser.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.RacerAdapter;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import data.Racer;

public class RacerFragment extends ListFragment {
	public static final String TITLE = "Versenyzők";

	private RacerAdapter adapter;
	ArrayList<Racer> racerList;

	public ProgressDialog pDialog;

	JSONParser jParser = new JSONParser();

	private static String url_all_racer = "http://gyerob.no-ip.biz/trakiweb/get_all_racer.php";

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "racers";

	JSONArray racers = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		racerList = new ArrayList<Racer>();

		new LoadAllRacer().execute();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.output_racer, container, false);
		return v;
	}

	class LoadAllRacer extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(RacerFragment.this.getActivity());
			pDialog.setMessage("Versenyzők betöltése, kérlek várj...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_racer, "GET",
					params);

			// Check your log cat for JSON reponse
			Log.d("All Racer: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					racers = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < racers.length(); i++) {
						JSONObject c = racers.getJSONObject(i);

						Racer racer = new Racer();

						// Storing each json item in variable
						racer.setNumber(Integer.parseInt(c.getString("rajt")));
						racer.setName(c.getString("nev"));
						racer.setTown(c.getString("varos"));
						racer.setSex(Boolean.parseBoolean(c.getString("nem")));
						racer.setTrailer((Boolean.parseBoolean(c
								.getString("potkocsi"))));
						racer.setSlalom(Boolean.parseBoolean(c
								.getString("szlalom")));
						racer.setDrag(Boolean.parseBoolean(c
								.getString("gyorsulas")));

						racerList.add(racer);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();
			
			adapter = new RacerAdapter(racerList);
			Log.d("listaméret:", Integer.toString(racerList.size()));
			setListAdapter(adapter);
		}
	}
}
