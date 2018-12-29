package com.software.program.astrixsa.model;

import java.util.ArrayList;
import java.util.List;

public class Category extends InfoPC implements CategoryI{
    private List<ProductI> listProducts;

    public Category(String nameCategory, Integer image){
        super(nameCategory,"", image);
        listProducts = new ArrayList<>();
    }

    @Override
    public void addProduct(ProductI categoryI) {
        listProducts.add(categoryI);
    }

    @Override
    public List<ProductI> getProducts() {
        return listProducts;
    }
    @Override
    public ProductI getProduct(int index) {
        if(index>=0 && index < listProducts.size()){
            return listProducts.get(index);
        }
        return null;
    }
}
