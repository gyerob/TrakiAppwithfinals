package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import application.App;

import com.google.gson.Gson;

import data.Drag;
import data.Finals;
import data.Racer;
import data.Slalom;
import data.Trailer;
import datastorage.DbConstants;
import datastorage.DbLoader;

public class UpdateAllDB extends AsyncTask<Void, Void, Void> {

	private String SERVER_IP;
	private int SERVERPORT;
	private Socket socket;
	private Gson gson;
	DbLoader dbloader;

	public UpdateAllDB() {
		SERVER_IP = App.getIP();
		SERVERPORT = App.getPort();
		gson = new Gson();
		dbloader = App.getDbLoader();
	}

	public void updateRacer(BufferedReader in, PrintWriter out) {
		try {
			int numberofracers = Integer.parseInt(in.readLine());
			String[] adatok = new String[numberofracers];
			int[] ujracer = new int[numberofracers];
			int j = 0;
			for (int i = 0; i < numberofracers; i++) {
				adatok[i] = in.readLine();
				Log.d("adatok:", adatok[i]);

				Racer r = gson.fromJson(adatok[i], Racer.class);

				Cursor c = dbloader.getRacer(r.getNumber());
				if (c.getCount() < 1) {
					dbloader.inputRacer(r);
					ujracer[j] = r.getNumber();
					j++;
				}
			}

			ArrayList<String> feltoltendok = new ArrayList<String>();
			Cursor racers = dbloader.fetchAllRacer();
			while (racers.moveToNext()) {
				int i = 0;
				boolean friss = false;
				while (i < ujracer.length) {
					int rajtszam = racers.getInt(racers
							.getColumnIndex(DbConstants.Racer.Key_Rajtszam));
					if (ujracer[i] == rajtszam) {
						friss = true;
						break;
					}
					i++;
				}

				if (!friss) {
					String nev, varos, nem, pot, szlalom, gyors;
					int rajt;

					nev = racers.getString(racers
							.getColumnIndex(DbConstants.Racer.Key_Name));
					rajt = racers.getInt(racers
							.getColumnIndex(DbConstants.Racer.Key_Rajtszam));
					varos = racers.getString(racers
							.getColumnIndex(DbConstants.Racer.Key_Varos));
					nem = racers.getString(racers
							.getColumnIndex(DbConstants.Racer.Key_Nem));
					pot = racers.getString(racers
							.getColumnIndex(DbConstants.Racer.Key_Potkocsis));
					szlalom = racers.getString(racers
							.getColumnIndex(DbConstants.Racer.Key_Szlalom));
					gyors = racers.getString(racers
							.getColumnIndex(DbConstants.Racer.Key_Gyorsulas));

					Racer r = new Racer(nev, rajt, varos, nem, pot, szlalom,
							gyors);
					String json = gson.toJson(r);
					Log.d("feltolteall", json);
					feltoltendok.add(json);
				}
			}
			Log.d("felméret", Integer.toString(feltoltendok.size()));
			out.println(feltoltendok.size());

			for (int i = 0; i < feltoltendok.size(); i++) {
				out.println(feltoltendok.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateTrailer(BufferedReader in, PrintWriter out) {
		try {
			int numberofracers = Integer.parseInt(in.readLine());
			String[] adatok = new String[numberofracers];
			int[] ujracer = new int[numberofracers];
			int j = 0;
			for (int i = 0; i < numberofracers; i++) {
				adatok[i] = in.readLine();
				Log.d("adatok:", adatok[i]);

				Trailer r = gson.fromJson(adatok[i], Trailer.class);

				Cursor c = dbloader.getTrailer(r.getNumber());
				if (c.getCount() < 1) {
					dbloader.inputTrailer(r);
					ujracer[j] = r.getNumber();
					j++;
				}
			}

			ArrayList<String> feltoltendok = new ArrayList<String>();
			Cursor racers = dbloader.fetchAllTrailer();
			while (racers.moveToNext()) {
				int i = 0;
				boolean friss = false;
				while (i < ujracer.length) {
					int rajtszam = racers.getInt(racers
							.getColumnIndex(DbConstants.Trailer.Key_Rajtszam));
					if (ujracer[i] == rajtszam) {
						friss = true;
						break;
					}
					i++;
				}

				if (!friss) {
					String nev, ido;
					int rajt, hiba;

					nev = racers.getString(racers
							.getColumnIndex(DbConstants.Trailer.Key_Name));
					rajt = racers.getInt(racers
							.getColumnIndex(DbConstants.Trailer.Key_Rajtszam));
					ido = racers.getString(racers
							.getColumnIndex(DbConstants.Trailer.Key_Ido));
					hiba = racers.getInt(racers
							.getColumnIndex(DbConstants.Trailer.Key_Hiba));

					Trailer r = new Trailer(nev, rajt, ido, hiba);
					String json = gson.toJson(r);
					Log.d("feltolteall", json);
					feltoltendok.add(json);
				}
			}
			Log.d("felméret", Integer.toString(feltoltendok.size()));
			out.println(feltoltendok.size());

			for (int i = 0; i < feltoltendok.size(); i++) {
				out.println(feltoltendok.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateSlalom(BufferedReader in, PrintWriter out) {
		try {
			int numberofracers = Integer.parseInt(in.readLine());
			String[] adatok = new String[numberofracers];
			int[] ujracer = new int[numberofracers];
			int j = 0;
			for (int i = 0; i < numberofracers; i++) {
				adatok[i] = in.readLine();
				Log.d("adatok:", adatok[i]);

				Slalom r = gson.fromJson(adatok[i], Slalom.class);

				Cursor c = dbloader.getSlalom(r.getNumber());
				if (c.getCount() < 1) {
					dbloader.inputSlalom(r);
					ujracer[j] = r.getNumber();
					j++;
				}
			}

			ArrayList<String> feltoltendok = new ArrayList<String>();
			Cursor racers = dbloader.fetchAllSlalom();
			while (racers.moveToNext()) {
				int i = 0;
				boolean friss = false;
				while (i < ujracer.length) {
					int rajtszam = racers.getInt(racers
							.getColumnIndex(DbConstants.Slalom.Key_Rajtszam));
					if (ujracer[i] == rajtszam) {
						friss = true;
						break;
					}
					i++;
				}

				if (!friss) {
					String nev, ido;
					int rajt, hiba;

					nev = racers.getString(racers
							.getColumnIndex(DbConstants.Slalom.Key_Name));
					rajt = racers.getInt(racers
							.getColumnIndex(DbConstants.Slalom.Key_Rajtszam));
					ido = racers.getString(racers
							.getColumnIndex(DbConstants.Slalom.Key_Ido));
					hiba = racers.getInt(racers
							.getColumnIndex(DbConstants.Slalom.Key_Hiba));

					Slalom r = new Slalom(nev, rajt, ido, hiba);
					String json = gson.toJson(r);
					Log.d("feltolteall", json);
					feltoltendok.add(json);
				}
			}
			Log.d("felméret", Integer.toString(feltoltendok.size()));
			out.println(feltoltendok.size());

			for (int i = 0; i < feltoltendok.size(); i++) {
				out.println(feltoltendok.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDrag(BufferedReader in, PrintWriter out) {
		try {
			int numberofracers = Integer.parseInt(in.readLine());
			String[] adatok = new String[numberofracers];
			int[] ujracer = new int[numberofracers];
			int j = 0;
			for (int i = 0; i < numberofracers; i++) {
				adatok[i] = in.readLine();
				Log.d("adatok:", adatok[i]);

				Drag r = gson.fromJson(adatok[i], Drag.class);

				Cursor c = dbloader.getDrag(r.getNumber());
				if (c.getCount() < 1) {
					dbloader.inputDrag(r, true);
					ujracer[j] = r.getNumber();
					j++;
				}
			}

			ArrayList<String> feltoltendok = new ArrayList<String>();
			Cursor racers = dbloader.fetchAllDrag();
			while (racers.moveToNext()) {
				int i = 0;
				boolean friss = false;
				while (i < ujracer.length) {
					int rajtszam = racers.getInt(racers
							.getColumnIndex(DbConstants.Drag.Key_Rajtszam));
					if (ujracer[i] == rajtszam) {
						friss = true;
						break;
					}
					i++;
				}

				if (!friss) {
					String nev, ido1, ido2;
					int rajt;

					nev = racers.getString(racers
							.getColumnIndex(DbConstants.Drag.Key_Name));
					rajt = racers.getInt(racers
							.getColumnIndex(DbConstants.Drag.Key_Rajtszam));
					ido1 = racers.getString(racers
							.getColumnIndex(DbConstants.Drag.Key_Ido1));
					ido2 = racers.getString(racers
							.getColumnIndex(DbConstants.Drag.Key_Ido2));

					Drag r = new Drag(nev, rajt, ido1, ido2);
					String json = gson.toJson(r);
					Log.d("feltolteall", json);
					feltoltendok.add(json);
				}
			}
			Log.d("felméret", Integer.toString(feltoltendok.size()));
			out.println(feltoltendok.size());

			for (int i = 0; i < feltoltendok.size(); i++) {
				out.println(feltoltendok.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDragTop10(BufferedReader in, PrintWriter out) {
		out.println("updatedragtop10");
		try {
			if (!App.isDragloaded()) {
				String[] adatokr1 = new String[8];
				String[] adatokr2 = new String[4];
				String[] adatokr3 = new String[4];
				String[] adatokr4 = new String[4];
				for (int i = 0; i < 8; i++) {
					adatokr1[i] = in.readLine();
					Log.d("adatok:", adatokr1[i]);
					Finals f = gson.fromJson(adatokr1[i], Finals.class);
					dbloader.updateDragRound1(f, i);
				}
				for (int i = 0; i < 4; i++) {
					adatokr2[i] = in.readLine();
					Log.d("adatok:", adatokr2[i]);
					Finals f = gson.fromJson(adatokr2[i], Finals.class);
					dbloader.updateDragRound2(f, i);
				}
				for (int i = 0; i < 4; i++) {
					adatokr3[i] = in.readLine();
					Log.d("adatok:", adatokr3[i]);
					Finals f = gson.fromJson(adatokr3[i], Finals.class);
					dbloader.updateDragRound3(f, i);
				}
				for (int i = 0; i < 4; i++) {
					adatokr4[i] = in.readLine();
					Log.d("adatok:", adatokr4[i]);
					Finals f = gson.fromJson(adatokr4[i], Finals.class);
					dbloader.updateDragRound4(f, i);
				}
			} else {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateSlalomTop10() {
		if (!App.isSlalomloaded()) {

		} else {

		}
	}

	@Override
	protected Void doInBackground(Void... params) {
		InetAddress serverAddr = null;

		try {
			serverAddr = InetAddress.getByName(SERVER_IP);
			socket = new Socket(serverAddr, SERVERPORT);

			PrintWriter output = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);

			output.println("updatealldb");

			BufferedReader input = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "ISO-8859-1"));

			updateRacer(input, output);
			updateTrailer(input, output);
			updateSlalom(input, output);
			updateDrag(input, output);

			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
