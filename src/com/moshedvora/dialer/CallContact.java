package com.moshedvora.dialer;

import android.content.Intent;
import android.net.Uri;

public class CallContact  {
    String phoneNumber;

    protected  Intent makeCall(String phoneNumber){
    String uri = "tel:" + phoneNumber;
    Intent intent = new Intent(Intent.ACTION_CALL);
    intent.setData(Uri.parse(uri));
    return intent;
    }


}
