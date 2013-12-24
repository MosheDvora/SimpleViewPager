package com.moshedvora.dialer;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17/12/13.
 */
public class ContactsManager extends Activity {
    private static CallerDbAdapter callerDbAdapter;

    public static List<Contact> mContactList = new ArrayList<Contact>();
    int i;
    public static void  buildContactsManager(int size){
    for(int i=0; i<size; i++){
        mContactList.add(new Contact(i));


    }

    }

    public static void add(int pos, String picture,String name, String phone, boolean speaker,boolean vibration){
        callerDbAdapter = new CallerDbAdapter(MyApp.getAppContext());
        callerDbAdapter.insertData(pos,picture,name,phone,speaker);

     mContactList.add(new Contact(pos));
    }

    public static void delete(int pos){

    }

    public static void save(int pos, String picture,String name, String phone, boolean speaker,boolean vibration){
        mContactList.get(pos).setPicture(picture);
        mContactList.get(pos).setName(name);
        mContactList.get(pos).setPhoneNumber(phone);
        mContactList.get(pos).setSpeakerOn(speaker);
        mContactList.get(pos).setVibration(vibration);
    }

    public static   Contact getContact(int pos){
        return mContactList.get(pos);
    }

    public static int size(){
        return mContactList.size();
    }



}
