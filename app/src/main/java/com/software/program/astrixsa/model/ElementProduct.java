package com.software.program.astrixsa.model;

import android.util.Log;

public class ElementProduct {
    private String url;
    private Integer image;
    private Enum state;
    public ElementProduct(String url,Integer image){
        this.url = url;
        this.image = image;
        state = stateVideo.EN_LINEA;
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

    public void setUrl(String url){
        String string = url.substring(0,4);
        state = string.equals("https")?stateVideo.EN_LINEA:stateVideo.DESCARGADO;
        if(string.equals("https")){
            Log.i("salida","comparo bien");
        }
        this.url = url;
    }
}
