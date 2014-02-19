package inputfragments;

import hu.gyerob.trakiapp.R;
import network.PostTrailer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import application.App;
import data.Trailer;
import datastorage.DbLoader;

public class TrailerFragment extends Fragment {
	public static final String TITLE = "Pótkocsis";

	private DbLoader dbLoader;

	private Button save;
	private EditText number;
	private EditText hiba;
	private EditText p;
	private EditText mp;
	private EditText ms;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.input_trailer, container, false);

		save = (Button) v.findViewById(R.id.inputtrailerbtn);

		number = (EditText) v.findViewById(R.id.inputtrailerEditNumber);
		p = (EditText) v.findViewById(R.id.inputtrailerEditP);
		mp = (EditText) v.findViewById(R.id.inputtrailerEditMP);
		ms = (EditText) v.findViewById(R.id.inputtrailerEditMS);
		hiba = (EditText) v.findViewById(R.id.inputtrailerEditError);

		save.setOnClickListener(click);

		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbLoader = App.getDbLoader();
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Trailer t = new Trailer();
			int rajt, perc, mperc, mmp, h;

			if (number.getText().toString().equals(""))
				rajt = 0;
			else
				rajt = Integer.parseInt(number.getText().toString());

			if (p.getText().toString().equals(""))
				perc = 0;
			else
				perc = Integer.parseInt(p.getText().toString());

			if (mp.getText().toString().equals(""))
				mperc = 0;
			else
				mperc = Integer.parseInt(mp.getText().toString());

			if (ms.getText().toString().equals(""))
				mmp = 0;
			else
				mmp = Integer.parseInt(ms.getText().toString());

			if (hiba.getText().toString().equals(""))
				h = 0;
			else
				h = Integer.parseInt(hiba.getText().toString());

			String msecond = ms.getText().toString();
			Log.d("ezredms", msecond + "hossza:" + msecond.length());
			String tort;
			if (msecond.length() == 2) {
				tort = "Század";
			}
			else if (msecond.length() == 3){
				tort = "Ezred";
			}
			else{
				tort = "Tized";
			}

			t.setNumber(rajt);
			t.setP(perc);
			t.setMP(mperc);
			t.setMS(mmp);
			t.setHiba(h);
			t.setTort(tort);

			t.setTimes();

			dbLoader.inputTrailer(t);

			PostTrailer pt = new PostTrailer(t.getNumber());
			pt.execute();
		}
	};
}
