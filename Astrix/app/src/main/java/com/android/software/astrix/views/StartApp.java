package com.android.software.astrix.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.software.astrix.App;
import com.android.software.astrix.R;
/**
 * Created by user on 29/11/2018.
 */

public class StartApp extends AppCompatActivity {
    /*example of dates*/
    private static final String[] videos = new String[]{"Manchas Profundas",
                                                        "Oxipoder Quitamanchas",
                                                        "Sistema Blanqueador",
                                                        "Medio ambiente",
                                                        "Anti bacterial"};
    private static final String[] descriptions = new String[]{"Quita todas las manchas",
                                                              "Oxipoder, la nueva tecnologia",
                                                              "Blanquea tu ropa a mas no poder!",
                                                              "Amigable con el medio ambiente",
                                                              "Elimina el 99.9% de las bacterias"};
    private static final Integer[] images = new Integer[]{R.drawable.name1, R.drawable.name2, R.drawable.name3, R.drawable.name4, R.drawable.name5};
    private ListView listView;
    private int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView= (ListView) findViewById(R.id.listvideos);
        createListView();
    }

    private void createListView() {
        final ListViewVideo listViewVideo = new ListViewVideo(this, images, descriptions, videos);
        listView.setAdapter(listViewVideo);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(StartApp.this, ViewVideo.class);

                startActivity(intent);
            }
        });

    }
}
