package com.software.program.astrixsa.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.model.AppCategory;
import com.software.program.astrixsa.model.AppCategoryI;
import com.software.program.astrixsa.model.CategoryI;
import com.software.program.astrixsa.model.InfoPC;

import java.util.List;

public class ViewCategory extends AppCompatActivity {
    private String[] products;
    private String[] descriptions;
    private Integer[] images;
    private ListView listView;
    private AppCategoryI appCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView = findViewById(R.id.listvideos);
        appCategory = new AppCategory();
        createListView();
    }
    private void updateList(List<CategoryI>categories){
        int size = categories.size();
        products = new String[size];
        descriptions = new String[size];
        images = new Integer[size];
        int indexCategory = 0;
        for (CategoryI category:categories){
            InfoPC info = (InfoPC) category;
            products[indexCategory] = info.getName();
            descriptions[indexCategory] = info.getDescripcion();
            images[indexCategory] = info.getImage();
            indexCategory++;
        }
    }
    private void createListView() {
        final List<CategoryI> categoryIList = appCategory.getListCategories();
        updateList(categoryIList);
        final ListAdapter listAdapter = new ListAdapter(this, images, descriptions, products);
        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewCategory.this, ViewProduct.class);
                intent.putExtra("category", position+"");
                startActivity(intent);
            }
        });

    }
}
