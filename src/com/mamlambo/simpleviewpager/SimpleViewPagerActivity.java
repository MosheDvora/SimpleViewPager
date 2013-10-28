package com.mamlambo.simpleviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class SimpleViewPagerActivity extends Activity {
    TelephonyManager manager;
    StatePhoneReceiver myPhoneStateListener;
    boolean callFromOffHook=false;
    static boolean callFromApp=false;

	@Override
	public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		MyPagerAdapter adapter = new MyPagerAdapter(this,callFromApp);
		ViewPager myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);

        manager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        myPhoneStateListener = new StatePhoneReceiver(this);
        manager.listen(myPhoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
	}

    public static void  setcallFromApp(Boolean call){
        callFromApp=call;
    }

    public static boolean getcallFromApp(){
        return callFromApp;
    }




    // Phone Listener
    public class StatePhoneReceiver extends PhoneStateListener {
        Context context;
        public StatePhoneReceiver(Context context) {
            this.context = context;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
          //  super.onCallStateChanged(state, incomingNumber);

            switch (state) {

                case TelephonyManager.CALL_STATE_OFFHOOK: //Call is established
                    if (SimpleViewPagerActivity.getcallFromApp()) {
                        callFromApp=false;
                        callFromOffHook=true;

                        try
                        {
                            Thread.sleep(500); // Delay 0,5 seconds to handle better turning on loudspeaker
                        } catch (InterruptedException e)
                        {
                        }

                        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setMode(AudioManager.MODE_IN_CALL);
                        audioManager.setSpeakerphoneOn(true);
                    }
                    break;

                case TelephonyManager.CALL_STATE_IDLE: //Call is finished
                    if (callFromOffHook)
                    {
                        callFromOffHook=false;
                        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setMode(AudioManager.MODE_NORMAL); //Deactivate loudspeaker
                        manager.listen(myPhoneStateListener,PhoneStateListener.LISTEN_NONE);  // Remove listener
                    }
                    break;
            }
        }
    }
}