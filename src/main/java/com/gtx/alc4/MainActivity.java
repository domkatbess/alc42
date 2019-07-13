package com.gtx.alc4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gtx.alc4.exceptions.Alc4AboutException;
import com.gtx.alc4.exceptions.Alc4ProfileException;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeText;
    private WebView webView;
    private WebSettings webSettings;
    public static final String MY_URL="https://andela.com/alc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            manipulateWelcomeText();
        } catch (Alc4ProfileException e) {
            Toast.makeText(this,"there as an error processing your request",Toast.LENGTH_LONG);
            new Alc4ProfileException("A profile request exception thrown");
        } catch (Alc4AboutException e) {
            Toast.makeText(this,"there as an error processing your request",Toast.LENGTH_LONG);
            new Alc4AboutException("An about request error thrown");
        }

        Button btnAbout=(Button) findViewById(R.id.btn_about);
        Button btnProfile=(Button) findViewById(R.id.btn_profile);
        webView=(WebView) findViewById(R.id.webview_about);

        btnAbout.setOnClickListener(new ButtonAboutListener());
        btnProfile.setOnClickListener(new ButtonProfileListener());
    }

    public void createAboutIntent(){
        Intent aboutIntent=new Intent(MainActivity.this,AboutActivity.class);
        startActivity(aboutIntent);
    }

    public void createProfileIntent(){
        Intent profileIntent=new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(profileIntent);
    }

    private void manipulateWelcomeText() throws Alc4ProfileException, Alc4AboutException {
        welcomeText = (TextView) findViewById(R.id.text_welcome);
        String message=welcomeText.getText().toString().trim();
        Spannable ss= new SpannableString(message);
        ForegroundColorSpan fcsOrange=new ForegroundColorSpan(Color.rgb(255,69,0));
        ForegroundColorSpan fcsBlue=new ForegroundColorSpan(Color.BLUE);

        ss.setSpan(fcsOrange,11,14, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsBlue,14,17,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        RelativeSizeSpan rssMedium=new RelativeSizeSpan(1.5f);
        RelativeSizeSpan rssSmall=new RelativeSizeSpan(0.6f);
        //ss.setSpan(rssMedium,8,10,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(rssSmall,14,17,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        welcomeText.setText(ss);

    }

    class ButtonAboutListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            createAboutIntent();
        }
    }

    class ButtonProfileListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            createProfileIntent();
        }
    }
}
