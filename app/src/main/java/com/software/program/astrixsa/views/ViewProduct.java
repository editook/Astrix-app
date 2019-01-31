package com.software.program.astrixsa.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.system.app.AppCategory;
import com.software.program.astrixsa.system.app.AppCategoryI;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.productmanager.Product;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;

import java.util.List;

public class ViewProduct extends AppCompatActivity {
    /*example of dates*/
    private String[] products;
    private String[] descriptions;
    private Integer[] images;
    private ListView listView;
    private Button botonSugerencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView = findViewById(R.id.listvideos);
        //index list SubCategory
        Bundle parametros = this.getIntent().getExtras();
        String product =  parametros.getString("category");
        int index = Integer.parseInt(product);
        createListView(index);
      //  ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void updateList(List<SubCategoryI>productsList){
        int size = productsList.size();
        products = new String[size];
        descriptions = new String[size];
        images = new Integer[size];
        int indexProduct = 0;
        for (SubCategoryI product:productsList){
            Product info = (Product) product;
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
        List<SubCategoryI> subCategoryIList = category.getProducts();
        updateList(subCategoryIList);

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
