package com.android.software.astrix.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.software.astrix.R;
/**
 * Created by user on 29/11/2018.
 */

public class StartApp extends AppCompatActivity {
    /*example of dates*/
    private static final String[] videos = new String[]{"video name1", "video name2", "video name3", "video 4", "video 5"};
    private static final String[] descriptions = new String[]{"descriptions name1", "descriptions name2", "descriptions name3", "descriptions 4", "descriptions 5"};
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

                if (selected == position) {
                    listView.getChildAt(position).setBackgroundColor(Color.WHITE);
                    selected = -1;
                } else {
                    selected = position;
                    if (selected != -1) {
                        listView.getChildAt(selected).setBackgroundColor(Color.WHITE);
                    }
                    listView.getChildAt(position).setBackgroundColor(Color.LTGRAY);
                }
            }
        });

    }
}
