package com.software.program.astrixsa.model;

public class Item {


    private int idProduct;
    private int position;
    private String URLYoutube;
    private String imageURL;
    private String URLVideo;
    private String fileName;

    public Item(int idProduct, int position, String URLYoutube, String imageURL, String URLVideo, String fileName) {
        this.idProduct   = idProduct;
        this.position    = position;
        this.URLYoutube  = URLYoutube;
        this.imageURL    = imageURL;
        this.URLVideo    = URLVideo;
        this.fileName    = fileName;
    }
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getURLYoutube() {
        return URLYoutube;
    }

    public void setURLYoutube(String URLYoutube) {
        this.URLYoutube = URLYoutube;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getURLVideo() {
        return URLVideo;
    }

    public void setURLVideo(String URLVideo) {
        this.URLVideo = URLVideo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
