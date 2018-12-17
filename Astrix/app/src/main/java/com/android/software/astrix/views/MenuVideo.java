package com.android.software.astrix.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.android.software.astrix.R;

public class MenuVideo extends AppCompatActivity {
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
        ImageView quinto = (ImageView) findViewById(R.id.quinto);
        quinto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("4");
            }
        });
    }

    private void newWindows(String idVideo) {
        Intent actividad = new Intent(MenuVideo.this, ViewVideoYoutube.class);
        actividad.putExtra("AcConSecDia2134", idVideo);
        startActivity(actividad);
    }

    private void cuarto() {
        ImageView cuarto = (ImageView) findViewById(R.id.cuarto);
        cuarto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("3");
            }
        });
    }

    private void tercero() {
        ImageView tercero = (ImageView) findViewById(R.id.tercero);
        tercero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("2");
            }
        });
    }

    private void segundo() {
        ImageView segundo = (ImageView) findViewById(R.id.segundo);
        segundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("1");
            }
        });
    }

    private void primero() {
        ImageView primero = (ImageView) findViewById(R.id.primero);
        primero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows("0");
            }
        });
    }

}
