package hu.gyerob.trakiapp;

import adapter.DragTop10FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

public class FinalsDragActivity extends FragmentActivity {

	private ViewPager pager;
	private DragTop10FragmentPagerAdapter adapter;
	public boolean veg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finals_drag);
		veg = getIntent().getExtras().getBoolean("vege");

		FragmentManager fm = getSupportFragmentManager();
		pager = (ViewPager) findViewById(R.id.dragViewPager);
		adapter = new DragTop10FragmentPagerAdapter(fm, veg);
		pager.setAdapter(adapter);
	}
}
