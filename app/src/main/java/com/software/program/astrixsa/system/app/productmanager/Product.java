package com.software.program.astrixsa.system.app.productmanager;

public abstract class Product implements ProductI {
    private String name,description;
    private Integer image;
    public Product(String name, String description, Integer image){
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
