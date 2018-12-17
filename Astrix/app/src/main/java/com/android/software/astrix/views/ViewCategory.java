package com.android.software.astrix.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.software.astrix.R;

public class ViewCategory extends AppCompatActivity {
    /*example of dates*/
    private static final String[] products = new String[]{"  LAVADO Y CUIDADO DE LA ROPA"};
    private static final String[] descriptions = new String[]{""};
    private static final Integer[] images = new Integer[]{R.drawable.categoria};
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView = (ListView) findViewById(R.id.listvideos);
        createListView();
    }

    private void createListView() {
        final ListAdapter listAdapter = new ListAdapter(this, images, descriptions, products);
        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewCategory.this, ViewProduct.class);
                startActivity(intent);
            }
        });

    }
}
