package finalsslalomfragments;

import hu.gyerob.trakiapp.R;
import adapter.SlalomTop10Adapter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import application.App;
import datastorage.DbLoader;

public class SlalomTop10Fragment2 extends ListFragment {
	public static final String TITLE = "Helyezések";
	
	private DbLoader dbLoader;
	private SlalomTop10Adapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbLoader = App.getDbLoader();
		
		Cursor c = dbLoader.fetchAllSlalomTop10R5();
		adapter = new SlalomTop10Adapter(getActivity().getApplicationContext(), c);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.finalsslalomlist, container, false);

		return v;
	}
}
