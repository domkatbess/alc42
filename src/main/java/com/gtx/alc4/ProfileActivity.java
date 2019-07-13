package com.gtx.alc4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gtx.alc4.exceptions.Alc4ProfileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    private String[] textValuesArray=null;
    private TextView tvName;
    private TextView tvTrack;
    private TextView tvCountry;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvSlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            textValuesArray=readTextViewValues();
            setProfileValues(textValuesArray);
        } catch (Alc4ProfileException e) {
            Toast.makeText(this,"there was as an error processing your request",Toast.LENGTH_LONG);
        }
    }

    private void setProfileValues(String[] textValuesArray) throws Alc4ProfileException {
        tvName = (TextView) findViewById(R.id.textview_name);
        tvTrack = (TextView) findViewById(R.id.textview_track);
        tvCountry = (TextView) findViewById(R.id.textview_country);
        tvEmail = (TextView) findViewById(R.id.textview_email);
        tvPhone = (TextView) findViewById(R.id.textview_phone);
        tvSlack = (TextView) findViewById(R.id.textview_slack);

        tvName.setText(textValuesArray[0].toUpperCase());
        tvTrack.setText(textValuesArray[1].toUpperCase());
        tvCountry.setText(textValuesArray[2].toUpperCase());
        tvEmail.setText(textValuesArray[3].toUpperCase());
        tvPhone.setText(textValuesArray[4].toUpperCase());
        tvSlack.setText(textValuesArray[5].toUpperCase());
    }

    private String[] readTextViewValues() throws Alc4ProfileException {

        try {
            InputStream inputStream=getAssets().open("profile");
            int size=inputStream.available();
            byte[] profilebytes=new byte[size];
            inputStream.read(profilebytes);
            inputStream.close();
            String textValue=new String(profilebytes);

            textValuesArray=textValue.split(",");
            System.out.println("the number of items in array is "+textValuesArray.length);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return textValuesArray;
    }

}
