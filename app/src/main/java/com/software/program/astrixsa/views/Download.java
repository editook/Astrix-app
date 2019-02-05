package com.software.program.astrixsa.views;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.system.app.AppCategory;
import com.software.program.astrixsa.system.app.AppCategoryI;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.subcategorymanager.ElementSC;
import com.software.program.astrixsa.system.app.subcategorymanager.FileDownload;
import com.software.program.astrixsa.system.app.subcategorymanager.ListFormatSave;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;

import java.io.File;
import java.util.List;

public class Download extends Activity {
    private LinearLayout mainLayout;
    private int indexCat,indexProd;
    private List<FileDownload>listUrlsDownload;
    private ElementSC elementSC;
    private String DIRECCION ="storage/emulated/0/Android/data/com.software.program.astrixsa/files/";
    //private static final String DIRECCION ="storage/emulated/0/Android/data/com.software.program.astrixsa/files/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacio);
        mainLayout = findViewById(R.id.main_layout);
        Bundle parametros = this.getIntent().getExtras();
        String video = parametros.getString("video");
        String category = parametros.getString("category");
        String product = parametros.getString("product");
        if (category != null) {
            indexCat =Integer.parseInt(category);
        }
        if (product != null) {
            indexProd=Integer.parseInt(product);
        }
        AppCategoryI appCategory = new AppCategory();
        CategoryI categoryI = appCategory.getCategory(indexCat);
        SubCategoryI productI = categoryI.getProduct(indexProd);
        List<ElementSC> elements = productI.getElements();
        for (ElementSC e:elements){
            if(e.getFileName().equals(video)){
                elementSC = e;
                ListFormatSave aux = elementSC.getFormatDownload();
                listUrlsDownload = aux.getListUrlsDownload();
             break;
            }
        }
        getYoutubeDownloadUrl();
    }

    private void getYoutubeDownloadUrl() {
        for (FileDownload fileDownload: listUrlsDownload){
            addButtonToMainLayout(fileDownload);
        }
    }

    private void addButtonToMainLayout(final FileDownload fileDownload) {
        String btnText;
        btnText = fileDownload.getUrlFormat();
        Button btn = new Button(this);
        btn.setText(btnText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFileSearchName();
                downloadFromUrl(fileDownload.getUrlFile(), elementSC.getFileName(),
                        elementSC.getFileName() + "."+fileDownload.getFormat());
                finish();
            }
        });
        mainLayout.addView(btn);
    }
    private void removeFileSearchName() {
        DIRECCION = Environment.getExternalStorageDirectory().toString()+"/"+Environment.DIRECTORY_DCIM+"/astrix/videos/";
        String URLS1 = DIRECCION+elementSC.getFileName()+".mp4";
        String URLS2 = DIRECCION+elementSC.getFileName()+".3gp";

//        String URLS1 = DIRECCION+elementSC.getFileName()+".mp4";
//        String URLS2 = DIRECCION+elementSC.getFileName()+".3gp";
        File dir1 = new File(URLS1);
        File dir2 = new File(URLS2);
        if(dir1.delete()|dir2.delete()){
            Toast.makeText(Download.this,"Modificando: "+elementSC.getFileName(),Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Download.this,"Descargando: "+elementSC.getFileName(),Toast.LENGTH_LONG).show();
        }
    }
    private void downloadFromUrl(String youtubeDlUrl, String downloadTitle, String fileName) {
        downloadFile(this,youtubeDlUrl,fileName);
    }

    public void downloadFile(final Activity activity, final String url, final String fileName) {
        try {
            if (url != null && !url.isEmpty()) {

                Uri uri = Uri.parse("https://astrixserviceapp.000webhostapp.com/"+url);

                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//                request.setAllowedOverRoaming(false);
//                request.setTitle("Descargando video astrix");
//                String fileNameWithFormat = fileName.split("/")[1];
//                request.setDescription("Nombre de archivo: "+fileNameWithFormat);
               // request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setAllowedOverRoaming(false);

                request.setTitle("Descargando video Astrix");
                String fileNameWithFormat = fileName.split("videos/")[0];
                request.setDescription("Nombre de archivo: "+fileNameWithFormat);
                //request.setDescription("Astrix2");
                request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, "/astrix/videos/"  + "/" + fileName);

                DownloadManager downloadManager =(DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
            }
        } catch (IllegalStateException e) {
            Toast.makeText(activity, "La version no es compatible con su dispositivo\nfavor enviar comentario", Toast.LENGTH_SHORT).show();
        }
    }


}
