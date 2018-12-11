package com.android.software.astrix.views;

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
    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList("https://mega.nz/#!XpZF3S7R!7iZOqcTxcvMzTvtMHKJ79C732R_shy8CVWZZTOR_sp4","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));

    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video);
        playVideo();

    }
    private void playVideo(){
        videoView = (VideoView) findViewById(R.id.videoView);
        final MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);


        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(Uri.parse(arrayList.get(index)));
        videoView.requestFocus();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                if (index++ == arrayList.size()) {
                    index = 0;
                    mp.release();

                } else {
                    videoView.setVideoURI(Uri.parse(arrayList.get(index)));
                    videoView.start();
                }


            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }
}
