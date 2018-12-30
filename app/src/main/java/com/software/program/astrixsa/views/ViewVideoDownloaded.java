package com.software.program.astrixsa.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.MediaController;
import android.widget.VideoView;

import com.software.program.astrixsa.R;

public class ViewVideoDownloaded extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video_downloaded);
        videoView = findViewById(R.id.videodownloaded);
        //String urlVideo = "/storage/extSdCard/astrix/Kudai.mp4";
        Bundle parametros = this.getIntent().getExtras();
        String filename = parametros.getString("urlVideo");
        String URLS = "/storage/emulated/0/astrix/"+filename+".mp4";
        Uri uri = Uri.parse(URLS);
        configurateVideo(uri);
    }

    private void configurateVideo(Uri uri) {
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
            super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
