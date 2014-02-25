package adapter;

import hu.gyerob.trakiapp.R;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import data.Finals;
import datastorage.DbLoader;

public class SlalomTop10Adapter extends CursorAdapter {

	public SlalomTop10Adapter(Context context, Cursor c) {
		super(context, c, false);
	}

	@Override
	public Finals getItem(int position) {
		getCursor().moveToPosition(position);
		return DbLoader.getSlalomTop10ByCursor(getCursor());
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView pos = (TextView) view.findViewById(R.id.row_slalomtopPos);
		TextView name = (TextView) view.findViewById(R.id.row_slalomtopName);
		TextView number = (TextView) view.findViewById(R.id.row_slalomtopNumber);
		
		pos.setTextColor(Color.BLACK);
		name.setTextColor(Color.BLACK);
		number.setTextColor(Color.BLACK);
		
		Finals f = DbLoader.getSlalomTop10ByCursor(cursor);
		pos.setText(Integer.toString(f.getWon()));
		name.setText(f.getName());
		number.setText(Integer.toString(f.getNumber()));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup arg2) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		View row = inflater.inflate(R.layout.row_top10_slalom, null);
		bindView(row, context, cursor);
		return row;
	}

}
