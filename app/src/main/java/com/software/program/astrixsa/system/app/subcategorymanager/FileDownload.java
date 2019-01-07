package com.software.program.astrixsa.system.app.subcategorymanager;

public class FileDownload {
    private String urlFile,urlFormat,format;
    public FileDownload(String format,String formatFile, String urlFile){
        this.urlFormat = formatFile;
        this.format = format;
        this.urlFile = urlFile;
    }
    public String getFormat(){
        return format;
    }
    public String getUrlFormat() {
        return urlFormat;
    }

    public String getUrlFile() {
        return urlFile;
    }



}
