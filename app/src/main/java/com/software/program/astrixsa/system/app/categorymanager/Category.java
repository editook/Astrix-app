package com.software.program.astrixsa.system.app.categorymanager;

import com.software.program.astrixsa.system.app.productmanager.Product;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;

import java.util.ArrayList;
import java.util.List;

public class Category extends Product implements CategoryI {
    private List<SubCategoryI> listProducts;

    public Category(String nameCategory, String image){
        super(nameCategory,"", image);
        listProducts = new ArrayList<>();
    }

    @Override
    public void addProduct(SubCategoryI categoryI) {
        listProducts.add(categoryI);
    }

    @Override
    public List<SubCategoryI> getProducts() {
        return listProducts;
    }
    @Override
    public SubCategoryI getProduct(int index) {
        if(index>=0 && index < listProducts.size()){
            return listProducts.get(index);
        }
        return null;
    }
}
