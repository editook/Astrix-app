package com.software.program.astrixsa.views;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.system.app.AppCategoryI;
import com.software.program.astrixsa.views.YoutubeExtractor.VideoMeta;
import com.software.program.astrixsa.views.YoutubeExtractor.YouTubeExtractor;
import com.software.program.astrixsa.views.YoutubeExtractor.YtFile;

public class Download extends Activity {
    private LinearLayout mainLayout;
    private ProgressBar mainProgressBar;
    private int indexCat,indexProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacio);
        mainLayout = findViewById(R.id.main_layout);
        mainProgressBar =  findViewById(R.id.prgrBar);
        Bundle parametros = this.getIntent().getExtras();
        String ytLink = parametros.getString("urlVideo");
        String category = parametros.getString("category");
        String product = parametros.getString("product");
        indexCat =Integer.parseInt(category);
        indexProd=Integer.parseInt(product);
        ytLink = ytLink.replaceAll("embed/", "");
        ytLink = ytLink.substring(0,ytLink.length()-18);
        ytLink = ytLink.replaceAll("youtube.com/", "youtube.com/watch?v=");
        Log.d("salida",ytLink);
        getYoutubeDownloadUrl(ytLink);

    }

    @SuppressLint("StaticFieldLeak")
    private void getYoutubeDownloadUrl(String youtubeLink) {
        new YouTubeExtractor(this) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                mainProgressBar.setVisibility(View.GONE);
                if (ytFiles == null) {
                    finish();
                    return;
                }
                for (int i = 0, itag; i < ytFiles.size(); i++) {
                    itag = ytFiles.keyAt(i);
                    YtFile ytFile = ytFiles.get(itag);
                    if (ytFile.getFormat().getHeight() >= 144) {
                        addButtonToMainLayout(vMeta.getTitle(), ytFile);
                    }
                }
            }
        }.extract(youtubeLink, true, false);
    }

    @SuppressLint("ResourceAsColor")
    private void addButtonToMainLayout(final String videoTitle, final YtFile ytFrVideo) {
        // Display some buttons and let the user choose the format
        String btnText="";
        if(ytFrVideo.getFormat().getItag()==135){
            btnText = ytFrVideo.getFormat().getExt()+"( mute )\t\t"+ytFrVideo.getFormat().getHeight() + "p";
        }
        else{
            btnText = ytFrVideo.getFormat().getExt()+"\t\t\t\t"+ytFrVideo.getFormat().getHeight() + "p";
        }


        Button btn = new Button(this);
        btn.setText(btnText);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String filename;
                if (videoTitle.length() > 40) {
                    filename = videoTitle.substring(0, 40);
                } else {
                    filename = videoTitle;
                }
                filename = filename.replaceAll("[\\\\><\"|*?%:#/]", "");
                if (ytFrVideo.getFormat() != null) {
                    downloadFromUrl(ytFrVideo.getUrl(), videoTitle,
                            filename + ".mp4");
                    Toast.makeText(Download.this,"Descargando: "+videoTitle,Toast.LENGTH_LONG).show();
                }

                finish();
            }
        });
        mainLayout.addView(btn);
    }

    private void downloadFromUrl(String youtubeDlUrl, String downloadTitle, String fileName) {
        Uri uri = Uri.parse(youtubeDlUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(downloadTitle);
        request.allowScanningByMediaScanner();

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir("/astrix/", fileName);
        saveChangesData(fileName);
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
    private void saveChangesData(String filename){
        //serializar object to save changes
    }
}
