package com.software.program.astrixsa.model;

public class Product {
    private int     id;
    private int     idCategory;
    private String  name;
    private String  description;
    private String  image;



    private String  titleProduct;

    public Product (int idCategory, String name, String description,String image, String titleProduct){
        this.idCategory  = idCategory;
        this.name        = name;
        this.description = description;
        this.image       = image;
        this.titleProduct       = titleProduct;
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
    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }
}
