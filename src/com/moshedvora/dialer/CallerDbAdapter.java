package com.moshedvora.dialer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CallerDbAdapter {

    CallerDbHelper callerDbHelper;
    public CallerDbAdapter(Context context){
        callerDbHelper=new CallerDbHelper(context);
    }

    public long insertData(int position,String photo,String name,String phone,boolean speaker){
        SQLiteDatabase db=callerDbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CallerDbHelper.POSITION,position);
        contentValues.put(CallerDbHelper.PHOTO,photo);
        contentValues.put(CallerDbHelper.NAME,name);
        contentValues.put(CallerDbHelper.PHONE,phone);
        int speakerFlag = (speaker)? 1 : 0;
        contentValues.put(CallerDbHelper.SPEAKER,speakerFlag);

        long id=db.insert(CallerDbHelper.TABLE_NAME,null,contentValues);
        return id;

    }

    public String getAllData(){
        SQLiteDatabase db = callerDbHelper.getWritableDatabase();
        String[] columns={CallerDbHelper.UID,CallerDbHelper.POSITION,CallerDbHelper.PHOTO,
                            CallerDbHelper.NAME,CallerDbHelper.PHONE,CallerDbHelper.SPEAKER};
        Cursor cursor = db.query(CallerDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer sb = new StringBuffer();
        while (cursor.moveToNext()){

            int uid = cursor.getInt(cursor.getColumnIndex(CallerDbHelper.UID));
            int position = cursor.getInt(cursor.getColumnIndex(CallerDbHelper.POSITION));
            String photo = cursor.getString(cursor.getColumnIndex(CallerDbHelper.PHOTO));
            String name = cursor.getString(cursor.getColumnIndex(CallerDbHelper.NAME));
            String phone = cursor.getString(cursor.getColumnIndex(CallerDbHelper.PHONE));
            int speaker = cursor.getInt(cursor.getColumnIndex(CallerDbHelper.SPEAKER));

            sb.append(uid+"\n"+position+"\n"+photo+"\n"+name+"\n"+phone+"\n"+speaker);

        }
            return sb.toString();
    }

    public String getPhoto(int uid){
        SQLiteDatabase db = callerDbHelper.getWritableDatabase();
        String[] columns={CallerDbHelper.UID,CallerDbHelper.POSITION,CallerDbHelper.PHOTO,
                CallerDbHelper.NAME,CallerDbHelper.PHONE,CallerDbHelper.SPEAKER};
        Cursor cursor = db.query(CallerDbHelper.TABLE_NAME, columns, CallerDbHelper.UID+" = '"+uid+"'" , null, null, null, null);
        String photo=null;
        while (cursor.moveToNext()){
            photo = cursor.getString(cursor.getColumnIndex(CallerDbHelper.PHOTO));
        }
        return photo;
    }

    public String getName(int uid){
        SQLiteDatabase db = callerDbHelper.getWritableDatabase();
        String[] columns={CallerDbHelper.UID,CallerDbHelper.POSITION,CallerDbHelper.PHOTO,
                CallerDbHelper.NAME,CallerDbHelper.PHONE,CallerDbHelper.SPEAKER};
        Cursor cursor = db.query(CallerDbHelper.TABLE_NAME, columns, CallerDbHelper.UID+" = '"+uid+"'" , null, null, null, null);
        String name=null;
        while (cursor.moveToNext()){
             name = cursor.getString(cursor.getColumnIndex(CallerDbHelper.NAME));
        }
        return name;
    }

    public String getPhone(int uid){
        SQLiteDatabase db = callerDbHelper.getWritableDatabase();
        String[] columns={CallerDbHelper.UID,CallerDbHelper.POSITION,CallerDbHelper.PHOTO,
                CallerDbHelper.NAME,CallerDbHelper.PHONE,CallerDbHelper.SPEAKER};
        Cursor cursor = db.query(CallerDbHelper.TABLE_NAME, columns, CallerDbHelper.UID+" = '"+uid+"'" , null, null, null, null);
        String phone=null;
        while (cursor.moveToNext()){
            phone = cursor.getString(cursor.getColumnIndex(CallerDbHelper.PHONE));
        }
        return phone;
    }





     static class CallerDbHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME="callerDb";
        private static final String TABLE_NAME ="callerDbTable";
        private static final int    DATABASE_VERSION=2;
        private static final String UID="_id";
        private static final String POSITION="Position";
        private static final String PHOTO="Photo";
        private static final String NAME="Name";
        private static final String PHONE="Phone";
        private static final String SPEAKER="Speaker";

        private static final String CREATE_TABLE="CREATE TABLE "+ TABLE_NAME +" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+POSITION+" INTEGER,"+PHOTO+" VARCHAR(255),"+NAME+" VARCHAR(255),"+PHONE+" VARCHAR(255),"+SPEAKER+" INTEGER );";
        private static final String DROP_TABLE="DROP TABLE "+ TABLE_NAME +" IF EXITS);";



        public CallerDbHelper(Context context) {
            super(context, DATABASE_NAME, null , DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }
    }

   }
