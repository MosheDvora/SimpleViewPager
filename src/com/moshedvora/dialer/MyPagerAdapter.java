package com.moshedvora.dialer;

import android.app.Activity;
import android.content.Context;
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


public class MyPagerAdapter extends PagerAdapter {


    private Activity activity;
    Animation animFadein;
    CallContact call;
    Boolean callFromApp;



    public MyPagerAdapter(Activity activity,Boolean callFromApp) {
        this.activity = activity;
        this.callFromApp=callFromApp;
    }

    @Override
    public int getCount() {
        return 5;
    }
    @Override
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

        final Button btCall = (Button)activity.findViewById(R.id.btCall);
        btCall.setOnTouchListener(new View.OnTouchListener() {
            @Override


            public boolean onTouch(View v, MotionEvent event) {
                animFadein = AnimationUtils.loadAnimation(activity, R.anim.abc_fade_in);

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Toast.makeText(activity, "Button Pressed", Toast.LENGTH_SHORT).show();
                    btCall.setBackgroundResource(R.drawable.call_button_pressed);
                    btCall.startAnimation(animFadein);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Toast.makeText(activity, "Button Release", Toast.LENGTH_SHORT).show();
                    btCall.setBackgroundResource(R.drawable.call_button_focused);
                    SimpleViewPagerActivity.setcallFromApp(true);
                    call = new CallContact("089788676");
                    activity.startActivity(call.makeCall());
                }

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