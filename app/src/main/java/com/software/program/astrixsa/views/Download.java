package com.software.program.astrixsa.views;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
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
        btnText = fileDownload.getUrlFormat()+ "p";
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
        String URLS1 = "/download/"+elementSC.getFileName()+".mp4";
        String URLS2 = "/download/"+elementSC.getFileName()+".3gp";
        File dir1 = new File(Environment.getExternalStorageDirectory()+URLS1);
        File dir2 = new File(Environment.getExternalStorageDirectory()+URLS2);
        if(dir1.delete() || dir2.delete()){
            Toast.makeText(Download.this,"Modificando: "+elementSC.getFileName(),Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Download.this,"Descargando: "+elementSC.getFileName(),Toast.LENGTH_LONG).show();
        }
    }
    private void downloadFromUrl(String youtubeDlUrl, String downloadTitle, String fileName) {
        Uri uri = Uri.parse(youtubeDlUrl);
        //Uri uri = Uri.parse("http://eduardperez.000webhostapp.com/video/1_1.mp4");

        //DownloadManager.Request request = new DownloadManager.Request(uri);
        //request.setTitle(downloadTitle);
        //request.allowScanningByMediaScanner();

        //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //request.setDestinationInExternalPublicDir("/astrix/", fileName);
        //DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //manager.enqueue(request);

        DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        String s = Environment.getExternalStorageDirectory()+"";
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalPublicDir(s ,fileName);
        request.setTitle(downloadTitle);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long ref = downloadManager.enqueue(request);

    }

}
