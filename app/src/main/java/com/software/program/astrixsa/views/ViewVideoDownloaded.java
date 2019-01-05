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
    private static String FILE ="/storage/emulated/0/download/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video_downloaded);
        videoView = findViewById(R.id.videodownloaded);
        Bundle parametros = this.getIntent().getExtras();
        String filename = parametros.getString("urlVideo");
        playVideo(filename);

    }
    private void playVideo(String filename){
        String URLS1 = FILE+filename+".mp4";
        String URLS2 = FILE+filename+".3gp";
        Uri uri1 = Uri.parse(URLS1);
        Uri uri2 = Uri.parse(URLS2);
        int e = 1;
        try {
            configurateVideo(uri2);

        }
        catch (Exception ex){
            e = 0;
        }
        if(e==0){
            configurateVideo(uri1);
        }

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
