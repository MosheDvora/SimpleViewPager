package com.moshedvora.dialer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MyPagerAdapter extends PagerAdapter {

    private Main_Activity activity;
    private Animation animFadein;
    //int position=0;

    public MyPagerAdapter(Main_Activity activity) {
            this.activity = activity;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {

        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resId = R.layout.inside_layout;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);


        View view = inflater.inflate(resId, null);
        resId = R.layout.inside_layout;
        view = inflater.inflate(resId, null);
        view.setBackground(activity.getContacts().get(position).getPic());

        ((ViewPager) collection).addView(view, 0);
        final Button btCall = (Button)activity.findViewById(R.id.btCall);
        btCall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                animFadein = AnimationUtils.loadAnimation(activity, R.anim.abc_fade_in);

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    int storedPreference = preferences.getInt("storedInt", 0);
                    Toast.makeText(activity, "Button Pressed", Toast.LENGTH_SHORT).show();

                    btCall.setBackgroundResource(R.drawable.call_button_pressed);
                    btCall.startAnimation(animFadein);
                }

                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    Toast.makeText(activity, "Button Release", Toast.LENGTH_SHORT).show();
                    btCall.setBackgroundResource(R.drawable.call_button_focused);
                    activity.setCallFromApp(true);
                    activity.startActivity(new CallContact().makeCall(activity.getContacts().get(position).getPhoneNumber()));
                }

                return true;
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);

    }

    @Override
    public void finishUpdate(ViewGroup arg0) {
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
    public void startUpdate(ViewGroup arg0) {
        // TODO Auto-generated method stub

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;
    }

}