package com.moshedvora.dialer;


import android.content.Context;
import android.content.SharedPreferences;

public class Contact {

    private String name,phoneNumber;
    private boolean speakerOn;
    private boolean vibration;
    private String mPicture;
    SharedPreferences mPrefs;
    private CallerDbAdapter callerDbAdapter;

    public Contact (int id){

        mPrefs = MyApp.getAppContext().getSharedPreferences("pagePosition" + id, Context.MODE_PRIVATE);
        this.name = mPrefs.getString("name", "");
        this.phoneNumber = mPrefs.getString("phone", "");
        this.speakerOn = true;
        this.mPicture = mPrefs.getString("image", "empty");
        callerDbAdapter = new CallerDbAdapter(MyApp.getAppContext());


    }

    public String getName() {
        return mPrefs.getString("name", "");
    }

    public void setName(String name) {
       // this.name = name;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("name", name).commit();
    }

    public String getPhoneNumber() {
        return mPrefs.getString("phone", "");
    }

    public String getmPicture() {
        return mPrefs.getString("image", "empty");
    }

    public void setPhoneNumber(String phoneNumber) {
       // this.phoneNumber = phoneNumber;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("phone", phoneNumber).commit();


    }

    public boolean isSpeakerOn() {
        return mPrefs.getBoolean("speakerOn", false);
    }

    public void setSpeakerOn(boolean speakerOn) {
        this.speakerOn = speakerOn;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean("speakerOn", speakerOn).commit();
    }


    public void setPicture(String picture) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("image",picture).commit();
    }

    public void setVibration(boolean vibration) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean("vibrationOn", vibration).commit();
    }

    public boolean isVibration() {
        return mPrefs.getBoolean("vibrationOn", false);
    }


}
