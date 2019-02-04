package com.software.program.astrixsa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.software.program.astrixsa.database.ConexionSQLiteHelper;
import com.software.program.astrixsa.database.Database;

import com.software.program.astrixsa.server.Server;
import com.software.program.astrixsa.views.ViewCategory;


public class App extends AppCompatActivity {
    private static final int TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_view);
        Database.initDatabase(this);
        showJSON();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(App.this, ViewCategory.class);

                startActivity(intent);
                App.this.finish();
            }
        }, TIME);
    }
    public void showJSON(){
        //Server s = new Server(this);
       //s.execute();
    }
}

