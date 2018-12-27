package com.software.program.astrixsa.views;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.software.program.astrixsa.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 30/11/2018.
 */

public class ViewVideoYoutube extends AppCompatActivity {
    private WebSettings webSettings;
    private WebView displayYoutubeVideo;

    private static final ArrayList<String> arrayList = new ArrayList<String>(
            Arrays.asList("https://www.youtube.com/embed/dRwq9rAZGyA?rel=0;&autoplay=1",
                    "https://www.youtube.com/embed/5V6h1i-7I4s?rel=0;&autoplay=1",
                    "https://www.youtube.com/embed/oeYKN6dFOcA?rel=0;&autoplay=1",
                    "https://www.youtube.com/embed/6Xs6-j2DIdY?rel=0;&autoplay=1",
                    "https://www.youtube.com/embed/eVzVxS5Pe9A?rel=0;&autoplay=1"));

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
        // pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        String frameVideo = "<html><body bgcolor='#000000'><center ><iframe width='"+630+"' height='"+343+"' src='"+arrayList.get(value) +"' frameborder='0' allowfullscreen></iframe></center ></body></html>";
        //?autoplay=1
        displayYoutubeVideo = findViewById(R.id.webview);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
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
