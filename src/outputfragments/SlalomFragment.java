package outputfragments;

import hu.gyerob.trakiapp.R;
import adapter.SlalomAdapter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import application.App;
import datastorage.DbLoader;

public class SlalomFragment extends ListFragment{
	public static final String TITLE = "Szlalom";
	
	private DbLoader dbLoader;
	private SlalomAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbLoader = App.getDbLoader();
		
		Cursor c = dbLoader.fetchAllSlalom();
		adapter = new SlalomAdapter(getActivity().getApplicationContext(), c);
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.output_slalom, container, false);
		return v;
	}
}
