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
 * Created by Gonzalo on 02/02/2016.
 */
public class Entrantes extends AppCompatActivity {

    private WebView wb;
    private int[] llamadas= new int[]{1,2,6,1,8,2,4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview);
        wb = (WebView)findViewById(R.id.webView);

        setTitle("Llamadas entrantes");
        Intent in = getIntent();
        llamadas = in.getExtras().getIntArray("datosE");

        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wb.setWebChromeClient(new WebChromeClient());
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("file:///android_asset/canvas/entrantes.html");
        //wb.loadUrl("http://canvasjs.com/");
        wb.addJavascriptInterface(this, "InterfazAndroid");
    }

    @JavascriptInterface
    public int enviarDia(int x){
        return llamadas[x];
    }
}