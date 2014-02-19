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
import data.Drag;
import datastorage.DbLoader;

public class DragAdapter extends CursorAdapter {
	public DragAdapter(Context context, Cursor c) {
		super(context, c, false);
	}

	@Override
	public Drag getItem(int position) {
		getCursor().moveToPosition(position);
		return DbLoader.getDragByCursor(getCursor());
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView name = (TextView) view.findViewById(R.id.row_dragName);
		TextView number = (TextView) view.findViewById(R.id.row_dragNumber);
		TextView ido1 = (TextView) view.findViewById(R.id.row_dragIdo1);
		TextView ido2 = (TextView) view.findViewById(R.id.row_dragIdo2);
		TextView lido = (TextView) view.findViewById(R.id.row_dragLido);
		
		name.setTextColor(Color.BLACK);
		number.setTextColor(Color.BLACK);
		ido1.setTextColor(Color.BLACK);
		ido2.setTextColor(Color.BLACK);
		lido.setTextColor(Color.BLACK);

		Drag drag = DbLoader.getDragByCursor(cursor);

		name.setText(drag.getName());
		number.setText(Integer.toString(drag.getNumber()));
		ido1.setText(drag.getIdo1());
		ido2.setText(drag.getIdo2());
		lido.setText(drag.getLegjobbido());
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		View row = inflater.inflate(R.layout.row_drag, null);
		bindView(row, context, cursor);
		return row;
	}
}
