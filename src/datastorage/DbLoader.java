package datastorage;

import java.util.concurrent.ExecutionException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import data.Drag;
import data.Finals;
import data.Racer;
import data.Slalom;
import data.Trailer;

public class DbLoader {
	private Context context;
	private DbHelper dbHelper;
	public DbHelper dbHelperGy;
	private DbHelper dbHelperSz;
	private SQLiteDatabase Db;
	private SQLiteDatabase DbGy;
	private SQLiteDatabase DbSz;

	public DbLoader(Context context) {
		this.context = context;
	}

	public void open() throws SQLException {
		dbHelper = new DbHelper(context, DbConstants.Database_Name);
		dbHelperGy = new DbHelper(context, DbConstants.Database_NameGy);
		dbHelperSz = new DbHelper(context, DbConstants.Database_NameSz);

		Db = dbHelper.getWritableDatabase();
		DbGy = dbHelperGy.getWritableDatabase();
		DbSz = dbHelperSz.getWritableDatabase();

		dbHelper.onCreate(Db);
		dbHelperGy.onCreate(DbGy);
		dbHelperSz.onCreate(DbSz);
	}

	public void close() {
		dbHelper.close();
		dbHelperGy.close();
		dbHelperSz.close();
	}

	/**
	 * 
	 * Képek
	 * 
	 */

	public void loadImages() {

	}

	public class GetAllimage extends AsyncTask<Void, Void, Cursor> {
		@Override
		protected Cursor doInBackground(Void... params) {
			return Db.query(DbConstants.Kepek.Database_Table, new String[] {
					DbConstants.Key_ID, DbConstants.Kepek.Key_Nev,
					DbConstants.Kepek.Key_Ext }, null, null, null, null,
					DbConstants.Key_ID);
		}		
	}
	
	public Cursor getAllImage() {
		GetAllimage gai = new GetAllimage();
		gai.execute();
		try {
			Cursor c = gai.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Db.query(DbConstants.Kepek.Database_Table, new String[] {
				DbConstants.Key_ID, DbConstants.Kepek.Key_Nev,
				DbConstants.Kepek.Key_Ext }, null, null, null, null,
				DbConstants.Key_ID);
	}

	public long addImage(String name, String extension) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.Kepek.Key_Nev, name);
		values.put(DbConstants.Kepek.Key_Ext, extension);

		return Db.insert(DbConstants.Kepek.Database_Table, null, values);
	}

	public Cursor getImageById(int id) {
		return Db.query(DbConstants.Kepek.Database_Table, new String[] {
				DbConstants.Kepek.Key_Nev, DbConstants.Kepek.Key_Ext },
				DbConstants.Key_ID + "=" + id, null, null, null, null);
	}

	/**
	 * 
	 * Rajtszám alapján lekérés
	 * 
	 */

	public Cursor getRacer(int id) {
		return Db.query(DbConstants.Racer.Database_Table, new String[] {
				DbConstants.Racer.Key_Rajtszam, DbConstants.Racer.Key_Name,
				DbConstants.Racer.Key_Varos, DbConstants.Racer.Key_Nem,
				DbConstants.Racer.Key_Potkocsis, DbConstants.Racer.Key_Szlalom,
				DbConstants.Racer.Key_Gyorsulas },
				DbConstants.Racer.Key_Rajtszam + "=" + id, null, null, null,
				DbConstants.Racer.Key_Rajtszam);
	}

	public Cursor getTrailer(int id) {
		return Db.query(DbConstants.Trailer.Database_Table, new String[] {
				DbConstants.Trailer.Key_Rajtszam, DbConstants.Trailer.Key_Name,
				DbConstants.Trailer.Key_Ido, DbConstants.Trailer.Key_Hiba,
				DbConstants.Trailer.Key_VIdo },
				DbConstants.Trailer.Key_Rajtszam + "=" + id, null, null, null,
				DbConstants.Trailer.Key_Rajtszam);
	}

	public Cursor getSlalom(int id) {
		return Db.query(DbConstants.Slalom.Database_Table, new String[] {
				DbConstants.Slalom.Key_Rajtszam, DbConstants.Slalom.Key_Name,
				DbConstants.Slalom.Key_Ido, DbConstants.Slalom.Key_Hiba,
				DbConstants.Slalom.Key_VIdo }, DbConstants.Slalom.Key_Rajtszam
				+ "=" + id, null, null, null, DbConstants.Slalom.Key_Rajtszam);
	}

	public Cursor getDrag(int id) {
		return Db.query(DbConstants.Drag.Database_Table, new String[] {
				DbConstants.Drag.Key_Rajtszam, DbConstants.Drag.Key_Name,
				DbConstants.Drag.Key_Ido1, DbConstants.Drag.Key_Ido2,
				DbConstants.Drag.Key_LIdo }, DbConstants.Drag.Key_Rajtszam
				+ "=" + id, null, null, null, DbConstants.Drag.Key_Rajtszam);
	}

	/**
	 * összes lekérése
	 * 
	 * @return
	 */
	public Cursor fetchAllRacer() {
		// cursor minden rekordra (where = null)
		return Db.query(DbConstants.Racer.Database_Table,
				new String[] { DbConstants.Key_ID,
						DbConstants.Racer.Key_Rajtszam,
						DbConstants.Racer.Key_Name,
						DbConstants.Racer.Key_Varos, DbConstants.Racer.Key_Nem,
						DbConstants.Racer.Key_Potkocsis,
						DbConstants.Racer.Key_Szlalom,
						DbConstants.Racer.Key_Gyorsulas }, null, null, null,
				null, DbConstants.Racer.Key_Rajtszam);
	}

	public Cursor fetchAllTrailer() {
		// cursor minden rekordra (where = null)
		return Db.query(DbConstants.Trailer.Database_Table, new String[] {
				DbConstants.Key_ID, DbConstants.Trailer.Key_Rajtszam,
				DbConstants.Trailer.Key_Name, DbConstants.Trailer.Key_Ido,
				DbConstants.Trailer.Key_Hiba, DbConstants.Trailer.Key_VIdo },
				null, null, null, null, DbConstants.Trailer.Key_VIdo);
	}

	public Cursor fetchAllSlalom() {
		// cursor minden rekordra (where = null)
		return Db.query(DbConstants.Slalom.Database_Table, new String[] {
				DbConstants.Key_ID, DbConstants.Slalom.Key_Rajtszam,
				DbConstants.Slalom.Key_Name, DbConstants.Slalom.Key_Ido,
				DbConstants.Slalom.Key_Hiba, DbConstants.Slalom.Key_VIdo },
				null, null, null, null, DbConstants.Slalom.Key_VIdo);
	}

	public Cursor fetchAllDrag() {
		// cursor minden rekordra (where = null)
		return Db.query(DbConstants.Drag.Database_Table, new String[] {
				DbConstants.Key_ID, DbConstants.Drag.Key_Rajtszam,
				DbConstants.Drag.Key_Name, DbConstants.Drag.Key_Ido1,
				DbConstants.Drag.Key_Ido2, DbConstants.Drag.Key_LIdo }, null,
				null, null, null, DbConstants.Drag.Key_LIdo);
	}

	public Cursor fetchAllSlalomTop10R1() {
		return DbSz.query(DbConstants.SlalomTop10.Database_Table1,
				new String[] { DbConstants.Key_ID,
						DbConstants.SlalomTop10.Key_Rajtszam,
						DbConstants.SlalomTop10.Key_Nev,
						DbConstants.SlalomTop10.Key_Nyert }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllSlalomTop10R2() {
		return DbSz.query(DbConstants.SlalomTop10.Database_Table2,
				new String[] { DbConstants.Key_ID,
						DbConstants.SlalomTop10.Key_Rajtszam,
						DbConstants.SlalomTop10.Key_Nev,
						DbConstants.SlalomTop10.Key_Nyert }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllSlalomTop10R3() {
		return DbSz.query(DbConstants.SlalomTop10.Database_Table3,
				new String[] { DbConstants.Key_ID,
						DbConstants.SlalomTop10.Key_Rajtszam,
						DbConstants.SlalomTop10.Key_Nev,
						DbConstants.SlalomTop10.Key_Nyert }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllSlalomTop10R4() {
		return DbSz.query(DbConstants.SlalomTop10.Database_Table4,
				new String[] { DbConstants.Key_ID,
						DbConstants.SlalomTop10.Key_Rajtszam,
						DbConstants.SlalomTop10.Key_Nev,
						DbConstants.SlalomTop10.Key_Nyert }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllSlalomTop10R5() {
		return DbSz.query(DbConstants.SlalomTop10.Database_Table5,
				new String[] { DbConstants.Key_ID,
						DbConstants.SlalomTop10.Key_Rajtszam,
						DbConstants.SlalomTop10.Key_Nev }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllDragTop10R1() {
		return DbGy.query(DbConstants.DragTop10.Database_Table1,
				new String[] { DbConstants.Key_ID,
						DbConstants.DragTop10.Key_Rajtszam,
						DbConstants.DragTop10.Key_Nev,
						DbConstants.DragTop10.Key_Nyert }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllDragTop10R2() {
		return DbGy.query(DbConstants.DragTop10.Database_Table2,
				new String[] { DbConstants.Key_ID,
						DbConstants.DragTop10.Key_Rajtszam,
						DbConstants.DragTop10.Key_Nev,
						DbConstants.DragTop10.Key_Nyert }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllDragTop10R3() {
		return DbGy.query(DbConstants.DragTop10.Database_Table3,
				new String[] { DbConstants.Key_ID,
						DbConstants.DragTop10.Key_Rajtszam,
						DbConstants.DragTop10.Key_Nev,
						DbConstants.DragTop10.Key_Nyert }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllDragTop10R4() {
		return DbGy.query(DbConstants.DragTop10.Database_Table4,
				new String[] { DbConstants.Key_ID,
						DbConstants.DragTop10.Key_Rajtszam,
						DbConstants.DragTop10.Key_Nev,
						DbConstants.DragTop10.Key_Nyert }, null, null, null,
				null, DbConstants.Key_ID);
	}

	public Cursor fetchAllDragTop10R5() {
		return DbGy.query(DbConstants.DragTop10.Database_Table5, new String[] {
				DbConstants.Key_ID, DbConstants.DragTop10.Key_Rajtszam,
				DbConstants.DragTop10.Key_Nev }, null, null, null, null,
				DbConstants.Key_ID);
	}

	/**
	 * 
	 * Egy elem lekérése
	 * 
	 */
	public Cursor fetchDragTop10(int round, int pos) {
		String table = null;
		switch (round) {
		case 1:
			table = DbConstants.DragTop10.Database_Table1;
			break;
		case 2:
			table = DbConstants.DragTop10.Database_Table2;
			break;
		case 3:
			table = DbConstants.DragTop10.Database_Table3;
			break;
		case 4:
			table = DbConstants.DragTop10.Database_Table4;
			break;
		case 5:
			table = DbConstants.DragTop10.Database_Table5;
			break;
		}
		if (round == 5) {
			return DbGy.query(table, new String[] { DbConstants.Key_ID,
					DbConstants.DragTop10.Key_Rajtszam,
					DbConstants.DragTop10.Key_Nev }, DbConstants.Key_ID + "="
					+ pos, null, null, null, DbConstants.Key_ID);
		} else {
			return DbGy.query(table, new String[] { DbConstants.Key_ID,
					DbConstants.DragTop10.Key_Rajtszam,
					DbConstants.DragTop10.Key_Nev,
					DbConstants.DragTop10.Key_Nyert }, DbConstants.Key_ID + "="
					+ pos, null, null, null, DbConstants.Key_ID);
		}
	}

	public Cursor fetchSlalomTop10(int round, int pos) {
		String table = null;
		switch (round) {
		case 1:
			table = DbConstants.SlalomTop10.Database_Table1;
			break;
		case 2:
			table = DbConstants.SlalomTop10.Database_Table2;
			break;
		case 3:
			table = DbConstants.SlalomTop10.Database_Table3;
			break;
		case 4:
			table = DbConstants.SlalomTop10.Database_Table4;
			break;
		}

		return DbGy.query(table, new String[] { DbConstants.Key_ID,
				DbConstants.SlalomTop10.Key_Rajtszam,
				DbConstants.SlalomTop10.Key_Nev,
				DbConstants.SlalomTop10.Key_Nyert }, DbConstants.Key_ID + "="
				+ pos, null, null, null, DbConstants.Key_ID);
	}

	/**
	 * 
	 * lekérés kurzor alapján
	 * 
	 */
	public static Racer getRacerByCursor(Cursor c) {
		Racer racer = new Racer(c.getString(c
				.getColumnIndex(DbConstants.Racer.Key_Name)), c.getInt(c
				.getColumnIndex(DbConstants.Racer.Key_Rajtszam)), c.getString(c
				.getColumnIndex(DbConstants.Racer.Key_Varos)), c.getString(c
				.getColumnIndex(DbConstants.Racer.Key_Nem)), c.getString(c
				.getColumnIndex(DbConstants.Racer.Key_Potkocsis)),
				c.getString(c.getColumnIndex(DbConstants.Racer.Key_Szlalom)),
				c.getString(c.getColumnIndex(DbConstants.Racer.Key_Gyorsulas)));

		return racer;
	}

	public static Trailer getTrailerByCursor(Cursor c) {
		Trailer trailer = new Trailer(c.getString(c
				.getColumnIndex(DbConstants.Trailer.Key_Name)), c.getInt(c
				.getColumnIndex(DbConstants.Trailer.Key_Rajtszam)),
				c.getString(c.getColumnIndex(DbConstants.Trailer.Key_Ido)),
				c.getInt(c.getColumnIndex(DbConstants.Trailer.Key_Hiba)));
		return trailer;
	}

	public static Slalom getSlalomByCursor(Cursor c) {
		Slalom slalom = new Slalom(c.getString(c
				.getColumnIndex(DbConstants.Slalom.Key_Name)), c.getInt(c
				.getColumnIndex(DbConstants.Slalom.Key_Rajtszam)),
				c.getString(c.getColumnIndex(DbConstants.Slalom.Key_Ido)),
				c.getInt(c.getColumnIndex(DbConstants.Slalom.Key_Hiba)));

		return slalom;
	}

	public static Drag getDragByCursor(Cursor c) {
		Drag drag = new Drag(c.getString(c
				.getColumnIndex(DbConstants.Drag.Key_Name)), c.getInt(c
				.getColumnIndex(DbConstants.Drag.Key_Rajtszam)), c.getString(c
				.getColumnIndex(DbConstants.Drag.Key_Ido1)), c.getString(c
				.getColumnIndex(DbConstants.Drag.Key_Ido2)));

		return drag;
	}

	public static Finals getDragTop10ByCursor(Cursor c) {
		Finals f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.DragTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.DragTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.Key_ID)));
		return f;
	}

	public static Finals getSlalomTop10ByCursor(Cursor c) {
		Finals f = new Finals(c.getString(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Nev)), c.getInt(c
				.getColumnIndex(DbConstants.SlalomTop10.Key_Rajtszam)),
				c.getInt(c.getColumnIndex(DbConstants.Key_ID)));
		return f;
	}

	/**
	 * 
	 * Adatbevitel
	 * 
	 */
	public void inputRacer(Racer racer) {
		Cursor c = Db.rawQuery("SELECT COUNT(*) FROM "
				+ DbConstants.Racer.Database_Table + " WHERE "
				+ DbConstants.Racer.Key_Rajtszam + " == " + racer.getNumber(),
				null);
		c.moveToNext();
		int szamlalo = Integer.parseInt(c.getString(c
				.getColumnIndex("COUNT(*)")));
		if (szamlalo == 0) {
			createRacer(racer);
		} else {
			updateRacer(racer);
		}
	}

	public void inputTrailer(Trailer trailer) {
		Cursor c = Db.rawQuery(
				"SELECT COUNT(*) FROM " + DbConstants.Trailer.Database_Table
						+ " WHERE " + DbConstants.Trailer.Key_Rajtszam + " == "
						+ trailer.getNumber(), null);
		c.moveToNext();
		int szamlalo = Integer.parseInt(c.getString(c
				.getColumnIndex("COUNT(*)")));
		if (szamlalo == 0) {
			createTrailer(trailer);
		} else {
			updateTrailer(trailer);
		}
	}

	public void inputSlalom(Slalom slalom) {
		Cursor c = Db.rawQuery(
				"SELECT COUNT(*) FROM " + DbConstants.Slalom.Database_Table
						+ " WHERE " + DbConstants.Slalom.Key_Rajtszam + " == "
						+ slalom.getNumber(), null);
		c.moveToNext();
		int szamlalo = Integer.parseInt(c.getString(c
				.getColumnIndex("COUNT(*)")));
		if (szamlalo == 0) {
			createSlalom(slalom);
		} else {
			updateSlalom(slalom);
		}
	}

	public void inputDrag(Drag drag, boolean firstround) {
		Cursor c = Db.rawQuery("SELECT COUNT(*) FROM "
				+ DbConstants.Drag.Database_Table + " WHERE "
				+ DbConstants.Drag.Key_Rajtszam + " == " + drag.getNumber(),
				null);
		c.moveToNext();
		int szamlalo = Integer.parseInt(c.getString(c
				.getColumnIndex("COUNT(*)")));
		if (szamlalo == 0) {
			createDrag(drag);
		} else {
			updateDrag(drag, firstround);
		}
	}

	/**
	 * 
	 * Rekord létrehozása
	 * 
	 */
	public long createRacer(Racer racer) {
		ContentValues values = new ContentValues();
		String does;
		values.put(DbConstants.Racer.Key_Name, racer.getName());
		values.put(DbConstants.Racer.Key_Rajtszam, racer.getNumber());
		if (racer.getSex())
			does = "true";
		else
			does = "false";
		values.put(DbConstants.Racer.Key_Nem, does);
		values.put(DbConstants.Racer.Key_Varos, racer.getTown());

		if (racer.getTrailer())
			does = "true";
		else
			does = "false";
		values.put(DbConstants.Racer.Key_Potkocsis, does);
		if (racer.getSlalom())
			does = "true";
		else
			does = "false";
		values.put(DbConstants.Racer.Key_Szlalom, does);
		if (racer.getDrag())
			does = "true";
		else
			does = "false";
		values.put(DbConstants.Racer.Key_Gyorsulas, does);

		return Db.insert(DbConstants.Racer.Database_Table, null, values);
	}

	public long createDrag(Drag drag) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.Drag.Key_Name, drag.getName());
		values.put(DbConstants.Drag.Key_Rajtszam, drag.getNumber());
		values.put(DbConstants.Drag.Key_Ido1, drag.getIdo1());
		values.put(DbConstants.Drag.Key_Ido2, drag.getIdo2());
		values.put(DbConstants.Drag.Key_LIdo, drag.getLegjobbido());

		return Db.insert(DbConstants.Drag.Database_Table, null, values);
	}

	public long createSlalom(Slalom slalom) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.Slalom.Key_Name, slalom.getName());
		values.put(DbConstants.Slalom.Key_Rajtszam, slalom.getNumber());
		values.put(DbConstants.Slalom.Key_Ido, slalom.getIdo());
		values.put(DbConstants.Slalom.Key_Hiba, slalom.getHiba());
		values.put(DbConstants.Slalom.Key_VIdo, slalom.getVido());

		return Db.insert(DbConstants.Slalom.Database_Table, null, values);
	}

	public long createTrailer(Trailer trailer) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.Trailer.Key_Name, trailer.getName());
		values.put(DbConstants.Trailer.Key_Rajtszam, trailer.getNumber());
		values.put(DbConstants.Trailer.Key_Ido, trailer.getIdo());
		values.put(DbConstants.Trailer.Key_Hiba, trailer.getHiba());
		values.put(DbConstants.Trailer.Key_VIdo, trailer.getVido());

		return Db.insert(DbConstants.Trailer.Database_Table, null, values);
	}

	/**
	 * 
	 * Rekord frissítése
	 * 
	 */
	public boolean updateRacer(Racer racer) {
		ContentValues values = new ContentValues();
		String does;
		values.put(DbConstants.Racer.Key_Name, racer.getName());
		values.put(DbConstants.Racer.Key_Rajtszam, racer.getNumber());
		if (racer.getSex())
			does = "true";
		else
			does = "false";
		values.put(DbConstants.Racer.Key_Nem, does);
		values.put(DbConstants.Racer.Key_Varos, racer.getTown());

		if (racer.getTrailer())
			does = "true";
		else
			does = "false";
		values.put(DbConstants.Racer.Key_Potkocsis, does);
		if (racer.getSlalom())
			does = "true";
		else
			does = "false";
		values.put(DbConstants.Racer.Key_Szlalom, does);
		if (racer.getDrag())
			does = "true";
		else
			does = "false";
		values.put(DbConstants.Racer.Key_Gyorsulas, does);

		return Db.update(DbConstants.Racer.Database_Table, values,
				DbConstants.Racer.Key_Rajtszam + "=" + racer.getNumber(), null) > 0;
	}

	public boolean updateTrailer(Trailer trailer) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.Trailer.Key_Ido, trailer.getIdo());
		values.put(DbConstants.Trailer.Key_Hiba, trailer.getHiba());
		values.put(DbConstants.Trailer.Key_VIdo, trailer.getVido());

		return Db.update(DbConstants.Trailer.Database_Table, values,
				DbConstants.Trailer.Key_Rajtszam + "=" + trailer.getNumber(),
				null) > 0;
	}

	public boolean updateSlalom(Slalom slalom) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.Slalom.Key_Ido, slalom.getIdo());
		values.put(DbConstants.Slalom.Key_Hiba, slalom.getHiba());
		values.put(DbConstants.Slalom.Key_VIdo, slalom.getVido());

		return Db.update(DbConstants.Slalom.Database_Table, values,
				DbConstants.Slalom.Key_Rajtszam + "=" + slalom.getNumber(),
				null) > 0;
	}

	public boolean updateDrag(Drag drag, boolean firstround) {
		ContentValues values = new ContentValues();

		if (firstround) {
			values.put(DbConstants.Drag.Key_Ido1, drag.getIdo1());
		} else {
			values.put(DbConstants.Drag.Key_Ido2, drag.getIdo2());
		}

		values.put(DbConstants.Drag.Key_LIdo, drag.getLegjobbido());

		return Db.update(DbConstants.Drag.Database_Table, values,
				DbConstants.Drag.Key_Rajtszam + "=" + drag.getNumber(), null) > 0;
	}

	/**
	 * 
	 * Rekord törlése
	 * 
	 */
	public boolean deleteRacer(Racer racer) {
		return Db.delete(DbConstants.Racer.Database_Table,
				DbConstants.Racer.Key_Rajtszam + "=" + racer.getNumber(), null) > 0;
	}

	public boolean deleteTrailer(Trailer trailer) {
		return Db.delete(DbConstants.Trailer.Database_Table,
				DbConstants.Trailer.Key_Rajtszam + "=" + trailer.getNumber(),
				null) > 0;
	}

	public boolean deleteSlalom(Slalom slalom) {
		return Db.delete(DbConstants.Slalom.Database_Table,
				DbConstants.Slalom.Key_Rajtszam + "=" + slalom.getNumber(),
				null) > 0;
	}

	public boolean deleteDrag(Drag drag) {
		return Db.delete(DbConstants.Drag.Database_Table,
				DbConstants.Drag.Key_Rajtszam + "=" + drag.getNumber(), null) > 0;
	}

	/**
	 * 
	 * Döntõ rekord frissítés
	 * 
	 */

	public boolean updateDragRound1(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.DragTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.DragTop10.Key_Nev, f.getName());
		values.put(DbConstants.DragTop10.Key_Nyert, f.getWon());

		return DbGy.update(DbConstants.DragTop10.Database_Table1, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;

	}

	public boolean updateDragRound2(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.DragTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.DragTop10.Key_Nev, f.getName());
		values.put(DbConstants.DragTop10.Key_Nyert, f.getWon());

		return DbGy.update(DbConstants.DragTop10.Database_Table2, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}

	public boolean updateDragRound3(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.DragTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.DragTop10.Key_Nev, f.getName());
		values.put(DbConstants.DragTop10.Key_Nyert, f.getWon());

		return DbGy.update(DbConstants.DragTop10.Database_Table3, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}

	public boolean updateDragRound4(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.DragTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.DragTop10.Key_Nev, f.getName());
		values.put(DbConstants.DragTop10.Key_Nyert, f.getWon());

		return DbGy.update(DbConstants.DragTop10.Database_Table4, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}

	public boolean updateDragRound5(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.DragTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.DragTop10.Key_Nev, f.getName());

		return DbGy.update(DbConstants.DragTop10.Database_Table5, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}

	public boolean updateSlalomRound1(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.SlalomTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.SlalomTop10.Key_Nev, f.getName());
		values.put(DbConstants.SlalomTop10.Key_Nyert, f.getWon());

		return DbSz.update(DbConstants.SlalomTop10.Database_Table1, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}

	public boolean updateSlalomRound2(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.SlalomTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.SlalomTop10.Key_Nev, f.getName());
		values.put(DbConstants.SlalomTop10.Key_Nyert, f.getWon());

		return DbSz.update(DbConstants.SlalomTop10.Database_Table2, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}

	public boolean updateSlalomRound3(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.SlalomTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.SlalomTop10.Key_Nev, f.getName());
		values.put(DbConstants.SlalomTop10.Key_Nyert, f.getWon());

		return DbSz.update(DbConstants.SlalomTop10.Database_Table3, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}

	public boolean updateSlalomRound4(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.SlalomTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.SlalomTop10.Key_Nev, f.getName());
		values.put(DbConstants.SlalomTop10.Key_Nyert, f.getWon());

		return DbSz.update(DbConstants.SlalomTop10.Database_Table4, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}
	
	public boolean updateSlalomRound5(Finals f, int rowid) {
		ContentValues values = new ContentValues();

		values.put(DbConstants.SlalomTop10.Key_Rajtszam, f.getNumber());
		values.put(DbConstants.SlalomTop10.Key_Nev, f.getName());

		return DbSz.update(DbConstants.SlalomTop10.Database_Table5, values,
				DbConstants.Key_ID + "=" + rowid, null) > 0;
	}

	/**
	 * Döntõk frissítése
	 */
	public void updateFinalsDrag() {

	}

	public void updateFinalsSlalom() {

	}
}
