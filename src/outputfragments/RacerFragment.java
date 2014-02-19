package outputfragments;

import hu.gyerob.trakiapp.R;
import adapter.RacerAdapter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import application.App;
import datastorage.DbLoader;

public class RacerFragment extends ListFragment {
	public static final String TITLE = "Versenyzõk";

	private DbLoader dbLoader;
	private RacerAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbLoader = App.getDbLoader();
		
		Cursor c = dbLoader.fetchAllRacer();
		adapter = new RacerAdapter(getActivity().getApplicationContext(), c);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.output_racer, container, false);
		return v;
	}
}
