package finalsdragfragments;

import hu.gyerob.trakiapp.R;

import java.util.ArrayList;
import java.util.List;

import jsonParser.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import toplistview.ToplistView;
import toplistview.ToplistView.IListViewUpdate;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import data.DragTop;

public class DragTop10Fragment1 extends Fragment implements IListViewUpdate {
	public static final String TITLE = "Lépcsők";

	private static String url_get_drag_top = "http://gyerob.no-ip.biz/trakiweb/get_all_drag_top.php";
	private static String url_update_drag_top = "http://gyerob.no-ip.biz/trakiweb/update_drag_top.php";
	private static String url_update_next_drag_top = "http://gyerob.no-ip.biz/trakiweb/update_next_drag_top.php";

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "racers";

	// Progress Dialog
	private ProgressDialog pDialog;

	private JSONParser jsonParser;

	private ArrayList<DragTop> dragListr1;
	private ArrayList<DragTop> dragListr2;
	private ArrayList<DragTop> dragListr3;
	private ArrayList<DragTop> dragListr4;
	private JSONArray racers = null;

	ToplistView tvRound11;
	ToplistView tvRound12;
	ToplistView tvRound13;
	ToplistView tvRound14;
	ToplistView tvRound15;
	ToplistView tvRound16;
	ToplistView tvRound17;
	ToplistView tvRound18;

	ToplistView tvRound21;
	ToplistView tvRound22;
	ToplistView tvRound23;
	ToplistView tvRound24;

	ToplistView tvRound31;
	ToplistView tvRound32;
	ToplistView tvRound33;
	ToplistView tvRound34;

	ToplistView tvRound41;
	ToplistView tvRound42;
	ToplistView tvRound43;
	ToplistView tvRound44;

	ArrayList<ToplistView> topracers;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		jsonParser = new JSONParser();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.finalsdragscrollv2, container,
				false);

		tvRound11 = (ToplistView) v.findViewById(R.id.dtoplistr11);
		tvRound12 = (ToplistView) v.findViewById(R.id.dtoplistr12);
		tvRound13 = (ToplistView) v.findViewById(R.id.dtoplistr13);
		tvRound14 = (ToplistView) v.findViewById(R.id.dtoplistr14);
		tvRound15 = (ToplistView) v.findViewById(R.id.dtoplistr15);
		tvRound16 = (ToplistView) v.findViewById(R.id.dtoplistr16);
		tvRound17 = (ToplistView) v.findViewById(R.id.dtoplistr17);
		tvRound18 = (ToplistView) v.findViewById(R.id.dtoplistr18);

		tvRound21 = (ToplistView) v.findViewById(R.id.dtoplistr21);
		tvRound22 = (ToplistView) v.findViewById(R.id.dtoplistr22);
		tvRound23 = (ToplistView) v.findViewById(R.id.dtoplistr23);
		tvRound24 = (ToplistView) v.findViewById(R.id.dtoplistr24);

		tvRound31 = (ToplistView) v.findViewById(R.id.dtoplistr31);
		tvRound32 = (ToplistView) v.findViewById(R.id.dtoplistr32);
		tvRound33 = (ToplistView) v.findViewById(R.id.dtoplistr33);
		tvRound34 = (ToplistView) v.findViewById(R.id.dtoplistr34);

		tvRound41 = (ToplistView) v.findViewById(R.id.dtoplistr41);
		tvRound42 = (ToplistView) v.findViewById(R.id.dtoplistr42);
		tvRound43 = (ToplistView) v.findViewById(R.id.dtoplistr43);
		tvRound44 = (ToplistView) v.findViewById(R.id.dtoplistr44);

		tvRound11.setDragListener(this);
		tvRound12.setDragListener(this);
		tvRound13.setDragListener(this);
		tvRound14.setDragListener(this);
		tvRound15.setDragListener(this);
		tvRound16.setDragListener(this);
		tvRound17.setDragListener(this);
		tvRound18.setDragListener(this);

		tvRound21.setDragListener(this);
		tvRound22.setDragListener(this);
		tvRound23.setDragListener(this);
		tvRound24.setDragListener(this);

		tvRound31.setDragListener(this);
		tvRound32.setDragListener(this);
		tvRound33.setDragListener(this);
		tvRound34.setDragListener(this);

		tvRound41.setDragListener(this);
		tvRound42.setDragListener(this);
		tvRound43.setDragListener(this);
		tvRound44.setDragListener(this);

		tvRound11.setRound(1);
		tvRound12.setRound(1);
		tvRound13.setRound(1);
		tvRound14.setRound(1);
		tvRound15.setRound(1);
		tvRound16.setRound(1);
		tvRound17.setRound(1);
		tvRound18.setRound(1);

		tvRound21.setRound(2);
		tvRound22.setRound(2);
		tvRound23.setRound(2);
		tvRound24.setRound(2);

		tvRound31.setRound(3);
		tvRound32.setRound(3);
		tvRound33.setRound(3);
		tvRound34.setRound(3);

		tvRound41.setRound(4);
		tvRound42.setRound(4);
		tvRound43.setRound(4);
		tvRound44.setRound(4);

		dragListr1 = new ArrayList<DragTop>();
		dragListr2 = new ArrayList<DragTop>();
		dragListr3 = new ArrayList<DragTop>();
		dragListr4 = new ArrayList<DragTop>();
		new GetList().execute();

		return v;
	}

	@Override
	public void onRacerClick(ToplistView tv) {
		Log.d("katt elfogás", "sikeres");

		new UpdateList().execute(tv);
	}

	class UpdateList extends AsyncTask<ToplistView, String, ToplistView> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(
					DragTop10Fragment1.this.getActivity());
			pDialog.setMessage("Versenyző eltárolása..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected ToplistView doInBackground(ToplistView... param) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("rajt", Integer.toString(param[0]
					.getNumber())));
			params.add(new BasicNameValuePair("nev", param[0].getName()));
			params.add(new BasicNameValuePair("nyert", Integer
					.toString(param[0].getWin())));
			params.add(new BasicNameValuePair("kor", Integer.toString(param[0]
					.getRound())));

			Log.d("elküldendő:",
					param[0].getName() + " " + param[0].getNumber() + " "
							+ param[0].getWin() + " " + param[0].getRound());

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_update_drag_top,
					"POST", params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			if (param[0].getWin() == 2) {
				params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("rajt", Integer
						.toString(param[0].getNumber())));
				params.add(new BasicNameValuePair("nev", param[0].getName()));
				params.add(new BasicNameValuePair("kor", Integer
						.toString(param[0].getRound() + 1)));

				if (param[0].getRound() == 1) {
					if (param[0].getPid() == 8 || param[0].getPid() == 1) {
						params.add(new BasicNameValuePair("pid", Integer
								.toString(3)));
					} else if (param[0].getPid() == 7 || param[0].getPid() == 2) {
						params.add(new BasicNameValuePair("pid", Integer
								.toString(2)));
					} else if (param[0].getPid() == 6 || param[0].getPid() == 3) {
						params.add(new BasicNameValuePair("pid", Integer
								.toString(4)));
					} else if (param[0].getPid() == 5 || param[0].getPid() == 4) {
						params.add(new BasicNameValuePair("pid", Integer
								.toString(1)));
					}
					json = jsonParser.makeHttpRequest(
							url_update_next_drag_top, "POST", params);
				} else if (param[0].getRound() == 2) {
					if (param[0].getPid() == 1 || param[0].getPid() == 2) {
						params.add(new BasicNameValuePair("pid", Integer
								.toString(1)));
					} else if (param[0].getPid() == 3 || param[0].getPid() == 4) {
						params.add(new BasicNameValuePair("pid", Integer
								.toString(4)));
					}
					json = jsonParser.makeHttpRequest(
							url_update_next_drag_top, "POST", params);
				} else if (param[0].getRound() == 3) {
					if (param[0].getPid() == 1 || param[0].getPid() == 2) {
						params.add(new BasicNameValuePair("pid", Integer
								.toString(1)));
						json = jsonParser.makeHttpRequest(
								url_update_next_drag_top, "POST", params);
						if (param[0].getPid() == 1) {
							params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("rajt", Integer
									.toString(tvRound32.getNumber())));
							params.add(new BasicNameValuePair("nev", tvRound32
									.getName()));
							params.add(new BasicNameValuePair("kor", Integer
									.toString(tvRound32.getRound() + 1)));
							params.add(new BasicNameValuePair("pid", Integer
									.toString(3)));
						} else {
							params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("rajt", Integer
									.toString(tvRound31.getNumber())));
							params.add(new BasicNameValuePair("nev", tvRound31
									.getName()));
							params.add(new BasicNameValuePair("kor", Integer
									.toString(tvRound31.getRound() + 1)));
							params.add(new BasicNameValuePair("pid", Integer
									.toString(3)));
						}
						json = jsonParser.makeHttpRequest(
								url_update_next_drag_top, "POST", params);
					} else if (param[0].getPid() == 3 || param[0].getPid() == 4) {
						params.add(new BasicNameValuePair("pid", Integer
								.toString(2)));
						json = jsonParser.makeHttpRequest(
								url_update_next_drag_top, "POST", params);
						if (param[0].getPid() == 3) {
							params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("rajt", Integer
									.toString(tvRound34.getNumber())));
							params.add(new BasicNameValuePair("nev", tvRound34
									.getName()));
							params.add(new BasicNameValuePair("kor", Integer
									.toString(tvRound34.getRound() + 1)));
							params.add(new BasicNameValuePair("pid", Integer
									.toString(4)));
						} else {
							params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("rajt", Integer
									.toString(tvRound33.getNumber())));
							params.add(new BasicNameValuePair("nev", tvRound33
									.getName()));
							params.add(new BasicNameValuePair("kor", Integer
									.toString(tvRound33.getRound() + 1)));
							params.add(new BasicNameValuePair("pid", Integer
									.toString(4)));
						}
						json = jsonParser.makeHttpRequest(
								url_update_next_drag_top, "POST", params);
					}
				}
				
				Intent intent = new Intent("gyfrissit");
				LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);

				Log.d("Create Response", json.toString());
			}

			return param[0];
		}

		protected void onPostExecute(ToplistView nev) {
			// dismiss the dialog once done
			pDialog.dismiss();
			if (nev.getWin() == 2) {
				if (nev.getRound() == 1) {
					if (nev.getPid() == 8 || nev.getPid() == 1) {
						tvRound23.setNumber(nev.getNumber());
						tvRound23.setPid(nev.getPid());
						tvRound23.setName(nev.getName());
					} else if (nev.getPid() == 7 || nev.getPid() == 2) {
						tvRound22.setNumber(nev.getNumber());
						tvRound22.setPid(nev.getPid());
						tvRound22.setName(nev.getName());
					} else if (nev.getPid() == 6 || nev.getPid() == 3) {
						tvRound24.setNumber(nev.getNumber());
						tvRound24.setPid(nev.getPid());
						tvRound24.setName(nev.getName());
					} else if (nev.getPid() == 5 || nev.getPid() == 4) {
						tvRound21.setNumber(nev.getNumber());
						tvRound21.setPid(nev.getPid());
						tvRound21.setName(nev.getName());
					}
				} else if (nev.getRound() == 2) {
					if (nev.getPid() == 1 || nev.getPid() == 2) {
						tvRound31.setNumber(nev.getNumber());
						tvRound31.setPid(nev.getPid());
						tvRound31.setName(nev.getName());
					} else if (nev.getPid() == 3 || nev.getPid() == 4) {
						tvRound34.setNumber(nev.getNumber());
						tvRound34.setPid(nev.getPid());
						tvRound34.setName(nev.getName());
					}
				} else if (nev.getRound() == 3) {
					if (nev.getPid() == 1 || nev.getPid() == 2) {
						tvRound41.setNumber(nev.getNumber());
						tvRound41.setPid(nev.getPid());
						tvRound41.setName(nev.getName());
						if (nev.getPid() == 1) {
							tvRound43.setNumber(tvRound32.getNumber());
							tvRound43.setPid(tvRound32.getPid());
							tvRound43.setName(tvRound32.getName());
						} else {
							tvRound43.setNumber(tvRound31.getNumber());
							tvRound43.setPid(tvRound31.getPid());
							tvRound43.setName(tvRound31.getName());
						}
					} else if (nev.getPid() == 3 || nev.getPid() == 4) {
						tvRound42.setNumber(nev.getNumber());
						tvRound42.setPid(nev.getPid());
						tvRound42.setName(nev.getName());
						if (nev.getPid() == 4) {
							tvRound44.setNumber(tvRound33.getNumber());
							tvRound44.setPid(tvRound33.getPid());
							tvRound44.setName(tvRound33.getName());
						} else {
							tvRound44.setNumber(tvRound34.getNumber());
							tvRound44.setPid(tvRound34.getPid());
							tvRound44.setName(tvRound34.getName());
						}
					}
				}
			}
		}
	}

	class GetList extends AsyncTask<String, String, String> {

		boolean failed = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(
					DragTop10Fragment1.this.getActivity());
			pDialog.setMessage("Versenyző frissítése..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... param) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(url_get_drag_top,
					"GET", params);

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					racers = json.getJSONArray(TAG_PRODUCTS);

					Log.d("racers hossza:", Integer.toString(racers.length()));
					// looping through All Products
					for (int i = 0; i < racers.length(); i++) {
						JSONObject c = racers.getJSONObject(i);

						DragTop racer = new DragTop();

						// Storing each json item in variable
						racer.setNumber(Integer.parseInt(c.getString("rajt")));
						racer.setName(c.getString("nev"));
						racer.setWon(Integer.parseInt(c.getString("nyert")));
						racer.setPid(Integer.parseInt(c.getString("pid")));

						if (i < 8)
							dragListr1.add(racer);
						else if (i < 12)
							dragListr2.add(racer);
						else if (i < 16)
							dragListr3.add(racer);
						else if (i < 20)
							dragListr4.add(racer);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				failed = true;
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();

			if (failed) {
				Toast.makeText(
						DragTop10Fragment1.this.getActivity(),
						"Sikertelen lekérés\n, ellenőrizd az internetkapcsolatot.",
						Toast.LENGTH_LONG).show();
			} else {
				tvRound11.setData(dragListr1.get(4).getName(), dragListr1
						.get(4).getNumber(), dragListr1.get(4).getWon(),
						dragListr1.get(4).getPid());
				Log.d("1round", tvRound11.getName() + " " + tvRound11.getPid());
				tvRound12.setData(dragListr1.get(3).getName(), dragListr1
						.get(3).getNumber(), dragListr1.get(3).getWon(),
						dragListr1.get(3).getPid());
				Log.d("1round", tvRound12.getName() + " " + tvRound12.getPid());
				tvRound13.setData(dragListr1.get(1).getName(), dragListr1
						.get(1).getNumber(), dragListr1.get(1).getWon(),
						dragListr1.get(1).getPid());
				Log.d("1round", tvRound13.getName() + " " + tvRound13.getPid());
				tvRound14.setData(dragListr1.get(6).getName(), dragListr1
						.get(6).getNumber(), dragListr1.get(6).getWon(),
						dragListr1.get(6).getPid());
				Log.d("1round", tvRound14.getName() + " " + tvRound14.getPid());
				tvRound15.setData(dragListr1.get(7).getName(), dragListr1
						.get(7).getNumber(), dragListr1.get(7).getWon(),
						dragListr1.get(7).getPid());
				Log.d("1round", tvRound15.getName() + " " + tvRound15.getPid());
				tvRound16.setData(dragListr1.get(0).getName(), dragListr1
						.get(0).getNumber(), dragListr1.get(0).getWon(),
						dragListr1.get(0).getPid());
				Log.d("1round", tvRound16.getName() + " " + tvRound16.getPid());
				tvRound17.setData(dragListr1.get(2).getName(), dragListr1
						.get(2).getNumber(), dragListr1.get(2).getWon(),
						dragListr1.get(2).getPid());
				Log.d("1round", tvRound17.getName() + " " + tvRound17.getPid());
				tvRound18.setData(dragListr1.get(5).getName(), dragListr1
						.get(5).getNumber(), dragListr1.get(5).getWon(),
						dragListr1.get(5).getPid());
				Log.d("1round", tvRound18.getName() + " " + tvRound18.getPid());

				tvRound21.setData(dragListr2.get(0).getName(), dragListr2
						.get(0).getNumber(), dragListr2.get(0).getWon(),
						dragListr2.get(0).getPid());
				tvRound22.setData(dragListr2.get(1).getName(), dragListr2
						.get(1).getNumber(), dragListr2.get(1).getWon(),
						dragListr2.get(1).getPid());
				tvRound23.setData(dragListr2.get(2).getName(), dragListr2
						.get(2).getNumber(), dragListr2.get(2).getWon(),
						dragListr2.get(2).getPid());
				tvRound24.setData(dragListr2.get(3).getName(), dragListr2
						.get(3).getNumber(), dragListr2.get(3).getWon(),
						dragListr2.get(3).getPid());

				tvRound31.setData(dragListr3.get(0).getName(), dragListr3
						.get(0).getNumber(), dragListr3.get(0).getWon(),
						dragListr3.get(0).getPid());
				tvRound32.setData(dragListr3.get(1).getName(), dragListr3
						.get(1).getNumber(), dragListr3.get(1).getWon(),
						dragListr3.get(1).getPid());
				tvRound33.setData(dragListr3.get(2).getName(), dragListr3
						.get(2).getNumber(), dragListr3.get(2).getWon(),
						dragListr3.get(2).getPid());
				tvRound34.setData(dragListr3.get(3).getName(), dragListr3
						.get(3).getNumber(), dragListr3.get(3).getWon(),
						dragListr3.get(3).getPid());

				tvRound41.setData(dragListr4.get(0).getName(), dragListr4
						.get(0).getNumber(), dragListr4.get(0).getWon(),
						dragListr4.get(0).getPid());
				tvRound42.setData(dragListr4.get(1).getName(), dragListr4
						.get(1).getNumber(), dragListr4.get(1).getWon(),
						dragListr4.get(1).getPid());
				tvRound43.setData(dragListr4.get(2).getName(), dragListr4
						.get(2).getNumber(), dragListr4.get(2).getWon(),
						dragListr4.get(2).getPid());
				tvRound44.setData(dragListr4.get(3).getName(), dragListr4
						.get(3).getNumber(), dragListr4.get(3).getWon(),
						dragListr4.get(3).getPid());
			}
		}
	}
}
