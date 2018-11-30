package com.android.software.astrix.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.software.astrix.R;

/**
 * Created by user on 30/11/2018.
 */

public class ViewVideo extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video);
        playVideo();

    }
    private void playVideo(){
        WebView videoView =(WebView) findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri=Uri.parse("https://youtu.be/bSMZknDI6bg");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}
