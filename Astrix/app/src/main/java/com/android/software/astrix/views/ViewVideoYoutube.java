package com.android.software.astrix.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.software.astrix.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 30/11/2018.
 */

public class ViewVideoYoutube extends AppCompatActivity {
    private static final ArrayList<String> arrayList = new ArrayList<String>(
            Arrays.asList("https://www.youtube.com/embed/dRwq9rAZGyA",
                        "https://www.youtube.com/embed/5V6h1i-7I4s",
                        "https://www.youtube.com/embed/oeYKN6dFOcA",
                        "https://www.youtube.com/embed/6Xs6-j2DIdY",
                        "https://www.youtube.com/embed/eVzVxS5Pe9A"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Nathing", "HOLLLLAAAA");
        setContentView(R.layout.view_video);
        Bundle parametros = this.getIntent().getExtras();
        String date = parametros.getString("AcConSecDia2134");
        int value = Integer.parseInt(date);

        String frameVideo = "<html><body style='margin:auto'><iframe allowfullscreen='allowfullscreen' width=\"550px\" height=\"300px\" src='https://www.youtube.com/embed/oeYKN6dFOcA?autoplay=1'></iframe></body></html>";

        WebView displayYoutubeVideo = (WebView) findViewById(R.id.webview);

        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
    }
}
