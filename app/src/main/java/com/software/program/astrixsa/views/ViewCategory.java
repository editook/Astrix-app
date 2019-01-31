package com.software.program.astrixsa.views;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.database.ConexionSQLiteHelper;
import com.software.program.astrixsa.system.app.AppCategory;
import com.software.program.astrixsa.system.app.AppCategoryI;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.productmanager.Product;

import java.util.List;

public class ViewCategory extends AppCompatActivity {
    private String[] products;
    private String[] descriptions;
    private String[] images;
    private ListView listView;
    private AppCategoryI appCategory;
    private ConexionSQLiteHelper connection;
    private Button buttonUpdate;
    private TextView textViewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        listView = findViewById(R.id.listvideos);
        appCategory = new AppCategory();
        createListView();
        createButtonUpdate();
        downloadFile(this,"https://astrixserviceapp.000webhostapp.com/images/category1.png","category1");

    }
    private boolean getStatusData(){
        return false;
    }
    private void createButtonUpdate(){

        textViewUpdate = findViewById(R.id.statusVersion);
        buttonUpdate = findViewById(R.id.buttonupdate);
        SharedPreferences sp = getSharedPreferences("status", 0);
        final SharedPreferences.Editor editor = sp.edit();
        boolean firstRun = sp.getBoolean("first_run",false);
        boolean state = false;
        if (!firstRun) {
            state = getStatusData();
            editor.putBoolean("first_run",state).apply();
        }
        if(!state){

            textViewUpdate.setVisibility(View.VISIBLE);
            buttonUpdate.setVisibility(View.VISIBLE);
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if(isNetDisponible()){
                        editor.putBoolean("first_run",true).apply();
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Actualinzando...", Toast.LENGTH_SHORT);
                        toast1.show();

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
            });
        }
        else{
            textViewUpdate.setVisibility(View.INVISIBLE);
            buttonUpdate.setVisibility(View.INVISIBLE);
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
    public void downloadFile(final Activity activity, final String urls, final String fileName) {
        try {
            if (urls != null && !urls.isEmpty()) {
                Uri Download_Uri = Uri.parse(urls);

                DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setVisibleInDownloadsUi(true);

                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/astrix/"  + "/" + "image" + ".png");

                DownloadManager dm = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
                dm.enqueue(request);
            }
        } catch (IllegalStateException e) {

        }
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
