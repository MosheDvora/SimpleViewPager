package com.moshedvora.dialer;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class SimpleViewPagerActivity extends Activity {

    private TelephonyManager telephonyManager;
    private StatePhoneReceiver statePhoneReceiver;
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
        statePhoneReceiver = new StatePhoneReceiver(this);
        telephonyManager.listen(statePhoneReceiver, PhoneStateListener.LISTEN_CALL_STATE);
	}

    public void setCallFromApp(boolean call){
        isCallFromApp =call;
    }

    public boolean isCallFromApp(){
        return isCallFromApp;
    }

    public class StatePhoneReceiver extends PhoneStateListener {

        private SimpleViewPagerActivity simpleViewPagerActivity;

        public StatePhoneReceiver(SimpleViewPagerActivity simpleViewPagerActivity) {
            this.simpleViewPagerActivity= simpleViewPagerActivity;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            switch (state) {

                case TelephonyManager.CALL_STATE_OFFHOOK: //Call is established
                    if (simpleViewPagerActivity.isCallFromApp()) {
                        isCallFromApp =false;
                        isCallFromOffHook =true;

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
                    if (isCallFromOffHook)
                    {
                        isCallFromOffHook =false;
                        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setMode(AudioManager.MODE_NORMAL); //Deactivate loudspeaker
                        telephonyManager.listen(statePhoneReceiver, PhoneStateListener.LISTEN_NONE);  // Remove listener
                    }
                    break;
            }
        }
    }
}