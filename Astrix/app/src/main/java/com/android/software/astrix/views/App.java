package com.android.software.astrix.views;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.android.software.astrix.R;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class App extends AppCompatActivity {
    private ImageView primero,segundo,tercero,cuarto,quinto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista);

        primero();
        segundo();
        tercero();
        cuarto();
        quinto();

    }

    private void quinto() {
        quinto = (ImageView) findViewById(R.id.quinto);
        quinto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    newWindows("4");
            }
        });
    }
    private void newWindows(String idVideo){
        Intent actividad=new Intent(App.this,ViewVideo.class);
        actividad.putExtra("AcConSecDia2134",idVideo);
        startActivity(actividad);
    }
    private void cuarto() {
        cuarto = (ImageView) findViewById(R.id.cuarto);
        cuarto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("3");
            }
        });
    }

    private void tercero() {
        tercero = (ImageView) findViewById(R.id.tercero);
        tercero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("2");
            }
        });
    }

    private void segundo() {
        segundo = (ImageView) findViewById(R.id.segundo);
        segundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("1");
            }
        });
    }

    private void primero() {
        primero = (ImageView) findViewById(R.id.primero);
        primero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("0");
            }
        });
    }

}
