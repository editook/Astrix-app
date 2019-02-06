package com.software.program.astrixsa.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.system.app.AppCategory;
import com.software.program.astrixsa.system.app.AppCategoryI;
import com.software.program.astrixsa.system.app.categorymanager.Category;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.productmanager.Product;
import com.software.program.astrixsa.system.app.subcategorymanager.ElementSC;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategory;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class MenuVideo extends AppCompatActivity implements View.OnClickListener {
    private SubCategoryI subCategoryI;
    private int indexProd,indexCat;
    private Button botonSugerencias;
    private  String DIRECCION ="";
    private TextView description;
    private static HashMap<Integer,Integer>has ;
    private static int [] arrayOfIds = {R.id.primero,
            R.id.segundo,
            R.id.tercero,
            R.id.cuarto,
            R.id.quinto,
            R.id.sexto,
            R.id.septimo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista);
        description = findViewById(R.id.textView);
        //index list SubCategory
        has = new HashMap<Integer,Integer>();
        poblarHasMap();


        Bundle parametros = this.getIntent().getExtras();
        String product =  parametros.getString("product");
        String category =  parametros.getString("category");
        indexProd = Integer.parseInt(product);
        indexCat = Integer.parseInt(category);
        createViews(indexProd,indexCat);
        botonSugerencias = findViewById(R.id.botonSugerencias);
        botonSugerencias.setOnClickListener(this);
    }
    private void poblarHasMap(){
        for(int i =0;i<arrayOfIds.length;i++){
            has.put(i+1,arrayOfIds[i]);
        }

    }
    private void updateList(List<ElementSC> elementSCS){


        for(int indexElements  = 0; indexElements< elementSCS.size();  indexElements++){
            ImageView icoI1 = findViewById(has.get(elementSCS.get(indexElements).getPosition()));
            createItem(icoI1,elementSCS.get(indexElements),elementSCS.get(indexElements).getImage());

        }

    }
    private void createItem(ImageView icon, ElementSC element, String url){
        if(url == null || url.isEmpty()){
            icon.setImageResource(R.drawable.white);
            return;
        }
        final ElementSC indexs = element;
        /*image refomat*/
        String nombreFoto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +"/astrix/"+url;
        Uri output = Uri.fromFile(new File(nombreFoto));
        InputStream is;
        try {
            is = getContentResolver().openInputStream(output);
            BufferedInputStream bis = new BufferedInputStream(is);
            Bitmap bitmap = BitmapFactory.decodeStream(bis);
            icon.setImageBitmap(bitmap);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newWindows(indexs);
                }
            });
        } catch (FileNotFoundException ignored) {}
        /*image refomat*/

    }
    private void createViews(int indexProduc, int indexCate) {
        AppCategoryI appCategory = new AppCategory();
        CategoryI category = appCategory.getCategory(indexCate);
        subCategoryI = category.getProduct(indexProduc);
        SubCategory subCategory = (SubCategory) subCategoryI;
        String name = subCategory.getName();
        String productDescription = subCategory.getTitleProduct();
        this.setTitle(name);
        description.setText(productDescription);
        List<ElementSC> productIList = subCategoryI.getElements();
        updateList(productIList);
    }

    private void newWindows(final ElementSC element) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MenuVideo.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_option_replay,null);
        Button button = view.findViewById(R.id.playvideo);
        String name = button.getText().toString();
        final ElementSC elementSC = element;
        elementSC.setState(getFileSearchName(elementSC));
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
                downloadVideo(elementSC,elementSC);
            }

        });
        alert.setView(view);
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private boolean getFileSearchName(ElementSC fileName) {
        DIRECCION = Environment.getExternalStorageDirectory().toString()+"/"+Environment.DIRECTORY_DCIM+"/astrix/videos/";
        String URLS1 = DIRECCION+fileName.getFileName()+".mp4";
        String URLS2 = DIRECCION+fileName.getFileName()+".3gp";
        File dir1 = new File(URLS1);
        File dir2 = new File(URLS2);
        return dir1.getAbsoluteFile().isFile()|dir2.getAbsoluteFile().isFile();
    }

    private void downloadVideo(ElementSC elementSC, ElementSC element) {

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
