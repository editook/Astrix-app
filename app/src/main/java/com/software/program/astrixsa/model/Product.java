package com.software.program.astrixsa.model;

import java.util.ArrayList;
import java.util.List;

public class Product extends InfoPC implements ProductI{
    private List<ElementProduct>elementProductList;
    public Product(String nameProduct, String description, Integer image){
        super(nameProduct,description, image);
        elementProductList = new ArrayList<>();
    }

    @Override
    public void addElement(ElementProduct elementProduct) {
        elementProductList.add(elementProduct);
    }

    @Override
    public List<ElementProduct> getElements() {
        return elementProductList;
    }

    @Override
    public void setURl(String url, int index) {
        ElementProduct elementProduct = elementProductList.get(index);
        elementProduct.setUrl(url);
        elementProductList.set(index,elementProduct);

    }
    @Override
    public ElementProduct getElement(int index){
        if(index>=0 && index < elementProductList.size()){
            return elementProductList.get(index);
        }
        return null;
    }

}
