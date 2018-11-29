package com.android.software.astrix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.software.astrix.views.StartApp;

public class App extends Activity {

    private static int TIME =500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(App.this,StartApp.class);
                startActivity(intent);
                finish();
            }
        },TIME);
    }
}
