package finalsslalomfragments;

import hu.gyerob.trakiapp.R;
import data.Finals;
import datastorage.DbConstants;
import datastorage.DbLoader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import application.App;

public class SlalomTop10Fragment1 extends Fragment {
	public static final String TITLE = "Lépcsõk";
	
	private DbLoader dbLoader;

	private boolean veg;
	private boolean loading;

	private CheckBox chkr111;
	private CheckBox chkr112;
	private CheckBox chkr121;
	private CheckBox chkr122;
	private CheckBox chkr131;
	private CheckBox chkr132;
	private CheckBox chkr141;
	private CheckBox chkr142;
	private CheckBox chkr151;
	private CheckBox chkr152;
	private CheckBox chkr161;
	private CheckBox chkr162;
	private CheckBox chkr171;
	private CheckBox chkr172;
	private CheckBox chkr181;
	private CheckBox chkr182;

	private CheckBox chkr211;
	private CheckBox chkr212;
	private CheckBox chkr221;
	private CheckBox chkr222;
	private CheckBox chkr231;
	private CheckBox chkr232;
	private CheckBox chkr241;
	private CheckBox chkr242;

	private CheckBox chkr311;
	private CheckBox chkr312;
	private CheckBox chkr321;
	private CheckBox chkr322;
	private CheckBox chkr331;
	private CheckBox chkr332;
	private CheckBox chkr341;
	private CheckBox chkr342;

	private CheckBox chkr411;
	private CheckBox chkr412;
	private CheckBox chkr421;
	private CheckBox chkr422;
	private CheckBox chkr431;
	private CheckBox chkr432;
	private CheckBox chkr441;
	private CheckBox chkr442;

	private TextView txtr11;
	private TextView txtr12;
	private TextView txtr13;
	private TextView txtr14;
	private TextView txtr15;
	private TextView txtr16;
	private TextView txtr17;
	private TextView txtr18;

	private TextView txtr21;
	private TextView txtr22;
	private TextView txtr23;
	private TextView txtr24;

	private TextView txtr31;
	private TextView txtr32;
	private TextView txtr33;
	private TextView txtr34;

	private TextView txtr41;
	private TextView txtr42;
	private TextView txtr43;
	private TextView txtr44;

	private Cursor c;
	private Finals f;

	public void setVeg(boolean veg) {
		this.veg = veg;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbLoader = App.getDbLoader();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.finalsslalomscrollv, container, false);

		txtr11 = (TextView) v.findViewById(R.id.slalomround1Text1);
		txtr12 = (TextView) v.findViewById(R.id.slalomround1Text2);
		txtr13 = (TextView) v.findViewById(R.id.slalomround1Text3);
		txtr14 = (TextView) v.findViewById(R.id.slalomround1Text4);
		txtr15 = (TextView) v.findViewById(R.id.slalomround1Text5);
		txtr16 = (TextView) v.findViewById(R.id.slalomround1Text6);
		txtr17 = (TextView) v.findViewById(R.id.slalomround1Text7);
		txtr18 = (TextView) v.findViewById(R.id.slalomround1Text8);

		txtr21 = (TextView) v.findViewById(R.id.slalomround2Text1);
		txtr22 = (TextView) v.findViewById(R.id.slalomround2Text2);
		txtr23 = (TextView) v.findViewById(R.id.slalomround2Text3);
		txtr24 = (TextView) v.findViewById(R.id.slalomround2Text4);

		txtr31 = (TextView) v.findViewById(R.id.slalomround3Text1);
		txtr32 = (TextView) v.findViewById(R.id.slalomround3Text2);
		txtr33 = (TextView) v.findViewById(R.id.slalomround3Text3);
		txtr34 = (TextView) v.findViewById(R.id.slalomround3Text4);

		txtr41 = (TextView) v.findViewById(R.id.slalomround4Text1);
		txtr42 = (TextView) v.findViewById(R.id.slalomround4Text2);
		txtr43 = (TextView) v.findViewById(R.id.slalomround4Text3);
		txtr44 = (TextView) v.findViewById(R.id.slalomround4Text4);
		
		txtr11.setTextColor(Color.BLACK);
		txtr12.setTextColor(Color.BLACK);
		txtr13.setTextColor(Color.BLACK);
		txtr14.setTextColor(Color.BLACK);
		txtr15.setTextColor(Color.BLACK);
		txtr16.setTextColor(Color.BLACK);
		txtr17.setTextColor(Color.BLACK);
		txtr18.setTextColor(Color.BLACK);

		txtr21.setTextColor(Color.BLACK);
		txtr22.setTextColor(Color.BLACK);
		txtr23.setTextColor(Color.BLACK);
		txtr24.setTextColor(Color.BLACK);

		txtr31.setTextColor(Color.BLACK);
		txtr32.setTextColor(Color.BLACK);
		txtr33.setTextColor(Color.BLACK);
		txtr34.setTextColor(Color.BLACK);

		txtr41.setTextColor(Color.BLACK);
		txtr42.setTextColor(Color.BLACK);
		txtr43.setTextColor(Color.BLACK);
		txtr44.setTextColor(Color.BLACK);

		chkr111 = (CheckBox) v.findViewById(R.id.slalomround1chk11);
		chkr112 = (CheckBox) v.findViewById(R.id.slalomround1chk12);
		chkr121 = (CheckBox) v.findViewById(R.id.slalomround1chk21);
		chkr122 = (CheckBox) v.findViewById(R.id.slalomround1chk22);
		chkr131 = (CheckBox) v.findViewById(R.id.slalomround1chk31);
		chkr132 = (CheckBox) v.findViewById(R.id.slalomround1chk32);
		chkr141 = (CheckBox) v.findViewById(R.id.slalomround1chk41);
		chkr142 = (CheckBox) v.findViewById(R.id.slalomround1chk42);
		chkr151 = (CheckBox) v.findViewById(R.id.slalomround1chk51);
		chkr152 = (CheckBox) v.findViewById(R.id.slalomround1chk52);
		chkr161 = (CheckBox) v.findViewById(R.id.slalomround1chk61);
		chkr162 = (CheckBox) v.findViewById(R.id.slalomround1chk62);
		chkr171 = (CheckBox) v.findViewById(R.id.slalomround1chk71);
		chkr172 = (CheckBox) v.findViewById(R.id.slalomround1chk72);
		chkr181 = (CheckBox) v.findViewById(R.id.slalomround1chk81);
		chkr182 = (CheckBox) v.findViewById(R.id.slalomround1chk82);

		chkr211 = (CheckBox) v.findViewById(R.id.slalomround2chk11);
		chkr212 = (CheckBox) v.findViewById(R.id.slalomround2chk12);
		chkr221 = (CheckBox) v.findViewById(R.id.slalomround2chk21);
		chkr222 = (CheckBox) v.findViewById(R.id.slalomround2chk22);
		chkr231 = (CheckBox) v.findViewById(R.id.slalomround2chk31);
		chkr232 = (CheckBox) v.findViewById(R.id.slalomround2chk32);
		chkr241 = (CheckBox) v.findViewById(R.id.slalomround2chk41);
		chkr242 = (CheckBox) v.findViewById(R.id.slalomround2chk42);

		chkr311 = (CheckBox) v.findViewById(R.id.slalomround3chk11);
		chkr312 = (CheckBox) v.findViewById(R.id.slalomround3chk12);
		chkr321 = (CheckBox) v.findViewById(R.id.slalomround3chk21);
		chkr322 = (CheckBox) v.findViewById(R.id.slalomround3chk22);
		chkr331 = (CheckBox) v.findViewById(R.id.slalomround3chk31);
		chkr332 = (CheckBox) v.findViewById(R.id.slalomround3chk32);
		chkr341 = (CheckBox) v.findViewById(R.id.slalomround3chk41);
		chkr342 = (CheckBox) v.findViewById(R.id.slalomround3chk42);

		chkr411 = (CheckBox) v.findViewById(R.id.slalomround4chk11);
		chkr412 = (CheckBox) v.findViewById(R.id.slalomround4chk12);
		chkr421 = (CheckBox) v.findViewById(R.id.slalomround4chk21);
		chkr422 = (CheckBox) v.findViewById(R.id.slalomround4chk22);
		chkr431 = (CheckBox) v.findViewById(R.id.slalomround4chk31);
		chkr432 = (CheckBox) v.findViewById(R.id.slalomround4chk32);
		chkr441 = (CheckBox) v.findViewById(R.id.slalomround4chk41);
		chkr442 = (CheckBox) v.findViewById(R.id.slalomround4chk42);

		chkr112.setClickable(false);
		chkr122.setClickable(false);
		chkr132.setClickable(false);
		chkr142.setClickable(false);
		chkr152.setClickable(false);
		chkr162.setClickable(false);
		chkr172.setClickable(false);
		chkr182.setClickable(false);

		chkr212.setClickable(false);
		chkr222.setClickable(false);
		chkr232.setClickable(false);
		chkr242.setClickable(false);

		chkr312.setClickable(false);
		chkr322.setClickable(false);
		chkr332.setClickable(false);
		chkr342.setClickable(false);

		chkr412.setClickable(false);
		chkr422.setClickable(false);
		chkr432.setClickable(false);
		chkr442.setClickable(false);

		chkr111.setOnCheckedChangeListener(click);
		chkr112.setOnCheckedChangeListener(click);
		chkr121.setOnCheckedChangeListener(click);
		chkr122.setOnCheckedChangeListener(click);
		chkr131.setOnCheckedChangeListener(click);
		chkr132.setOnCheckedChangeListener(click);
		chkr141.setOnCheckedChangeListener(click);
		chkr142.setOnCheckedChangeListener(click);
		chkr151.setOnCheckedChangeListener(click);
		chkr152.setOnCheckedChangeListener(click);
		chkr161.setOnCheckedChangeListener(click);
		chkr162.setOnCheckedChangeListener(click);
		chkr171.setOnCheckedChangeListener(click);
		chkr172.setOnCheckedChangeListener(click);
		chkr181.setOnCheckedChangeListener(click);
		chkr182.setOnCheckedChangeListener(click);

		chkr211.setOnCheckedChangeListener(click);
		chkr212.setOnCheckedChangeListener(click);
		chkr221.setOnCheckedChangeListener(click);
		chkr222.setOnCheckedChangeListener(click);
		chkr231.setOnCheckedChangeListener(click);
		chkr232.setOnCheckedChangeListener(click);
		chkr241.setOnCheckedChangeListener(click);
		chkr242.setOnCheckedChangeListener(click);

		chkr311.setOnCheckedChangeListener(click);
		chkr312.setOnCheckedChangeListener(click);
		chkr321.setOnCheckedChangeListener(click);
		chkr322.setOnCheckedChangeListener(click);
		chkr331.setOnCheckedChangeListener(click);
		chkr332.setOnCheckedChangeListener(click);
		chkr341.setOnCheckedChangeListener(click);
		chkr342.setOnCheckedChangeListener(click);

		chkr411.setOnCheckedChangeListener(click);
		chkr412.setOnCheckedChangeListener(click);
		chkr421.setOnCheckedChangeListener(click);
		chkr422.setOnCheckedChangeListener(click);
		chkr431.setOnCheckedChangeListener(click);
		chkr432.setOnCheckedChangeListener(click);
		chkr441.setOnCheckedChangeListener(click);
		chkr442.setOnCheckedChangeListener(click);

		Cursor c;

		if (veg) {
			loading = true;
			if (App.isSlalomloaded()) {
				setNames();
			} else {
				c = dbLoader.fetchAllSlalom();
				int i = 0;
				while (c.moveToNext()) {
					i++;
				}
				if (i > 9) {
					loadStartData();
					App.setSlalomloaded(true);
				}
			}
			loading = false;
		}

		return v;
	}

	public void loadStartData() {
		Finals f;
		Cursor c;
		c = dbLoader.fetchAllSlalom();

		// 1.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound3(f, 2);
		txtr32.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 2.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound3(f, 3);
		txtr33.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 3.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound1(f, 6);
		txtr16.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 4.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound1(f, 3);
		txtr13.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 5.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound1(f, 7);
		txtr17.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 6.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound1(f, 2);
		txtr12.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 7.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound1(f, 1);
		txtr11.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 8.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound1(f, 8);
		txtr18.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 9.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound1(f, 4);
		txtr14.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));

		// 10.
		c.moveToNext();
		f = new Finals(
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)), 0);
		dbLoader.updateSlalomRound1(f, 5);
		txtr15.setText(c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Name)));
	}

	public void setNames() {
		Cursor c;
		Finals f;

		/*
		 * 
		 * 1. kör
		 */
		c = dbLoader.fetchAllSlalomTop10R1();

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr111.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr111.setChecked(true);
			chkr112.setChecked(true);
			chkr112.setClickable(true);
		}
		txtr11.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr121.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr121.setChecked(true);
			chkr122.setChecked(true);
			chkr122.setClickable(true);
		}
		txtr12.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr131.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr131.setChecked(true);
			chkr132.setChecked(true);
			chkr132.setClickable(true);
		}
		txtr13.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr141.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr141.setChecked(true);
			chkr142.setChecked(true);
			chkr142.setClickable(true);
		}
		txtr14.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr151.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr151.setChecked(true);
			chkr152.setChecked(true);
			chkr152.setClickable(true);
		}
		txtr15.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr161.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr161.setChecked(true);
			chkr162.setChecked(true);
			chkr162.setClickable(true);
		}
		txtr16.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr171.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr171.setChecked(true);
			chkr172.setChecked(true);
			chkr172.setClickable(true);
		}
		txtr17.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr181.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr181.setChecked(true);
			chkr182.setChecked(true);
			chkr182.setClickable(true);
		}
		txtr18.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));


		/*
		 * 
		 * 2. kör
		 */
		c = dbLoader.fetchAllSlalomTop10R2();

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr211.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr211.setChecked(true);
			chkr212.setChecked(true);
			chkr212.setClickable(true);
		}
		txtr21.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr221.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr221.setChecked(true);
			chkr222.setChecked(true);
			chkr222.setClickable(true);
		}
		txtr22.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr231.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr231.setChecked(true);
			chkr232.setChecked(true);
			chkr232.setClickable(true);
		}
		txtr23.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr241.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr241.setChecked(true);
			chkr242.setChecked(true);
			chkr242.setClickable(true);
		}
		txtr24.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		/*
		 * 
		 * 3. kör
		 */
		c = dbLoader.fetchAllSlalomTop10R3();

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr311.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr311.setChecked(true);
			chkr312.setChecked(true);
			chkr312.setClickable(true);
		}
		txtr31.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr321.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr321.setChecked(true);
			chkr322.setChecked(true);
			chkr322.setClickable(true);
		}
		txtr32.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr331.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr331.setChecked(true);
			chkr332.setChecked(true);
			chkr332.setClickable(true);
		}
		txtr33.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr341.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr341.setChecked(true);
			chkr342.setChecked(true);
			chkr342.setClickable(true);
		}
		txtr34.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		/*
		 * 
		 * 4. kör
		 */
		c = dbLoader.fetchAllSlalomTop10R4();

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr411.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr411.setChecked(true);
			chkr412.setChecked(true);
			chkr412.setClickable(true);
		}
		txtr41.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr421.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr421.setChecked(true);
			chkr422.setChecked(true);
			chkr422.setClickable(true);
		}
		txtr42.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr431.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr431.setChecked(true);
			chkr432.setChecked(true);
			chkr432.setClickable(true);
		}
		txtr43.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));

		c.moveToNext();
		f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.SlalomTop10.Key_Nyert)));
		if (f.getWon() == 1) {
			chkr441.setChecked(true);
		} else if (f.getWon() == 2) {
			chkr441.setChecked(true);
			chkr442.setChecked(true);
			chkr442.setClickable(true);
		}
		txtr44.setText(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)));
	}

	private OnCheckedChangeListener click = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton v, boolean isChecked) {
			int win1, win2;

			if (v.isChecked()) {
				win1 = 1;
				win2 = 2;
			} else {
				win1 = 0;
				win2 = 1;
			}
			if (!loading) {
				switch (v.getId()) {
				case R.id.slalomround1chk11: {
					c = dbLoader.fetchSlalomTop10(1, 1);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr112.setClickable(!chkr112.isClickable());
					dbLoader.updateSlalomRound1(f, 1);
					break;
				}
				case R.id.slalomround1chk21: {
					c = dbLoader.fetchSlalomTop10(1, 2);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr122.setClickable(!chkr122.isClickable());
					dbLoader.updateSlalomRound1(f, 2);
					break;
				}
				case R.id.slalomround1chk31: {
					c = dbLoader.fetchSlalomTop10(1, 3);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr132.setClickable(!chkr132.isClickable());
					dbLoader.updateSlalomRound1(f, 3);
					break;
				}
				case R.id.slalomround1chk41: {
					c = dbLoader.fetchSlalomTop10(1, 4);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr142.setClickable(!chkr142.isClickable());
					dbLoader.updateSlalomRound1(f, 4);
					break;
				}
				case R.id.slalomround1chk51: {
					c = dbLoader.fetchSlalomTop10(1, 5);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr152.setClickable(!chkr152.isClickable());
					dbLoader.updateSlalomRound1(f, 5);
					break;
				}
				case R.id.slalomround1chk61: {
					c = dbLoader.fetchSlalomTop10(1, 6);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr162.setClickable(!chkr162.isClickable());
					dbLoader.updateSlalomRound1(f, 6);
					break;
				}
				case R.id.slalomround1chk71: {
					c = dbLoader.fetchSlalomTop10(1, 7);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr172.setClickable(!chkr172.isClickable());
					dbLoader.updateSlalomRound1(f, 7);
					break;
				}
				case R.id.slalomround1chk81: {
					c = dbLoader.fetchSlalomTop10(1, 8);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr182.setClickable(!chkr182.isClickable());
					dbLoader.updateSlalomRound1(f, 8);
					break;
				}

				case R.id.slalomround1chk12: {
					c = dbLoader.fetchSlalomTop10(1, 1);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr21.setText(f.getName());
					dbLoader.updateSlalomRound1(f, 1);

					f.setWon(0);
					dbLoader.updateSlalomRound2(f, 1);
					break;
				}
				case R.id.slalomround1chk22: {
					c = dbLoader.fetchSlalomTop10(1, 2);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr21.setText(f.getName());
					dbLoader.updateSlalomRound1(f, 2);

					f.setWon(0);
					dbLoader.updateSlalomRound2(f, 1);
					break;
				}
				case R.id.slalomround1chk32: {
					c = dbLoader.fetchSlalomTop10(1, 3);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr22.setText(f.getName());
					dbLoader.updateSlalomRound1(f, 3);

					f.setWon(0);
					dbLoader.updateSlalomRound2(f, 2);
					break;
				}
				case R.id.slalomround1chk42: {
					c = dbLoader.fetchSlalomTop10(1, 4);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr22.setText(f.getName());
					dbLoader.updateSlalomRound1(f, 4);

					f.setWon(0);
					dbLoader.updateSlalomRound2(f, 2);
					break;
				}
				case R.id.slalomround1chk52: {
					c = dbLoader.fetchSlalomTop10(1, 5);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr23.setText(f.getName());
					dbLoader.updateSlalomRound1(f, 5);

					f.setWon(0);
					dbLoader.updateSlalomRound2(f, 3);
					break;
				}
				case R.id.slalomround1chk62: {
					c = dbLoader.fetchSlalomTop10(1, 6);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr23.setText(f.getName());
					dbLoader.updateSlalomRound1(f, 6);

					f.setWon(0);
					dbLoader.updateSlalomRound2(f, 3);
					break;
				}
				case R.id.slalomround1chk72: {
					c = dbLoader.fetchSlalomTop10(1, 7);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr24.setText(f.getName());
					dbLoader.updateSlalomRound1(f, 7);

					f.setWon(0);
					dbLoader.updateSlalomRound2(f, 4);
					break;
				}
				case R.id.slalomround1chk82: {
					c = dbLoader.fetchSlalomTop10(1, 8);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr24.setText(f.getName());
					dbLoader.updateSlalomRound1(f, 8);

					f.setWon(0);
					dbLoader.updateSlalomRound2(f, 4);
					break;
				}
				case R.id.slalomround2chk11: {
					c = dbLoader.fetchSlalomTop10(2, 1);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr212.setClickable(!chkr212.isClickable());
					dbLoader.updateSlalomRound2(f, 1);
					break;
				}
				case R.id.slalomround2chk21: {
					c = dbLoader.fetchSlalomTop10(2, 2);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr222.setClickable(!chkr222.isClickable());
					dbLoader.updateSlalomRound2(f, 2);
					break;
				}
				case R.id.slalomround2chk31: {
					c = dbLoader.fetchSlalomTop10(2, 3);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr232.setClickable(!chkr232.isClickable());
					dbLoader.updateSlalomRound2(f, 3);
					break;
				}
				case R.id.slalomround2chk41: {
					c = dbLoader.fetchSlalomTop10(2, 4);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);
					chkr242.setClickable(!chkr242.isClickable());
					dbLoader.updateSlalomRound2(f, 4);
					break;
				}

				case R.id.slalomround2chk12: {
					c = dbLoader.fetchSlalomTop10(2, 1);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					dbLoader.updateSlalomRound2(f, 1);

					f.setWon(0);
					dbLoader.updateSlalomRound3(f, 1);

					txtr31.setText(f.getName());
					break;
				}
				case R.id.slalomround2chk22: {
					c = dbLoader.fetchSlalomTop10(2, 2);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					dbLoader.updateSlalomRound2(f, 2);

					f.setWon(0);
					dbLoader.updateSlalomRound3(f, 1);

					txtr31.setText(f.getName());
					break;
				}
				case R.id.slalomround2chk32: {
					c = dbLoader.fetchSlalomTop10(2, 3);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					dbLoader.updateSlalomRound2(f, 3);

					f.setWon(0);
					dbLoader.updateSlalomRound3(f, 4);

					txtr34.setText(f.getName());
					break;
				}
				case R.id.slalomround2chk42: {
					c = dbLoader.fetchSlalomTop10(2, 4);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					dbLoader.updateSlalomRound2(f, 4);

					f.setWon(0);
					dbLoader.updateSlalomRound3(f, 4);

					txtr34.setText(f.getName());
					break;
				}
				case R.id.slalomround3chk11: {
					c = dbLoader.fetchSlalomTop10(3, 1);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr312.setClickable(!chkr312.isClickable());
					dbLoader.updateSlalomRound3(f, 1);
					break;
				}
				case R.id.slalomround3chk21: {
					c = dbLoader.fetchSlalomTop10(3, 2);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr322.setClickable(!chkr322.isClickable());
					dbLoader.updateSlalomRound3(f, 2);
					break;
				}
				case R.id.slalomround3chk31: {
					c = dbLoader.fetchSlalomTop10(3, 3);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr332.setClickable(!chkr332.isClickable());
					dbLoader.updateSlalomRound3(f, 3);
					break;
				}
				case R.id.slalomround3chk41: {
					c = dbLoader.fetchSlalomTop10(3, 4);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr342.setClickable(!chkr342.isClickable());
					dbLoader.updateSlalomRound3(f, 4);
					break;
				}
				case R.id.slalomround3chk12: {
					c = dbLoader.fetchSlalomTop10(3, 1);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr41.setText(f.getName());
					dbLoader.updateSlalomRound3(f, 1);

					f.setWon(0);
					dbLoader.updateSlalomRound4(f, 1);

					c = dbLoader.fetchSlalomTop10(3, 2);
					c.moveToNext();
					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							0);
					dbLoader.updateSlalomRound4(f, 3);
					txtr43.setText(txtr32.getText());
					break;
				}
				case R.id.slalomround3chk22: {
					c = dbLoader.fetchSlalomTop10(3, 2);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr41.setText(f.getName());
					dbLoader.updateSlalomRound3(f, 2);

					f.setWon(0);
					dbLoader.updateSlalomRound4(f, 1);

					c = dbLoader.fetchSlalomTop10(3, 1);
					c.moveToNext();
					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							0);
					dbLoader.updateSlalomRound4(f, 3);
					txtr43.setText(txtr31.getText());
					break;
				}
				case R.id.slalomround3chk32: {
					c = dbLoader.fetchSlalomTop10(3, 3);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr42.setText(f.getName());
					dbLoader.updateSlalomRound3(f, 3);

					f.setWon(0);
					dbLoader.updateSlalomRound4(f, 2);

					c = dbLoader.fetchSlalomTop10(3, 4);
					c.moveToNext();
					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							0);
					dbLoader.updateSlalomRound4(f, 4);
					txtr44.setText(txtr34.getText());
					break;
				}
				case R.id.slalomround3chk42: {
					c = dbLoader.fetchSlalomTop10(3, 4);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);
					txtr42.setText(f.getName());
					dbLoader.updateSlalomRound3(f, 4);

					f.setWon(0);
					dbLoader.updateSlalomRound4(f, 2);

					c = dbLoader.fetchSlalomTop10(3, 3);
					c.moveToNext();
					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							0);
					dbLoader.updateSlalomRound4(f, 4);
					txtr44.setText(txtr33.getText());
					break;
				}

				case R.id.slalomround4chk11: {
					c = dbLoader.fetchSlalomTop10(4, 1);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr412.setClickable(!chkr412.isClickable());
					dbLoader.updateSlalomRound4(f, 1);
					break;
				}
				case R.id.slalomround4chk21: {
					c = dbLoader.fetchSlalomTop10(4, 2);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr422.setClickable(!chkr422.isClickable());
					dbLoader.updateSlalomRound4(f, 2);
					break;
				}
				case R.id.slalomround4chk31: {
					c = dbLoader.fetchSlalomTop10(4, 3);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr432.setClickable(!chkr432.isClickable());
					dbLoader.updateSlalomRound4(f, 3);
					break;
				}
				case R.id.slalomround4chk41: {
					c = dbLoader.fetchSlalomTop10(4, 4);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win1);

					chkr442.setClickable(!chkr442.isClickable());
					dbLoader.updateSlalomRound4(f, 4);
					break;
				}
				case R.id.slalomround4chk12: {
					c = dbLoader.fetchSlalomTop10(4, 1);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);

					dbLoader.updateSlalomRound4(f, 1);
					break;
				}
				case R.id.slalomround4chk22: {
					c = dbLoader.fetchSlalomTop10(4, 2);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);

					dbLoader.updateSlalomRound4(f, 2);
					break;
				}
				case R.id.slalomround4chk32: {
					c = dbLoader.fetchSlalomTop10(4, 3);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);

					dbLoader.updateSlalomRound4(f, 3);
					break;
				}
				case R.id.slalomround4chk42: {
					c = dbLoader.fetchSlalomTop10(4, 4);
					c.moveToNext();

					f = new Finals(
							c.getString(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)),
							c.getInt(c
									.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
							win2);

					dbLoader.updateSlalomRound4(f, 4);
					break;
				}

				}
			}

		}
	};
}
