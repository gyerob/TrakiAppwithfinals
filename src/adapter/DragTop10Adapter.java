package adapter;

import hu.gyerob.trakiapp.R;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import data.Finals;
import datastorage.DbLoader;

public class DragTop10Adapter extends CursorAdapter{

	public DragTop10Adapter(Context context, Cursor c) {
		super(context, c, false);
	}
	
	@Override
	public Finals getItem(int position) {
		getCursor().moveToPosition(position);
		return DbLoader.getDragTop10ByCursor(getCursor());
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView pos = (TextView) view.findViewById(R.id.row_dragtopPos);
		TextView name = (TextView) view.findViewById(R.id.row_dragtopName);
		TextView number = (TextView) view.findViewById(R.id.row_dragtopNumber);
		
		pos.setTextColor(Color.BLACK);
		name.setTextColor(Color.BLACK);
		number.setTextColor(Color.BLACK);
		
		Finals f = DbLoader.getDragTop10ByCursor(cursor);
		
		pos.setText(Integer.toString(f.getWon()));
		name.setText(f.getName());
		number.setText(Integer.toString(f.getNumber()));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup arg2) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		View row = inflater.inflate(R.layout.row_top10_drag, null);
		bindView(row, context, cursor);
		return row;
	}

}
