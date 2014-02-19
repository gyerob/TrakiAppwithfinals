package inputfragments;

import hu.gyerob.trakiapp.R;

import java.util.ArrayList;
import java.util.List;

import jsonParser.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DragFragment extends Fragment {
	public static final String TITLE = "Gyorsulás";

	// url to create new product
	private static String url_update_drag = "http://192.168.0.101/trakiweb/update_drag.php";

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	private Button save;
	private EditText number;
	private EditText mp;
	private EditText ms;
	private CheckBox firstround;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.input_drag, container, false);

		save = (Button) v.findViewById(R.id.inputdragbtn);

		number = (EditText) v.findViewById(R.id.inputdragEditNumber);
		mp = (EditText) v.findViewById(R.id.inputdragEditMP);
		ms = (EditText) v.findViewById(R.id.inputdragEditMS);
		firstround = (CheckBox) v.findViewById(R.id.inputdragCheckBox);

		save.setOnClickListener(click);
		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {

			new UpdateDrag().execute();
		}
	};

	class UpdateDrag extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DragFragment.this.getActivity());
			pDialog.setMessage("Versenyzõ eltárolása..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String rajt = number.getText().toString();
			String time = "harr";

			int mperc, kor;
			String mmp;
			
			if(firstround.isChecked()) kor = 1;
			else kor = 2;
			
			mperc = Integer.parseInt(mp.getText().toString());
			mmp = ms.getText().toString();

			if (mperc < 10)
				time = "0" + mperc + ":" + mmp;
			else
				time = mperc + ":" + mmp;
			Log.d("ido", time);

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("rajt", rajt));
			params.add(new BasicNameValuePair("ido", time));
			params.add(new BasicNameValuePair("round", Integer.toString(kor)));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_update_drag,
					"POST", params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}
	}
}
