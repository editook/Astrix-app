package com.software.program.astrixsa.system.app.subcategorymanager;

import java.util.ArrayList;
import java.util.List;

public class ListFormatSave{
    private List<FileDownload>listUrlsDownload;
    public ListFormatSave(){
        listUrlsDownload = new ArrayList<>();

    }
    public void addUrl(FileDownload url){
        listUrlsDownload.add(url);
    }
    public List<FileDownload> getListUrlsDownload() {
        return listUrlsDownload;
    }
}
