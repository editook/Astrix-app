package com.android.software.astrix.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.software.astrix.R;

public class ViewProduct extends AppCompatActivity {
    /*example of dates*/
    private static final String[] products = new String[]{"Ola Futuro Maquina"};
    private static final String[] descriptions = new String[]{"Detergente liquido Ola futuro maquina"};
    private static final Integer[] images = new Integer[]{R.drawable.cuidadohogar};
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView= (ListView) findViewById(R.id.listvideos);
        createListView();
    }
    private void createListView() {
        final ListViewVideo listViewVideo = new ListViewVideo(this, images, descriptions, products);
        listView.setAdapter(listViewVideo);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewProduct.this, ViewVideo.class);
                startActivity(intent);
            }
        });
    }
    }
