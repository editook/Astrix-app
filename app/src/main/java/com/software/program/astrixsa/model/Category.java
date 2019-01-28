package com.software.program.astrixsa.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Category {


    private int id;
    public String name;
    public String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String getName(){
        return name;
    }

    private void setName(String name){
        this.name = name;
    }

    private String getImage(){
        return image;
    }

    private void setImage(String image){
        this.image = image;
    }
}