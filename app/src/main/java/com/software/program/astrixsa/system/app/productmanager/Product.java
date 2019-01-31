package com.software.program.astrixsa.system.app.productmanager;

public abstract class Product implements ProductI {
    private String name,description;
    private String image;
    public Product(String name, String description, String image){
        this.name = name;
        this.description = description;
        this.image = image;
    }
    @Override
    public String getImage(){
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
