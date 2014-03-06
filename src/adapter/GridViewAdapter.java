package adapter;

import hu.gyerob.trakiapp.R;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends ArrayAdapter<String> {
	private Context context;
	private int layoutResourceId;
	private ArrayList<String> data = new ArrayList<String>();
	private static String url_img_folder = "http://gyerob.no-ip.biz/trakiweb/pics/";

	public GridViewAdapter(Context context, int layoutResourceId,
			ArrayList<String> picturenames) {
		super(context, layoutResourceId, picturenames);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = picturenames;

		System.out.println(picturenames.size() + " " + this.data.size());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		System.out.println("data mérete" + data.size());
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) row.findViewById(R.id.image);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		if (data != null) {
			System.out.println("elem lekérdezése ha nem üres a lista");
			String item = data.get(position);
			if (holder.image != null) {
				System.out.println("getimage elõtt");
				new GetImage(holder.image).execute(item);
			}
		}
		return row;
	}

	static public class ViewHolder {
		TextView imageTitle;
		ImageView image;
	}

	public class GetImage extends AsyncTask<String, Void, Bitmap> {

		private WeakReference<ImageView> iv;

		public GetImage(ImageView iv) {
			this.iv = new WeakReference<ImageView>(iv);
			System.out.println("getimage konstruktor");
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bm = null;
			for (String param : params) {
				String urlstring = url_img_folder + param;
				System.out.println(urlstring);
				URL url;
				try {
					url = new URL(urlstring);
					bm = BitmapFactory.decodeStream(url.openConnection()
							.getInputStream());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return bm;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (iv != null) {
				ImageView imageView = iv.get();
				if (imageView != null) {
					if (result != null) {
						imageView.setImageBitmap(result);
					}
				}
			}
		}
	}
}
