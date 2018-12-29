package com.software.program.astrixsa.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.model.AppCategory;
import com.software.program.astrixsa.model.AppCategoryI;
import com.software.program.astrixsa.model.CategoryI;
import com.software.program.astrixsa.model.ElementProduct;
import com.software.program.astrixsa.model.ProductI;

import java.util.List;

public class MenuVideo extends AppCompatActivity {
    private ProductI product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista);
        //index list Product
        Bundle parametros = this.getIntent().getExtras();
        String product =  parametros.getString("product");
        String category =  parametros.getString("category");
        int indexProd = Integer.parseInt(product);
        int indexCat = Integer.parseInt(category);
        createViews(indexProd,indexCat);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    private void updateList(List<ElementProduct>elementProducts){
        int indexProduct = 0;
        for (ElementProduct element:elementProducts){
            createItem(indexProduct,element.getImage());
            indexProduct++;
        }
    }
    private void createItem(int index, Integer ide){
        final int indexs = index;
        ImageView icon = findViewById(ide);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWindows(indexs +"");
            }
        });
    }
    private void createViews(int indexProduc, int indexCate) {
        AppCategoryI appCategory = new AppCategory();
        CategoryI category = appCategory.getCategory(indexCate);
        product = category.getProduct(indexProduc);
        List<ElementProduct> productIList = product.getElements();
        updateList(productIList);
    }

    @SuppressLint("SetTextI18n")
    private void newWindows(final String idVideo) {
        //Intent actividad = new Intent(MenuVideo.this, ViewVideoYoutube.class);
        //actividad.putExtra("AcConSecDia2134", idVideo);
        //startActivity(actividad);
        AlertDialog.Builder alert = new AlertDialog.Builder(MenuVideo.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_option_replay,null);
        Button button = view.findViewById(R.id.playvideo);
        String name = button.getText().toString();
        final ElementProduct elementProduct = product.getElement(Integer.parseInt(idVideo));
        button.setText(name+ elementProduct.state());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(elementProduct);
            }
        });
        Button button2 = view.findViewById(R.id.donwload);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MenuVideo.this,idVideo, Toast.LENGTH_SHORT).show();
            }
        });
        alert.setView(view);
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
    private void playVideo(ElementProduct elementProduct){
        Intent actividad = null;//change donwloader
        if(!elementProduct.isDownload()){//is not donwload
            actividad = new Intent(MenuVideo.this, ViewVideoYoutube.class);
        }
        actividad.putExtra("urlVideo", elementProduct.getUrl());
        startActivity(actividad);
    }

}
