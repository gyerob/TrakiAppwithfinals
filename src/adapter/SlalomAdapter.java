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
import data.Slalom;
import datastorage.DbLoader;

public class SlalomAdapter extends CursorAdapter {
	Context context;

	public SlalomAdapter(Context context, Cursor c) {
		super(context, c, false);
		this.context = context;
	}

	@Override
	public Slalom getItem(int position) {
		getCursor().moveToPosition(position);
		return DbLoader.getSlalomByCursor(getCursor());
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView name = (TextView) view.findViewById(R.id.row_slalomName);
		TextView number = (TextView) view.findViewById(R.id.row_slalomNumber);
		TextView ido = (TextView) view.findViewById(R.id.row_slalomIdo);
		TextView hiba = (TextView) view.findViewById(R.id.row_slalomHiba);
		TextView vido = (TextView) view.findViewById(R.id.row_slalomVido);
		
		name.setTextColor(Color.BLACK);
		number.setTextColor(Color.BLACK);
		ido.setTextColor(Color.BLACK);
		hiba.setTextColor(Color.BLACK);
		vido.setTextColor(Color.BLACK);

		Slalom slalom = DbLoader.getSlalomByCursor(cursor);

		name.setText(slalom.getName());
		number.setText(Integer.toString(slalom.getNumber()));
		ido.setText(slalom.getIdo());
		hiba.setText(Integer.toString(slalom.getHiba()));
		vido.setText(slalom.getVido());
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		View row = inflater.inflate(R.layout.row_slalom, null);
		bindView(row, context, cursor);
		return row;
	}
}
