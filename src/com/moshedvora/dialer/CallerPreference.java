package com.moshedvora.dialer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by user on 05/11/13.
 */
public class CallerPreference extends Activity {

    private String name,phone;
    private boolean speakerOn,vibrationOn;
    private SharedPreferences mPrefs;
    private EditText mContactName;
    private EditText mContactPhone;
    private CheckedTextView mSpeakerOn;
    private CheckedTextView mVibrationOn;
    private Button btSave;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private ImageView img;
    private Button btChoseImage;
    Uri selectedImageUri;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.caller_preference_layout_r);

        mContactName = (EditText)findViewById(R.id.etContactName);
        Log.d("anr","1");
        mContactPhone = (EditText)findViewById(R.id.etPhoneNumber);
        Log.d("anr","2");
        mSpeakerOn = (CheckedTextView)findViewById(R.id.ctvSpeakerOn);
        Log.d("anr","3");
        mVibrationOn = (CheckedTextView)findViewById(R.id.ctvVibrationOn);
        Log.d("anr","4");
        btSave = (Button)findViewById(R.id.save);
        Log.d("anr","5");
        img = (ImageView)findViewById(R.id.ivContactImage);
        Log.d("anr","6");
       // btChoseImage = ((Button) findViewById(R.id.chooseImage));

        String callerNumber = extras.getString("callerNumber");
        Log.d("anr","7");
        mPrefs = getSharedPreferences(callerNumber, Context.MODE_PRIVATE);
        Log.d("anr","8");
        mContactName.setText(mPrefs.getString("name", ""));
        Log.d("anr","9");
        mContactPhone.setText(mPrefs.getString("phone", ""));
        Log.d("anr","10");
        mSpeakerOn.setChecked(mPrefs.getBoolean("speakerOn", false));
        Log.d("anr","11");
        mVibrationOn.setChecked(mPrefs.getBoolean("vibrationOn", false));
        if (img!=null){
            selectedImageUri=Uri.parse(mPrefs.getString("image",""));
            img.setImageURI(selectedImageUri);
        }

        Log.d("anr","12");

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("anr","31");
                updatePreferenceValue();
                Log.d("anr","32");
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View arg0) {
                Log.d("anr","42");
                        Intent intent = new Intent();
                Log.d("anr","43");
                        intent.setType("image*//*");
                Log.d("anr","44");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                Log.d("anr","45");
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
                Log.d("anr","46");
                    }
                });

        mSpeakerOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                ((CheckedTextView) v).toggle();
            }
        });

        mVibrationOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                ((CheckedTextView) v).toggle();
            }
        });
    }






    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("anr","52");
        if (resultCode == RESULT_OK) {
            Log.d("anr","53");
            if (requestCode == SELECT_PICTURE) {
                Log.d("anr","54");
                  selectedImageUri = data.getData();
                Log.d("anr","55");
               /*   selectedImagePath = getPath(selectedImageUri);
                Log.d("anr","56");
                  System.out.println("Image Path : " + selectedImagePath);*/
                Log.d("anr","57");
                  img.setImageURI(selectedImageUri);
                Log.d("anr","58");
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }



    private void updatePreferenceValue() {
        Log.d("anr","33");
        name = mContactName.getText().toString();
        Log.d("anr","13");
        phone = mContactPhone.getText().toString();
        Log.d("anr","14");
        speakerOn = mSpeakerOn.isChecked();
        Log.d("anr","15");
        vibrationOn = mVibrationOn.isChecked();
        Log.d("anr","16");

        SharedPreferences.Editor editor = mPrefs.edit();
        Log.d("anr","17");
        editor.putBoolean("speakerOn", speakerOn).commit();
        Log.d("anr","18");
        editor.putBoolean("vibrationOn", vibrationOn).commit();
        Log.d("anr","19");
        editor.putString("name", name).commit();
        Log.d("anr","20");
        editor.putString("phone", phone).commit();
        Log.d("anr","22");
        editor.putString("image", selectedImageUri.toString()).commit();
        Log.d("anr","23");
    }
}




