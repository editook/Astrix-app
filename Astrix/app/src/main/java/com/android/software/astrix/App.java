package com.android.software.astrix;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.software.astrix.views.StartApp;
import com.android.software.astrix.views.ViewProduct;

import java.util.ArrayList;
import java.util.Arrays;

public class App extends AppCompatActivity {
    private static int TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(App.this, ViewProduct.class);

                startActivity(intent);
                App.this.finish();
            }
        }, TIME);
    }
}
