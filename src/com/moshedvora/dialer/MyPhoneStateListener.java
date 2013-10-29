package com.moshedvora.dialer;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class MyPhoneStateListener extends PhoneStateListener {

    private SimpleViewPagerActivity activity;

    public MyPhoneStateListener(SimpleViewPagerActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {

        switch (state) {

            case TelephonyManager.CALL_STATE_OFFHOOK: //Call is established
                if (activity.isCallFromApp())
                {
                    activity.setCallFromApp(false);
                    activity.setCallFromOffHook(true);
                    try
                    {
                        Thread.sleep(500); // Delay 0,5 seconds to handle better turning on loudspeaker
                    } catch (InterruptedException e)
                    {
                    }

                    AudioManager audioManager = (AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setMode(AudioManager.MODE_IN_CALL);
                    audioManager.setSpeakerphoneOn(true);
                }
                break;

            case TelephonyManager.CALL_STATE_IDLE: //Call is finished
                if (activity.isCallFromOffHook())
                {
                    activity.setCallFromOffHook(false);
                    AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setMode(AudioManager.MODE_NORMAL); //Deactivate loudspeaker
                   // telephonyManager.listen(statePhoneReceiver, PhoneStateListener.LISTEN_NONE);  // Remove listener
                }
                break;
        }
    }
}