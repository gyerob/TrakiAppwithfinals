package hu.gyerob.trakiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class FinalsActivity extends Activity {

	private Button drag;
	private Button slalom;
	private CheckBox dragcheck;
	private CheckBox slalomcheck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finals);

		drag = (Button) findViewById(R.id.finalsDragbtn);
		slalom = (Button) findViewById(R.id.finalsSlalombtn);
		dragcheck = (CheckBox) findViewById(R.id.checkdragend);
		slalomcheck = (CheckBox) findViewById(R.id.checkslalomend);

		slalom.setOnClickListener(click);
		drag.setOnClickListener(click);
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent i = null;

			switch (v.getId()) {
			case R.id.finalsDragbtn: {
				i = new Intent(getApplicationContext(),
						FinalsDragActivity.class);
				i.putExtra("vege", dragcheck.isChecked());
				Log.d("nyomás", "drag");
				break;
			}
			case R.id.finalsSlalombtn: {
				i = new Intent(getApplicationContext(),
						FinalsSlalomActivity.class);
				i.putExtra("vege", slalomcheck.isChecked());
				Log.d("nyomás", "slalom");
				break;
			}
			}
			startActivity(i);
		}
	};
}
