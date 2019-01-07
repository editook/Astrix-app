package com.software.program.astrixsa.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.system.app.AppCategory;
import com.software.program.astrixsa.system.app.AppCategoryI;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.productmanager.ProductI;
import com.software.program.astrixsa.system.app.subcategorymanager.ElementSC;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MenuVideo extends AppCompatActivity implements View.OnClickListener {
    private SubCategoryI subCategoryI;
    private int indexProd,indexCat;
    private Button botonSugerencias;
    private static final String DIRECCION ="/Android/data/com.software.program.astrixsa/files/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista);
        //index list SubCategory
        Bundle parametros = this.getIntent().getExtras();
        String product =  parametros.getString("product");
        String category =  parametros.getString("category");
        indexProd = Integer.parseInt(product);
        indexCat = Integer.parseInt(category);
        createViews(indexProd,indexCat);
        botonSugerencias = findViewById(R.id.botonSugerencias);
        botonSugerencias.setOnClickListener(this);
    }

    private void updateList(List<ElementSC> elementSCS){
        int indexProduct = 0;
        for (ElementSC element: elementSCS){
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
        subCategoryI = category.getProduct(indexProduc);
        List<ElementSC> productIList = subCategoryI.getElements();
        updateList(productIList);
    }

    private void newWindows(final String idVideo) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MenuVideo.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_option_replay,null);
        Button button = view.findViewById(R.id.playvideo);
        String name = button.getText().toString();
        final ElementSC elementSC = subCategoryI.getElement(Integer.parseInt(idVideo));
        elementSC.setState(getFileSearchName(Integer.parseInt(idVideo)));
        button.setText(name+ elementSC.state());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(elementSC);
            }
        });
        Button button2 = view.findViewById(R.id.donwload);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadVideo(elementSC,idVideo);
            }

        });
        alert.setView(view);
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private boolean getFileSearchName(int id) {
        ElementSC element = subCategoryI.getElement(id);
        String URLS1 = DIRECCION+element.getFileName()+".mp4";
        String URLS2 = DIRECCION+element.getFileName()+".3gp";
        File dir1 = new File(Environment.getExternalStorageDirectory()+URLS1);
        File dir2 = new File(Environment.getExternalStorageDirectory()+URLS2);
        return dir1.getAbsoluteFile().isFile()|dir2.getAbsoluteFile().isFile();
    }

    private void downloadVideo(ElementSC elementSC, String idVideo) {

        Intent actividad = new Intent(MenuVideo.this, Download.class);
        actividad.putExtra("video", elementSC.getFileName());
        actividad.putExtra("category", indexCat+"");
        actividad.putExtra("product", indexProd+"");
        startActivity(actividad);

    }
    private void playVideo(ElementSC elementSC){
        Intent actividad = new Intent(MenuVideo.this, ViewVideoDownloaded.class);
        if(!elementSC.isDownload()){//is not donwload
            actividad = new Intent(MenuVideo.this, ViewVideoYoutube.class);
            actividad.putExtra("urlVideo", elementSC.getUrl());
        }
        else{
            actividad.putExtra("urlVideo", elementSC.getFileName());
        }
        startActivity(actividad);

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MenuVideo.this, SendMailView.class);
        startActivity(intent);
    }

}
