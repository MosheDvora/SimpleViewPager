package com.mamlambo.simpleviewpager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;

/**
 * Created by user on 18/10/13.
 */
public class CallContact  {


    protected static Intent makeCall(String phoneNumber){
    String uri = "tel:" + phoneNumber;
    Intent intent = new Intent(Intent.ACTION_CALL);
    intent.setData(Uri.parse(uri));

    return intent;
    }
}
