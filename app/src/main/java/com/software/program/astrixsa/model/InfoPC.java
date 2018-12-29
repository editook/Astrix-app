package com.software.program.astrixsa.model;

public abstract class InfoPC implements Information{
    private String name,description;
    private Integer image;
    public InfoPC(String name, String description, Integer image){
        this.name = name;
        this.description = description;
        this.image = image;
    }
    @Override
    public Integer getImage(){
        return image;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescripcion() {
        return description;
    }
}
