package com.software.program.astrixsa.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.model.AppCategory;
import com.software.program.astrixsa.model.AppCategoryI;
import com.software.program.astrixsa.model.CategoryI;
import com.software.program.astrixsa.model.InfoPC;
import com.software.program.astrixsa.model.ProductI;

import java.util.List;

public class ViewProduct extends AppCompatActivity {
    /*example of dates*/
    private String[] products;
    private String[] descriptions;
    private Integer[] images;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView = findViewById(R.id.listvideos);
        //index list Product
        Bundle parametros = this.getIntent().getExtras();
        String product =  parametros.getString("category");
        int index = Integer.parseInt(product);
        createListView(index);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    private void updateList(List<ProductI>productsList){
        int size = productsList.size();
        products = new String[size];
        descriptions = new String[size];
        images = new Integer[size];
        int indexProduct = 0;
        for (ProductI product:productsList){
            InfoPC info = (InfoPC) product;
            products[indexProduct] = info.getName();
            descriptions[indexProduct] = info.getDescripcion();
            images[indexProduct] = info.getImage();
            indexProduct++;
        }
    }
    private void createListView(int index) {
        final int valueIndexProduct = index;
        AppCategoryI appCategory = new AppCategory();
        CategoryI category = appCategory.getCategory(index);
        List<ProductI> productIList = category.getProducts();
        updateList(productIList);

        final ListAdapter listAdapter = new ListAdapter(this, images, descriptions, products);
        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewProduct.this, MenuVideo.class);
                intent.putExtra("category", valueIndexProduct+"");
                intent.putExtra("product", position+"");
                startActivity(intent);
            }
        });
    }
}
