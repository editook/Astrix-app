package com.android.software.astrix;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.software.astrix.views.StartApp;

public class App extends AppCompatActivity {

    private static int TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.init_view);
      //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)
        //this.setContentView(R.layout.your_layout_name_here);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(App.this, StartApp.class);

                startActivity(intent);
                App.this.finish();
            }
        }, 2000);
    }

}
