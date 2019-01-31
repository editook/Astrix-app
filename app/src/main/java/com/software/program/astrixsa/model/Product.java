package com.software.program.astrixsa.model;

public class Product {
    private int     id;
    private int     idCategory;
    private String  name;
    private String  description;
    private String  image;

    public Product (int idCategory, String name, String description,String image){
        this.idCategory  = idCategory;
        this.name        = name;
        this.description = description;
        this.image       = image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }
}
