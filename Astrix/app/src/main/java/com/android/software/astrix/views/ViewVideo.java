package com.android.software.astrix.views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.software.astrix.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 30/11/2018.
 */

public class ViewVideo extends AppCompatActivity{
    VideoView videoView;
    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList("https://mega.nz/#!YiZmwKTa!rTqTsLKFripekreUUO3LU1K0wYqFZXjDYEETuP7NA2Y","https://mega.nz/#!YiZmwKTa!rTqTsLKFripekreUUO3LU1K0wYqFZXjDYEETuP7NA2Y","https://mega.nz/#!YiZmwKTa!rTqTsLKFripekreUUO3LU1K0wYqFZXjDYEETuP7NA2Y","https://mega.nz/#!YiZmwKTa!rTqTsLKFripekreUUO3LU1K0wYqFZXjDYEETuP7NA2Y", "https://mega.nz/#!MyJwiYoS!bnZtnqWPEMBn-HO7nvEacGZU_hkSES-KBxpZ7zS-_Pc"));

    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video);
        Bundle parametros = this.getIntent().getExtras();
        String date = parametros.getString("AcConSecDia2134");
        int value = Integer.parseInt(date);
        playVideo(value);

    }
    private void playVideo(int value){
        getWindow().getDecorView().setSystemUiVisibility(0x10);
        setContentView(R.layout.view_video);
        String url = "https://youtu.be/dRwq9rAZGyA";
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUserAgentString("Desktop");
        webView.loadUrl(url);
    }
}
