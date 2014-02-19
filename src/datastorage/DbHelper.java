package datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	String dbname;

	public DbHelper(Context context, String name) {
		super(context, name, null, DbConstants.Database_Version);
		dbname = name;
	}

	private String getDbName() {
		return dbname;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Cursor c = null;
		if (getDbName().equals(DbConstants.Database_Name)) {
			db.execSQL(DbConstants.Racer.Database_Create);
			db.execSQL(DbConstants.Trailer.Database_Create);
			db.execSQL(DbConstants.Slalom.Database_Create);
			db.execSQL(DbConstants.Drag.Database_Create);
			db.execSQL(DbConstants.Kepek.Database_Create);
		}

		if (getDbName().equals(DbConstants.Database_NameGy)) {
			db.execSQL(DbConstants.DragTop10.Database_Create1);
			db.execSQL(DbConstants.DragTop10.Database_Create2);
			db.execSQL(DbConstants.DragTop10.Database_Create3);
			db.execSQL(DbConstants.DragTop10.Database_Create4);
			db.execSQL(DbConstants.DragTop10.Database_Create5);

			c = db.rawQuery("SELECT COUNT(*) FROM "
					+ DbConstants.DragTop10.Database_Table1, null);
			c.moveToNext();
			if (Integer.parseInt(c.getString(c.getColumnIndex("COUNT(*)"))) == 0) {
				ContentValues values = new ContentValues();

				values.put(DbConstants.DragTop10.Key_Rajtszam, 0);
				values.put(DbConstants.DragTop10.Key_Nev, "Gyors");
				values.put(DbConstants.DragTop10.Key_Nyert, 0);
				for (int i = 0; i < 8; i++) {
					db.insert(DbConstants.DragTop10.Database_Table1, null,
							values);

					if (i % 2 == 0) {
						db.insert(DbConstants.DragTop10.Database_Table2, null,
								values);
						db.insert(DbConstants.DragTop10.Database_Table3, null,
								values);
						db.insert(DbConstants.DragTop10.Database_Table4, null,
								values);
					}
				}
				
				values.remove(DbConstants.DragTop10.Key_Nyert);
				for (int i = 0; i < 10; i++) {
					db.insert(DbConstants.DragTop10.Database_Table5, null,
							values);
				}
			}
		}

		if (getDbName().equals(DbConstants.Database_NameSz)) {
			db.execSQL(DbConstants.SlalomTop10.Database_Create1);
			db.execSQL(DbConstants.SlalomTop10.Database_Create2);
			db.execSQL(DbConstants.SlalomTop10.Database_Create3);
			db.execSQL(DbConstants.SlalomTop10.Database_Create4);
			db.execSQL(DbConstants.SlalomTop10.Database_Create5);

			c = db.rawQuery("SELECT COUNT(*) FROM "
					+ DbConstants.SlalomTop10.Database_Table1, null);
			c.moveToNext();
			if (Integer.parseInt(c.getString(c.getColumnIndex("COUNT(*)"))) == 0) {
				ContentValues values = new ContentValues();

				values.put(DbConstants.SlalomTop10.Key_Rajtszam, 0);
				values.put(DbConstants.SlalomTop10.Key_Nev, "Szlalom");
				values.put(DbConstants.SlalomTop10.Key_Nyert, 0);
				for (int i = 0; i < 8; i++) {
					db.insert(DbConstants.SlalomTop10.Database_Table1, null,
							values);

					if (i % 2 == 0) {
						db.insert(DbConstants.SlalomTop10.Database_Table2,
								null, values);
						db.insert(DbConstants.SlalomTop10.Database_Table3,
								null, values);
						db.insert(DbConstants.SlalomTop10.Database_Table4,
								null, values);
					}
				}

				values.remove(DbConstants.SlalomTop10.Key_Nyert);
				for (int i = 0; i < 10; i++) {
					db.insert(DbConstants.SlalomTop10.Database_Table5, null,
							values);
				}
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
