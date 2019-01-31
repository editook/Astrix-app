package com.software.program.astrixsa.model;

public class FormatDownload {

    private int idItem;
    private int position;
    public String formatDownload;
    public String resolution;
    public String urlDownload;

    public FormatDownload(int idItem, int position, String formatDownload, String resolution, String urlDownload){
        this.idItem         = idItem;
        this.position       = position;
        this.formatDownload = formatDownload;
        this.resolution     = resolution;
        this.urlDownload    = urlDownload;

    }
    public int getIdItem() {
        return idItem;
    }

    public void setId(int id) {
        this.idItem = id;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getFormatDownload() {
        return formatDownload;
    }

    public void setFormatDownload(String formatDownload) {
        this.formatDownload = formatDownload;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getUrlDownload() {
        return urlDownload;
    }

    public void setUrlDownload(String urlDownload) {
        this.urlDownload = urlDownload;
    }
}
