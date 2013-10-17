package com.mamlambo.simpleviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class SimpleViewPagerActivity extends Activity {
	/** called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		MyPagerAdapter adapter = new MyPagerAdapter();
		ViewPager myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);

	}



	private class MyPagerAdapter extends PagerAdapter {

		public int getCount() {
			return 5;
		}

		public Object instantiateItem(View collection, int position) {

			LayoutInflater inflater = (LayoutInflater) collection.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			int resId = 0;
			switch (position) {
			case 0:{
				resId = R.layout.farleft;

				break;
            }
			case 1:
				resId = R.layout.left;
				break;
			case 2:
				resId = R.layout.middle;
				break;
			case 3:
				resId = R.layout.right;
				break;
			case 4:
				resId = R.layout.farright;
				break;
			}

			View view = inflater.inflate(resId, null);

			((ViewPager) collection).addView(view, 0);

            final Button btCall = (Button)findViewById(R.id.btCall);
            btCall.setOnTouchListener(new View.OnTouchListener() {
                @Override
                //public void onClick(View view) {
                public boolean onTouch(View v, MotionEvent event) {
                    Animation animFadein;
                    animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.abc_fade_in);
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(SimpleViewPagerActivity.this, "Button Pressed", Toast.LENGTH_SHORT).show();
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        Toast.makeText(SimpleViewPagerActivity.this, "Button Release", Toast.LENGTH_SHORT).show();
                        btCall.startAnimation(animFadein);
                        btCall.setBackgroundResource(R.drawable.call_button_pressed);
                    }

           /*       String uri = "tel:" + "089788676";
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);*/
                    return true;
                }
            });

			return view;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);

		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

	}

}