package com.moshedvora.dialer;


import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Contact {

    private String name,phoneNumber;
    private boolean speakerOn;
    private boolean vibration;
    private Drawable pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSpeakerOn() {
        return speakerOn;
    }

    public void setSpeakerOn(boolean speakerOn) {
        this.speakerOn = speakerOn;
    }

    public Drawable getPic() {
        return pic;
    }

    public void setPic(Drawable pic) {
        this.pic = pic;
    }

    public Contact(String name,String phoneNumber,boolean speakerOn, Drawable pic) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.speakerOn = speakerOn;
        this.pic = pic;

    }

}
