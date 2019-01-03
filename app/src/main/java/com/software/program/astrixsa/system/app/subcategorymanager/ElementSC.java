package com.software.program.astrixsa.system.app.subcategorymanager;

import android.util.Log;

import com.software.program.astrixsa.system.app.productmanager.stateVideo;

public class ElementSC {
    private String url;
    private Integer image;
    private Enum state;
    private String fileName;
    public ElementSC(String url, Integer image){
        this.url = url;
        this.image = image;
        state = stateVideo.EN_LINEA;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return fileName;
    }
    public Integer getImage(){
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
