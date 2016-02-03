package com.example.gonzalo.pmdmbroadcastreceiver.intents;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.gonzalo.pmdmbroadcastreceiver.R;

/**
 * Created by Gonzalo on 01/02/2016.
 */
public class Salientes extends AppCompatActivity{

    private WebView wb;
    private int[] llamadas= new int[]{1,2,6,1,8,2,4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview);
        wb = (WebView)findViewById(R.id.webView);

        setTitle("Llamadas salientes");
        final Intent i = getIntent();
        llamadas = i.getExtras().getIntArray("datosS");

        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wb.setWebChromeClient(new WebChromeClient());
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("file:///android_asset/canvas/salientes.html");
        wb.addJavascriptInterface(this, "InterfazAndroid");
    }

    @JavascriptInterface
    public int enviarDia(int x){
        return llamadas[x];
    }
}
