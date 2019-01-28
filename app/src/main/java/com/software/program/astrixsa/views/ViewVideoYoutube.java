package com.software.program.astrixsa.views;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.software.program.astrixsa.R;

/**
 * Created by user on 30/11/2018.
 */

public class ViewVideoYoutube extends AppCompatActivity {
    private WebSettings webSettings;
    private WebView displayYoutubeVideo;
    private String urlVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video);

        Bundle parametros = this.getIntent().getExtras();
        urlVideo = parametros.getString("urlVideo");
        leadVideo(urlVideo);
    }

    private void leadVideo(String urlVideo) {
        // pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        String frameVideo = "<html><body bgcolor='#000000'><center ><iframe width='"+630+"' height='"+343+"' src='"+urlVideo +"' frameborder='0' allowfullscreen></iframe></center ></body></html>";
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
            String frameVideo = "<html><body bgcolor='#000000'><center ><iframe width='"+630+"' height='"+343+"' src='"+urlVideo +"' frameborder='0' allowfullscreen></iframe></center ></body></html>";
            displayYoutubeVideo.loadData(frameVideo,"text/html", "utf-8");
            ViewVideoYoutube.this.finish();
            super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
