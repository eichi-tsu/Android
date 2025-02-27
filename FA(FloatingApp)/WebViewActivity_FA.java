package com.example.tamesi_floating;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebViewActivity extends AppCompatActivity {
    private String TAG = WebViewActivity.class.getSimpleName();

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Intent i = getIntent();
        String i2 = i.getStringExtra("code");
        webView = (WebView) findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.setDownloadListener(new DownloadListener(){
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

            }});

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);

        webView.getSettings().setJavaScriptEnabled(true);
//        webview.addJavascriptInterface(new AndroidBridge(), "android");
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);

        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("https://google.com");
        String inputData = webView.getUrl().toString();
        Pattern pattern = Pattern.compile("(http|https):\\/\\/b9good\\.org");
        Matcher matcher = pattern.matcher(inputData);
        long curTime2;
        long curTime = System.currentTimeMillis();
        do{
            curTime2 = System.currentTimeMillis();
        }
        while((curTime2-curTime)/1000==5);
        if((curTime2-curTime)/1000==5){
            webView.loadUrl("http://google.com");
        }
        if(matcher.find()){
            Toast.makeText(this,"alert : redirected!!",Toast.LENGTH_LONG).show();
            Log.d("rutora's",webView.getUrl()+"redirected!!!");
        }else{
            webView.loadUrl("https://google.com");
        }
        /*switch(i2){
            case("w"):
                if(matcher.find()){
                    Toast.makeText(this,"alert : redirected!!",Toast.LENGTH_LONG).show();
                }else{
                    webView.loadUrl("https://google.com");
                }
            case("c"):
                if(matcher.find()){

                }else{
                    webView.loadUrl("https://google.com");
                }
            case("l"):
                if(matcher.find()){
                    Log.d("rutora's",webView.getUrl());
                }else{
                    webView.loadUrl("https://google.com");
                }
            case("w_c"):
                if(matcher.find()){
                    Toast.makeText(this,"alert : redirected!!",Toast.LENGTH_LONG).show();
                }else{
                    webView.loadUrl("https://google.com");
                }
            case("w_c_l"):
                if(matcher.find()){
                    Toast.makeText(this,"alert : redirected!!",Toast.LENGTH_LONG).show();
                    Log.d("rutora's",webView.getUrl());
                }else{
                    webView.loadUrl("https://google.com");
                }
        }*/

        /*if(Pattern.matches("^[a-zA-Z0-9]",webView.getUrl())){
            Toast.makeText(this,"alert : redirected!!",Toast.LENGTH_LONG).show();
            Log.d("watais",webView.getUrl());
        }
        else{
            webView.loadUrl("https://google.com");
        }*/
    }

}
