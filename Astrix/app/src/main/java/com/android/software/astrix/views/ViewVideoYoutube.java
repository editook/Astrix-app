package com.android.software.astrix.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.software.astrix.App;
import com.android.software.astrix.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 30/11/2018.
 */

public class ViewVideoYoutube extends AppCompatActivity {
    WebSettings webSettings;
    WebView displayYoutubeVideo;

    private static final ArrayList<String> arrayList = new ArrayList<String>(
            Arrays.asList("https://www.youtube.com/embed/dRwq9rAZGyA?playlist=tgbNymZ7vqY&loop=1",
                    "https://www.youtube.com/embed/5V6h1i-7I4s?playlist=tgbNymZ7vqY&loop=1",
                    "https://www.youtube.com/embed/oeYKN6dFOcA?playlist=tgbNymZ7vqY&loop=1",
                    "https://www.youtube.com/embed/6Xs6-j2DIdY?playlist=tgbNymZ7vqY&loop=1",
                    "https://www.youtube.com/embed/eVzVxS5Pe9A?playlist=tgbNymZ7vqY&loop=1"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video);

        Bundle parametros = this.getIntent().getExtras();
        String date = parametros.getString("AcConSecDia2134");
        int value = Integer.parseInt(date);
        leadVideo(value);
    }

    private void leadVideo(int value) {
        String frameVideo = "<html><body bgcolor='#000000' style='margin-top: 20px;'><center ><iframe style=\"float:middle\" allowfullscreen='allowfullscreen' width='580px' height='320px' src='"+arrayList.get(value) +"'></iframe></center ></body></html>";
        //?autoplay=1
        displayYoutubeVideo = (WebView) findViewById(R.id.webview);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        webSettings = displayYoutubeVideo.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            String frameVideo = "<html><body style='margin-top: 20px;'><center ><iframe style=\"float:middle\" allowfullscreen='allowfullscreen' width='550px' height='300px' src='"+arrayList.get(0) +"'></iframe></center ></body></html>";
            displayYoutubeVideo.loadData(frameVideo,"text/html", "utf-8");
            ViewVideoYoutube.this.finish();
            super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
