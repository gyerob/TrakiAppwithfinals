package datastorage;

public class DbConstants {
	public static final String Database_Name 	= "data.db";
	public static final String Database_NameGy 	= "gyorsulastop10.db";
	public static final String Database_NameSz 	= "szlalomtop10.db";
	public static int Database_Version			= 1;
	public static String DatabaseDriver 		= "org.sqlite.JDBC";
	public static String DatabaseType 			= "jdbc:sqlite:";
	public static String Key_ID 				= "_id";

	public static class Kepek {
		public static final String Database_Table 	= "kepek";
		public static final String Key_Nev			= "nev";
		public static final String Key_Ext 			= "kiterjesztés";
		
		public static final String Database_Drop 	= "drop table if exists "
				+ Database_Table + "; ";
		public static final String Database_Create 	= "create table if not exists "
				+ Database_Table
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Nev 		+ " text, "
				+ Key_Ext		+ " text"
				+ "); ";
	}
	
	public static class Racer {
		public static final String Database_Table 	= "adatok";
		public static final String Key_Rajtszam 	= "rajtszam";
		public static final String Key_Name 		= "nev";
		public static final String Key_Varos 		= "varos";
		public static final String Key_Nem 			= "nem";
		public static final String Key_Potkocsis 	= "potkocsis";
		public static final String Key_Szlalom 		= "szlalom";
		public static final String Key_Gyorsulas 	= "gyorsulas";

		public static final String Database_Drop 	= "drop table if exists "
				+ Database_Table + "; ";
		public static final String Database_Create 	= "create table if not exists "
				+ Database_Table
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam 	+ " integer, "
				+ Key_Name 		+ " text, "
				+ Key_Varos		+ " text, "
				+ Key_Nem 		+ " text, "
				+ Key_Potkocsis + " text, "
				+ Key_Szlalom 	+ " text, "
				+ Key_Gyorsulas	+ " text"
				+ "); ";
	}

	public static class Drag {
		public static final String Database_Table 	= "gyorsulas";
		public static final String Key_Rajtszam 	= "rajtszam";
		public static final String Key_Name 		= "nev";
		public static final String Key_Ido1 		= "ido1";
		public static final String Key_Ido2 		= "ido2";
		public static final String Key_LIdo 		= "legjobb";

		public static final String Database_Drop 	= "drop table if exists "
				+ Database_Table + "; ";
		public static final String Database_Create 	= "create table if not exists "
				+ Database_Table
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam 	+ " integer, "
				+ Key_Name 		+ " text, "
				+ Key_Ido1 		+ " text, "
				+ Key_Ido2 		+ " text, "
				+ Key_LIdo 		+ " text" 
				+ "); ";
	}
	
	public static class DragTop10 {
		public static final String Database_Table1 	= "kör1";
		public static final String Database_Table2 	= "kör2";
		public static final String Database_Table3 	= "kör3";
		public static final String Database_Table4 	= "kör4";
		public static final String Database_Table5 	= "eredmény";
		public static final String Key_Rajtszam 	= "rajtszám";
		public static final String Key_Nev 			= "név";
		public static final String Key_Nyert 		= "nyert";
		
		public static final String Database_Create1 	= "create table if not exists "
				+ Database_Table1
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text, "
				+ Key_Nyert 	+ " integer"
				+ "); ";
		public static final String Database_Create2 	= "create table if not exists "
				+ Database_Table2
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text, "
				+ Key_Nyert 	+ " integer"
				+ "); ";
		public static final String Database_Create3 	= "create table if not exists "
				+ Database_Table3
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text, "
				+ Key_Nyert 	+ " integer"
				+ "); ";
		public static final String Database_Create4 	= "create table if not exists "
				+ Database_Table4
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text, "
				+ Key_Nyert 	+ " integer"
				+ "); ";
		
		public static final String Database_Create5		= "create table if not exists "
				+ Database_Table5
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text "
				+ "); ";
	}
	
	public static class SlalomTop10 {
		public static final String Database_Table1 	= "kör1";
		public static final String Database_Table2 	= "kör2";
		public static final String Database_Table3 	= "kör3";
		public static final String Database_Table4 	= "kör4";
		public static final String Database_Table5 	= "eredmény";
		public static final String Key_Rajtszam 	= "rajtszám";
		public static final String Key_Nev 			= "név";
		public static final String Key_Nyert 		= "nyert";
		
		public static final String Database_Create1 	= "create table if not exists "
				+ Database_Table1
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text, "
				+ Key_Nyert 	+ " integer"
				+ "); ";
		public static final String Database_Create2 	= "create table if not exists "
				+ Database_Table2
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text, "
				+ Key_Nyert 	+ " integer"
				+ "); ";
		public static final String Database_Create3 	= "create table if not exists "
				+ Database_Table3
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text, "
				+ Key_Nyert 	+ " integer"
				+ "); ";
		public static final String Database_Create4 	= "create table if not exists "
				+ Database_Table4
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text, "
				+ Key_Nyert 	+ " integer"
				+ "); ";
		
		public static final String Database_Create5		= "create table if not exists "
				+ Database_Table5
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam	+ " integer, "
				+ Key_Nev	 	+ " text "
				+ "); ";
	}

	public static class Slalom {
		public static final String Database_Table 	= "szlalom";
		public static final String Key_Rajtszam 	= "rajtszam";
		public static final String Key_Name 		= "nev";
		public static final String Key_Ido 			= "ido";
		public static final String Key_Hiba 		= "hiba";
		public static final String Key_VIdo 		= "vegleges";

		public static final String Database_Drop 	= "drop table if exists "
				+ Database_Table + "; ";
		public static final String Database_Create 	= "create table if not exists "
				+ Database_Table
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam 	+ " integer, "
				+ Key_Name 		+ " text, "
				+ Key_Ido 		+ " text, "
				+ Key_Hiba 		+ " text, "
				+ Key_VIdo 		+ " text" 
				+ "); ";
	}

	public static class Trailer {
		public static final String Database_Table 	= "potkocsi";
		public static final String Key_Rajtszam 	= "rajtszam";
		public static final String Key_Name 		= "nev";
		public static final String Key_Ido 			= "ido";
		public static final String Key_Hiba 		= "hiba";
		public static final String Key_VIdo 		= "vegleges";

		public static final String Database_Drop 	= "drop table if exists "
				+ Database_Table + "; ";
		public static final String Database_Create 	= "create table if not exists "
				+ Database_Table
				+ " ( "
				+ Key_ID 		+ " integer primary key autoincrement, "
				+ Key_Rajtszam 	+ " integer, "
				+ Key_Name 		+ " text, "
				+ Key_Ido 		+ " text, "
				+ Key_Hiba 		+ " text, "
				+ Key_VIdo 		+ " text" 
				+ "); ";
	}
}
