package com.software.program.astrixsa.views;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.software.program.astrixsa.R;

import java.io.File;

public class ViewVideoDownloaded extends AppCompatActivity {
    private VideoView videoView;
    private static String FILE ="/storage/emulated/0/Astrix/";
    private static final String DIRECCION ="storage/emulated/0/Android/data/com.software.program.astrixsa/files/";
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_video_downloaded);
        videoView = findViewById(R.id.videodownloaded);
        Bundle parametros = this.getIntent().getExtras();
        filename = parametros.getString("urlVideo");
        playVideo(filename);

    }

    private void playVideo(String filename){
        Uri uri1 = Uri.parse(DIRECCION+filename+".mp4");
        Uri uri2 = Uri.parse(DIRECCION+filename+".3gp");


        String URLS1 = DIRECCION+filename+".mp4";
        File dir1 = new File(URLS1);
        if(dir1.isFile()){
            configurateVideo(uri1);
        }
        else{
            configurateVideo(uri2);
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
