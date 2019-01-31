package com.software.program.astrixsa.system.app.subcategorymanager;

import android.util.Log;

import com.software.program.astrixsa.system.app.productmanager.stateVideo;

import java.util.ArrayList;
import java.util.List;

public class ElementSC {
    private String url;
    private String image;
    private Enum state;
    private String fileName;
    private ListFormatSave listFormatSave;
    public ElementSC(String url, String image){
        this.url = url;
        this.image = image;
        state = stateVideo.EN_LINEA;
    }
    public ListFormatSave getFormatDownload(){
        return listFormatSave;
    }
    public void setListFormatSave(ListFormatSave listFormatSave){
        this.listFormatSave = listFormatSave;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return fileName;
    }
    public String getImage(){
        return image;
    }

    public String getUrl() {
        return url;
    }
    public boolean isDownload(){
        return state==stateVideo.DESCARGADO;
    }
    public String state(){
        return state == stateVideo.EN_LINEA? " ( En Linea ) ":" ( Descargado ) ";
    }
    public void setState(boolean request){//descargado == true
        state = request?stateVideo.DESCARGADO:stateVideo.EN_LINEA;
    }
    public void setUrl(String url){
        String string = url.substring(0,4);
        state = string.equals("https")?stateVideo.EN_LINEA:stateVideo.DESCARGADO;
        this.url = url;
    }
}
