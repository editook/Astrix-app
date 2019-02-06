package com.software.program.astrixsa.views;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.program.astrixsa.App;
import com.software.program.astrixsa.R;
import com.software.program.astrixsa.database.ConexionSQLiteHelper;
import com.software.program.astrixsa.database.Database;
import com.software.program.astrixsa.server.Server;
import com.software.program.astrixsa.system.app.AppCategory;
import com.software.program.astrixsa.system.app.AppCategoryI;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.productmanager.Product;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;
import com.software.program.astrixsa.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ViewCategory extends AppCompatActivity {
    private String[] products;
    private String[] descriptions;
    private String[] images;
    private ListView listView;
    private AppCategoryI appCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView        = findViewById(R.id.listvideos);
       metodo();
    }
    private void metodo(){
        if(isStoragePermissionGranted()){
            askIfConnectedAndUpdate();
        }
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                askIfConnectedAndUpdate();
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
    private void askIfConnectedAndUpdate(){
        make();
        if(isNetDisponible()){
            Server s = new Server(this);
            s.execute();
        }
        else{
            if(isOnlineNet()){
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "No tiene datos suficientes para poder realizar la actualización", Toast.LENGTH_SHORT);
                toast1.show();
            }
            else{
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Sin Acceso a internet", Toast.LENGTH_SHORT);
                toast1.show();
            }

        }


    }
    public void make(){
        if(Database.isDownloaded()){
            appCategory = new AppCategory();
            createListView();
        }
    }
    public void resetView(){
        Intent i = new Intent(ViewCategory.this, App.class);
        startActivity(i);
    }
    private boolean getStatusData(){
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                metodo();

        }else{
            Toast.makeText(getApplicationContext(),
                    "Tiene que proporcionar permisos a la aplicacion", Toast.LENGTH_LONG).show();
            metodo();
        }
    }

    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean isNetDisponible() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    private void updateList(List<CategoryI>categories){

        int size = categories.size();
        products = new String[size];
        descriptions = new String[size];
        images = new String[size];
        int indexCategory = 0;

        for (CategoryI category:categories){
            Product info = (Product) category;
            products[indexCategory] = info.getName();
            descriptions[indexCategory] = info.getDescripcion();
            images[indexCategory] = info.getImage();
            indexCategory++;
        }
    }
    public void createListView() {
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

    public void downloadFile2(final Activity activity, final String url, final String fileName) {
        try {
            if (url != null && !url.isEmpty()) {
                Uri uri = Uri.parse(url);

                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setTitle("GadgetSaint Downloading " + "Sample" + ".png");
                request.setDescription("Downloading " + "Sample" + ".png");
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, "/astrix/images/"  + "/" + fileName + ".png");
                DownloadManager downloadManager =(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
            }
        } catch (IllegalStateException e) {
            Toast.makeText(activity, "La version no es compatible con su dispositivo\nfavor enviar comentario", Toast.LENGTH_SHORT).show();
        }
    }


}
