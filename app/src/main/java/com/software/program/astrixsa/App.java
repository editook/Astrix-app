package com.software.program.astrixsa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.software.program.astrixsa.views.ViewCategory;


public class App extends AppCompatActivity {
    private static final int TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(App.this, ViewCategory.class);

                startActivity(intent);
                App.this.finish();
            }
        }, TIME);
    }
}
