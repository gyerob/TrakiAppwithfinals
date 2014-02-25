package finalsslalomfragments;

import hu.gyerob.trakiapp.R;

import java.util.ArrayList;
import java.util.List;

import jsonParser.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import data.SlalomTop;

public class SlalomTop10Fragment1 extends Fragment {
	public static final String TITLE = "Lépcsõk";

	private static String url_get_slalom_top = "http://gyerob.no-ip.biz/trakiweb/get_all_slalom_top.php";
	private static String url_update_slalom_top = "http://gyerob.no-ip.biz/trakiweb/update_slalom_top.php";

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "racers";

	// Progress Dialog
	private ProgressDialog pDialog;

	private JSONParser jsonParser = new JSONParser();

	private ArrayList<SlalomTop> slalomListr1;
	private ArrayList<SlalomTop> slalomListr2;
	private ArrayList<SlalomTop> slalomListr3;
	private ArrayList<SlalomTop> slalomListr4;
	private JSONArray racers = null;

	private CheckBox chkr111;
	private CheckBox chkr112;
	private CheckBox chkr121;
	private CheckBox chkr122;
	private CheckBox chkr131;
	private CheckBox chkr132;
	private CheckBox chkr141;
	private CheckBox chkr142;
	private CheckBox chkr151;
	private CheckBox chkr152;
	private CheckBox chkr161;
	private CheckBox chkr162;
	private CheckBox chkr171;
	private CheckBox chkr172;
	private CheckBox chkr181;
	private CheckBox chkr182;

	private CheckBox chkr211;
	private CheckBox chkr212;
	private CheckBox chkr221;
	private CheckBox chkr222;
	private CheckBox chkr231;
	private CheckBox chkr232;
	private CheckBox chkr241;
	private CheckBox chkr242;

	private CheckBox chkr311;
	private CheckBox chkr312;
	private CheckBox chkr321;
	private CheckBox chkr322;
	private CheckBox chkr331;
	private CheckBox chkr332;
	private CheckBox chkr341;
	private CheckBox chkr342;

	private CheckBox chkr411;
	private CheckBox chkr412;
	private CheckBox chkr421;
	private CheckBox chkr422;
	private CheckBox chkr431;
	private CheckBox chkr432;
	private CheckBox chkr441;
	private CheckBox chkr442;

	private TextView txtr11;
	private TextView txtr12;
	private TextView txtr13;
	private TextView txtr14;
	private TextView txtr15;
	private TextView txtr16;
	private TextView txtr17;
	private TextView txtr18;

	private TextView txtr21;
	private TextView txtr22;
	private TextView txtr23;
	private TextView txtr24;

	private TextView txtr31;
	private TextView txtr32;
	private TextView txtr33;
	private TextView txtr34;

	private TextView txtr41;
	private TextView txtr42;
	private TextView txtr43;
	private TextView txtr44;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.finalsslalomscrollv, container,
				false);

		txtr11 = (TextView) v.findViewById(R.id.slalomround1Text1);
		txtr12 = (TextView) v.findViewById(R.id.slalomround1Text2);
		txtr13 = (TextView) v.findViewById(R.id.slalomround1Text3);
		txtr14 = (TextView) v.findViewById(R.id.slalomround1Text4);
		txtr15 = (TextView) v.findViewById(R.id.slalomround1Text5);
		txtr16 = (TextView) v.findViewById(R.id.slalomround1Text6);
		txtr17 = (TextView) v.findViewById(R.id.slalomround1Text7);
		txtr18 = (TextView) v.findViewById(R.id.slalomround1Text8);

		txtr21 = (TextView) v.findViewById(R.id.slalomround2Text1);
		txtr22 = (TextView) v.findViewById(R.id.slalomround2Text2);
		txtr23 = (TextView) v.findViewById(R.id.slalomround2Text3);
		txtr24 = (TextView) v.findViewById(R.id.slalomround2Text4);

		txtr31 = (TextView) v.findViewById(R.id.slalomround3Text1);
		txtr32 = (TextView) v.findViewById(R.id.slalomround3Text2);
		txtr33 = (TextView) v.findViewById(R.id.slalomround3Text3);
		txtr34 = (TextView) v.findViewById(R.id.slalomround3Text4);

		txtr41 = (TextView) v.findViewById(R.id.slalomround4Text1);
		txtr42 = (TextView) v.findViewById(R.id.slalomround4Text2);
		txtr43 = (TextView) v.findViewById(R.id.slalomround4Text3);
		txtr44 = (TextView) v.findViewById(R.id.slalomround4Text4);

		txtr11.setTextColor(Color.BLACK);
		txtr12.setTextColor(Color.BLACK);
		txtr13.setTextColor(Color.BLACK);
		txtr14.setTextColor(Color.BLACK);
		txtr15.setTextColor(Color.BLACK);
		txtr16.setTextColor(Color.BLACK);
		txtr17.setTextColor(Color.BLACK);
		txtr18.setTextColor(Color.BLACK);

		txtr21.setTextColor(Color.BLACK);
		txtr22.setTextColor(Color.BLACK);
		txtr23.setTextColor(Color.BLACK);
		txtr24.setTextColor(Color.BLACK);

		txtr31.setTextColor(Color.BLACK);
		txtr32.setTextColor(Color.BLACK);
		txtr33.setTextColor(Color.BLACK);
		txtr34.setTextColor(Color.BLACK);

		txtr41.setTextColor(Color.BLACK);
		txtr42.setTextColor(Color.BLACK);
		txtr43.setTextColor(Color.BLACK);
		txtr44.setTextColor(Color.BLACK);

		chkr111 = (CheckBox) v.findViewById(R.id.slalomround1chk11);
		chkr112 = (CheckBox) v.findViewById(R.id.slalomround1chk12);
		chkr121 = (CheckBox) v.findViewById(R.id.slalomround1chk21);
		chkr122 = (CheckBox) v.findViewById(R.id.slalomround1chk22);
		chkr131 = (CheckBox) v.findViewById(R.id.slalomround1chk31);
		chkr132 = (CheckBox) v.findViewById(R.id.slalomround1chk32);
		chkr141 = (CheckBox) v.findViewById(R.id.slalomround1chk41);
		chkr142 = (CheckBox) v.findViewById(R.id.slalomround1chk42);
		chkr151 = (CheckBox) v.findViewById(R.id.slalomround1chk51);
		chkr152 = (CheckBox) v.findViewById(R.id.slalomround1chk52);
		chkr161 = (CheckBox) v.findViewById(R.id.slalomround1chk61);
		chkr162 = (CheckBox) v.findViewById(R.id.slalomround1chk62);
		chkr171 = (CheckBox) v.findViewById(R.id.slalomround1chk71);
		chkr172 = (CheckBox) v.findViewById(R.id.slalomround1chk72);
		chkr181 = (CheckBox) v.findViewById(R.id.slalomround1chk81);
		chkr182 = (CheckBox) v.findViewById(R.id.slalomround1chk82);

		chkr211 = (CheckBox) v.findViewById(R.id.slalomround2chk11);
		chkr212 = (CheckBox) v.findViewById(R.id.slalomround2chk12);
		chkr221 = (CheckBox) v.findViewById(R.id.slalomround2chk21);
		chkr222 = (CheckBox) v.findViewById(R.id.slalomround2chk22);
		chkr231 = (CheckBox) v.findViewById(R.id.slalomround2chk31);
		chkr232 = (CheckBox) v.findViewById(R.id.slalomround2chk32);
		chkr241 = (CheckBox) v.findViewById(R.id.slalomround2chk41);
		chkr242 = (CheckBox) v.findViewById(R.id.slalomround2chk42);

		chkr311 = (CheckBox) v.findViewById(R.id.slalomround3chk11);
		chkr312 = (CheckBox) v.findViewById(R.id.slalomround3chk12);
		chkr321 = (CheckBox) v.findViewById(R.id.slalomround3chk21);
		chkr322 = (CheckBox) v.findViewById(R.id.slalomround3chk22);
		chkr331 = (CheckBox) v.findViewById(R.id.slalomround3chk31);
		chkr332 = (CheckBox) v.findViewById(R.id.slalomround3chk32);
		chkr341 = (CheckBox) v.findViewById(R.id.slalomround3chk41);
		chkr342 = (CheckBox) v.findViewById(R.id.slalomround3chk42);

		chkr411 = (CheckBox) v.findViewById(R.id.slalomround4chk11);
		chkr412 = (CheckBox) v.findViewById(R.id.slalomround4chk12);
		chkr421 = (CheckBox) v.findViewById(R.id.slalomround4chk21);
		chkr422 = (CheckBox) v.findViewById(R.id.slalomround4chk22);
		chkr431 = (CheckBox) v.findViewById(R.id.slalomround4chk31);
		chkr432 = (CheckBox) v.findViewById(R.id.slalomround4chk32);
		chkr441 = (CheckBox) v.findViewById(R.id.slalomround4chk41);
		chkr442 = (CheckBox) v.findViewById(R.id.slalomround4chk42);

		chkr112.setClickable(false);
		chkr122.setClickable(false);
		chkr132.setClickable(false);
		chkr142.setClickable(false);
		chkr152.setClickable(false);
		chkr162.setClickable(false);
		chkr172.setClickable(false);
		chkr182.setClickable(false);

		chkr212.setClickable(false);
		chkr222.setClickable(false);
		chkr232.setClickable(false);
		chkr242.setClickable(false);

		chkr312.setClickable(false);
		chkr322.setClickable(false);
		chkr332.setClickable(false);
		chkr342.setClickable(false);

		chkr412.setClickable(false);
		chkr422.setClickable(false);
		chkr432.setClickable(false);
		chkr442.setClickable(false);

		chkr111.setOnCheckedChangeListener(click);
		chkr112.setOnCheckedChangeListener(click);
		chkr121.setOnCheckedChangeListener(click);
		chkr122.setOnCheckedChangeListener(click);
		chkr131.setOnCheckedChangeListener(click);
		chkr132.setOnCheckedChangeListener(click);
		chkr141.setOnCheckedChangeListener(click);
		chkr142.setOnCheckedChangeListener(click);
		chkr151.setOnCheckedChangeListener(click);
		chkr152.setOnCheckedChangeListener(click);
		chkr161.setOnCheckedChangeListener(click);
		chkr162.setOnCheckedChangeListener(click);
		chkr171.setOnCheckedChangeListener(click);
		chkr172.setOnCheckedChangeListener(click);
		chkr181.setOnCheckedChangeListener(click);
		chkr182.setOnCheckedChangeListener(click);

		chkr211.setOnCheckedChangeListener(click);
		chkr212.setOnCheckedChangeListener(click);
		chkr221.setOnCheckedChangeListener(click);
		chkr222.setOnCheckedChangeListener(click);
		chkr231.setOnCheckedChangeListener(click);
		chkr232.setOnCheckedChangeListener(click);
		chkr241.setOnCheckedChangeListener(click);
		chkr242.setOnCheckedChangeListener(click);

		chkr311.setOnCheckedChangeListener(click);
		chkr312.setOnCheckedChangeListener(click);
		chkr321.setOnCheckedChangeListener(click);
		chkr322.setOnCheckedChangeListener(click);
		chkr331.setOnCheckedChangeListener(click);
		chkr332.setOnCheckedChangeListener(click);
		chkr341.setOnCheckedChangeListener(click);
		chkr342.setOnCheckedChangeListener(click);

		chkr411.setOnCheckedChangeListener(click);
		chkr412.setOnCheckedChangeListener(click);
		chkr421.setOnCheckedChangeListener(click);
		chkr422.setOnCheckedChangeListener(click);
		chkr431.setOnCheckedChangeListener(click);
		chkr432.setOnCheckedChangeListener(click);
		chkr441.setOnCheckedChangeListener(click);
		chkr442.setOnCheckedChangeListener(click);

		slalomListr1 = new ArrayList<SlalomTop>();
		slalomListr2 = new ArrayList<SlalomTop>();
		slalomListr3 = new ArrayList<SlalomTop>();
		slalomListr4 = new ArrayList<SlalomTop>();
		new GetList().execute();

		return v;
	}

	private OnCheckedChangeListener click = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub

		}
	};

	class UpdateList extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	class GetList extends AsyncTask<String, String, String> {

		boolean failed = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(
					SlalomTop10Fragment1.this.getActivity());
			pDialog.setMessage("Versenyzõ frissítése..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... param) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(url_get_slalom_top,
					"GET", params);

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					racers = json.getJSONArray(TAG_PRODUCTS);

					Log.d("racers hossza:", Integer.toString(racers.length()));
					// looping through All Products
					for (int i = 0; i < racers.length(); i++) {
						JSONObject c = racers.getJSONObject(i);

						SlalomTop racer = new SlalomTop();

						// Storing each json item in variable
						racer.setNumber(c.getInt("rajt"));
						racer.setName(c.getString("nev"));
						racer.setWon(c.getInt("nyert"));

						if (i < 8)
							slalomListr1.add(racer);
						else if (i < 12)
							slalomListr2.add(racer);
						else if (i < 16)
							slalomListr3.add(racer);
						else if (i < 20)
							slalomListr4.add(racer);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				failed = true;
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();

			if (failed) {
				Toast.makeText(
						SlalomTop10Fragment1.this.getActivity(),
						"Sikertelen lekérés, ellenõrizd az internetkapcsolatot",
						Toast.LENGTH_LONG).show();
			} else {
				txtr11.setText(slalomListr1.get(4).getName());
				txtr12.setText(slalomListr1.get(3).getName());
				txtr13.setText(slalomListr1.get(1).getName());
				txtr14.setText(slalomListr1.get(6).getName());
				txtr15.setText(slalomListr1.get(7).getName());
				txtr16.setText(slalomListr1.get(0).getName());
				txtr17.setText(slalomListr1.get(2).getName());
				txtr18.setText(slalomListr1.get(5).getName());
				
				txtr21.setText(slalomListr2.get(0).getName());
				txtr22.setText(slalomListr2.get(1).getName());
				txtr23.setText(slalomListr2.get(2).getName());
				txtr24.setText(slalomListr2.get(3).getName());
				
				txtr31.setText(slalomListr3.get(0).getName());
				txtr32.setText(slalomListr3.get(1).getName());
				txtr33.setText(slalomListr3.get(2).getName());
				txtr34.setText(slalomListr3.get(3).getName());
				
				txtr41.setText(slalomListr4.get(0).getName());
				txtr42.setText(slalomListr4.get(1).getName());
				txtr43.setText(slalomListr4.get(2).getName());
				txtr44.setText(slalomListr4.get(3).getName());

				// adapter = new RacerAdapter(racerList);
				// setListAdapter(adapter);
			}
		}
	}
}
