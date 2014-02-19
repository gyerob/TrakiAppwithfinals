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
import data.Trailer;
import datastorage.DbLoader;

public class TrailerAdapter extends CursorAdapter {
	Context context;

	public TrailerAdapter(Context context, Cursor c) {
		super(context, c, false);
		this.context = context;
	}

	@Override
	public Trailer getItem(int position) {
		getCursor().moveToPosition(position);
		return DbLoader.getTrailerByCursor(getCursor());
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView name = (TextView) view.findViewById(R.id.row_trailerName);
		TextView number = (TextView) view.findViewById(R.id.row_trailerNumber);
		TextView ido = (TextView) view.findViewById(R.id.row_trailerIdo);
		TextView hiba = (TextView) view.findViewById(R.id.row_trailerHiba);
		TextView vido = (TextView) view.findViewById(R.id.row_trailerVido);
		
		name.setTextColor(Color.BLACK);
		number.setTextColor(Color.BLACK);
		ido.setTextColor(Color.BLACK);
		hiba.setTextColor(Color.BLACK);
		vido.setTextColor(Color.BLACK);
		
		Trailer trailer = DbLoader.getTrailerByCursor(cursor);

		name.setText(trailer.getName());
		number.setText(Integer.toString(trailer.getNumber()));
		ido.setText(trailer.getIdo());
		hiba.setText(Integer.toString(trailer.getHiba()));
		vido.setText(trailer.getVido());
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		View row = inflater.inflate(R.layout.row_trailer, null);
		bindView(row, context, cursor);
		return row;
	}	
}
