package finalsdragfragments;

import hu.gyerob.trakiapp.R;
import adapter.DragTop10Adapter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import application.App;
import datastorage.DbLoader;

public class DragTop10Fragment2 extends ListFragment {
	public static final String TITLE = "Helyezések";

	private DbLoader dbLoader;
	private DragTop10Adapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbLoader = App.getDbLoader();
		
		Cursor c = dbLoader.fetchAllDragTop10R5();
		adapter = new DragTop10Adapter(getActivity().getApplicationContext(), c);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.finalsdraglist, container, false);

		return v;
	}
}
