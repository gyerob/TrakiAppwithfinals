package inputfragments;

import hu.gyerob.trakiapp.R;
import network.PostDrag;
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
import application.App;
import data.Drag;
import datastorage.DbLoader;

public class DragFragment extends Fragment {
	public static final String TITLE = "Gyorsulás";

	private DbLoader dbLoader;

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
		dbLoader = App.getDbLoader();
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Drag d = new Drag();
			int rajt, mperc, mmp;

			if (number.getText().toString().equals(""))
				rajt = 0;
			else
				rajt = Integer.parseInt(number.getText().toString());

			if (mp.getText().toString().equals(""))
				mperc = 0;
			else
				mperc = Integer.parseInt(mp.getText().toString());

			if (ms.getText().toString().equals(""))
				mmp = 0;
			else
				mmp = Integer.parseInt(ms.getText().toString());

			d.setNumber(rajt);
			
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
			
			if (firstround.isChecked()) {
				d.setMP1(mperc);
				d.setMS1(mmp);
				d.setTort1(tort);
				d.setTort2(tort);
			} else {
				d.setMP2(mperc);
				d.setMS2(mmp);
				d.setTort1(tort);
				d.setTort2(tort);
			}
			
			d.setTimes();

			dbLoader.inputDrag(d,firstround.isChecked());

			PostDrag pd = new PostDrag(d.getNumber());
			pd.execute();
		}
	};
}
