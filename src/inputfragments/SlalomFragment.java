package inputfragments;

import network.PostSlalom;
import hu.gyerob.trakiapp.R;
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
import data.Slalom;
import datastorage.DbLoader;

public class SlalomFragment extends Fragment {
	public static final String TITLE = "Szlalom";

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
		View v = inflater.inflate(R.layout.input_slalom, container, false);

		save = (Button) v.findViewById(R.id.inputslalombtn);

		number = (EditText) v.findViewById(R.id.inputslalomEditNumber);
		p = (EditText) v.findViewById(R.id.inputslalomEditP);
		mp = (EditText) v.findViewById(R.id.inputslalomEditMP);
		ms = (EditText) v.findViewById(R.id.inputslalomEditMS);
		hiba = (EditText) v.findViewById(R.id.inputslalomEditError);

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
			Slalom s = new Slalom();
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
			
			s.setNumber(rajt);
			s.setP(perc);
			s.setMP(mperc);
			s.setMS(mmp);
			s.setHiba(h);
			s.setTort(tort);
			
			s.setTimes();

			dbLoader.inputSlalom(s);
			dbLoader.updateFinalsSlalom();

			PostSlalom ps = new PostSlalom(s.getNumber());
			ps.execute();
		}
	};
}
