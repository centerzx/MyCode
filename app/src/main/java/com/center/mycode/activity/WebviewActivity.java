package com.center.mycode.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.center.mycode.R;

public class WebviewActivity extends BaseActivity
{
    private WebView myWebview;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        myWebview = (WebView)findViewById(R.id.my_webview);
        WebSettings webSettings = myWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebview.loadUrl("file:///android_asset/test.html");
    }
}
