package adapter;

import hu.gyerob.trakiapp.R;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import data.Racer;
import datastorage.DbLoader;

public class RacerAdapter extends CursorAdapter {
	Context context;

	public RacerAdapter(Context context, Cursor c) {
		super(context, c, false);
		this.context = context;
	}

	@Override
	public Racer getItem(int position) {
		getCursor().moveToPosition(position);
		return DbLoader.getRacerByCursor(getCursor());
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView name = (TextView) view.findViewById(R.id.row_racerName);
		TextView number = (TextView) view.findViewById(R.id.row_racerNumber);
		CheckBox sex = (CheckBox) view.findViewById(R.id.row_racerSex);
		TextView town = (TextView) view.findViewById(R.id.row_racerTown);
		CheckBox trailer = (CheckBox) view.findViewById(R.id.row_racerTrailer);
		CheckBox slalom = (CheckBox) view.findViewById(R.id.row_racerSlalom);
		CheckBox drag = (CheckBox) view.findViewById(R.id.row_racerDrag);
		
		name.setTextColor(Color.BLACK);
		number.setTextColor(Color.BLACK);
		town.setTextColor(Color.BLACK);

		Racer racer = DbLoader.getRacerByCursor(cursor);

		name.setText(racer.getName());
		number.setText(Integer.toString(racer.getNumber()));
		sex.setChecked(racer.getSex());
		town.setText(racer.getTown());
		trailer.setChecked(racer.getTrailer());
		slalom.setChecked(racer.getSlalom());
		drag.setChecked(racer.getDrag());
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		View row = inflater.inflate(R.layout.row_racer, null);
		bindView(row, context, cursor);
		return row;
	}
}
