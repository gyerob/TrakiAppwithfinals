package inputfragments;

import hu.gyerob.trakiapp.R;
import network.DeleteDrag;
import network.DeleteRacer;
import network.DeleteSlalom;
import network.DeleteTrailer;
import network.PostRacer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import application.App;
import data.Drag;
import data.Racer;
import data.Slalom;
import data.Trailer;
import datastorage.DbLoader;

public class RacerFragment extends Fragment {
	public static final String TITLE = "Versenyzõk";

	private DbLoader dbLoader;

	private Button save;
	private EditText name;
	private EditText number;
	private EditText town;
	private CheckBox sex;
	private CheckBox trailer;
	private CheckBox slalom;
	private CheckBox drag;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.input_racer, container, false);

		save = (Button) v.findViewById(R.id.inputracerbtn);

		name = (EditText) v.findViewById(R.id.inputracerEditName);
		number = (EditText) v.findViewById(R.id.inputracerEditNumber);
		town = (EditText) v.findViewById(R.id.inputracerEditTown);
		sex = (CheckBox) v.findViewById(R.id.inputracerSex);
		trailer = (CheckBox) v.findViewById(R.id.inputracerTrailer);
		slalom = (CheckBox) v.findViewById(R.id.inputracerSlalom);
		drag = (CheckBox) v.findViewById(R.id.inputracerDrag);

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
			Racer r = new Racer();

			r.setName(name.getText().toString());
			int racernumber;
			if (number.getText().toString().equals("")) {
				racernumber = 0;
			} else
				racernumber = Integer.parseInt(number.getText().toString());
			r.setNumber(racernumber);
			r.setTown(town.getText().toString());
			r.setSex(sex.isChecked());
			r.setTrailer(trailer.isChecked());
			r.setSlalom(slalom.isChecked());
			r.setDrag(drag.isChecked());

			if (!trailer.isChecked() && !slalom.isChecked()
					&& !drag.isChecked()) {
				dbLoader.deleteRacer(r);
				DeleteRacer dr = new DeleteRacer(racernumber);
				dr.execute();
			} else {
				dbLoader.inputRacer(r);

				PostRacer pr = new PostRacer(racernumber);
				pr.execute();

				Trailer t = new Trailer(name.getText().toString(), racernumber,
						99, 99, 999, 99);
				Slalom s = new Slalom(name.getText().toString(), racernumber,
						99, 99, 999, 99);
				Drag d = new Drag(name.getText().toString(), racernumber, 99,
						99, 999, 999);

				if (trailer.isChecked()) {
					dbLoader.inputTrailer(t);

				} else {
					dbLoader.deleteTrailer(t);
					DeleteTrailer dt = new DeleteTrailer(racernumber);
					dt.execute();
				}

				if (slalom.isChecked()) {
					dbLoader.inputSlalom(s);
				} else {
					dbLoader.deleteSlalom(s);
					DeleteSlalom ds = new DeleteSlalom(racernumber);
					ds.execute();
				}

				if (drag.isChecked()) {
					dbLoader.inputDrag(d, true);
				} else {
					dbLoader.deleteDrag(d);
					DeleteDrag dd = new DeleteDrag(racernumber);
					dd.execute();
				}
			}
			name.setText("");
			number.setText("");
			town.setText("");
			
			sex.setChecked(false);
			trailer.setChecked(false);
			slalom.setChecked(false);
			drag.setChecked(false);
		}
	};
}
