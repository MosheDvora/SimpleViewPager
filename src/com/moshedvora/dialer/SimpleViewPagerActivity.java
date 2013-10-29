package com.moshedvora.dialer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SimpleViewPagerActivity extends Activity {

    private TelephonyManager telephonyManager;
    private MyPhoneStateListener statePhoneReceiver;
    private boolean isCallFromOffHook =false;
    private boolean isCallFromApp =false;

	@Override
	public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		MyPagerAdapter adapter = new MyPagerAdapter(this);
		ViewPager myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);

        telephonyManager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        statePhoneReceiver = new MyPhoneStateListener(this);
        telephonyManager.listen(statePhoneReceiver, PhoneStateListener.LISTEN_CALL_STATE);
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Menu item 2 selected", Toast.LENGTH_SHORT)
                        .show();
                break;

            default:
                break;
        }

        return true;
    }

    public void setCallFromApp(boolean call){
        isCallFromApp =call;
    }

    public boolean isCallFromApp(){
        return isCallFromApp;
    }

    public void setCallFromOffHook(boolean call){
        isCallFromOffHook =call;
    }

    public boolean isCallFromOffHook(){
        return isCallFromOffHook;
    }

}