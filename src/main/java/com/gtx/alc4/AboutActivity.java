package com.gtx.alc4;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gtx.alc4.exceptions.Alc4AboutException;

public class AboutActivity extends AppCompatActivity {

    public static final String MY_URL="https://www.andela.com/alc";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            doWebSettings();
        } catch (Alc4AboutException e) {
            e.printStackTrace();
        }

    }


    public void doWebSettings() throws Alc4AboutException {

        webView = (WebView)findViewById(R.id.webview_about);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
               // super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        webView.loadUrl(MY_URL);
    }

    public boolean OnKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK && this.webView.canGoBack() ){
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

}
