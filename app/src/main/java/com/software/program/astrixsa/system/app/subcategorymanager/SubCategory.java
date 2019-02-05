package com.software.program.astrixsa.system.app.subcategorymanager;

import com.software.program.astrixsa.system.app.productmanager.Product;

import java.util.ArrayList;
import java.util.List;

public class SubCategory extends Product implements SubCategoryI {
    private List<ElementSC> elementSCList;
    private String titleProduct;
    public SubCategory(String nameProduct, String description, String image, String titleProduct){
        super(nameProduct,description, image);
        this.titleProduct = titleProduct;
        elementSCList = new ArrayList<>();
    }
    @Override
    public String getTitleProduct(){
        return titleProduct;
    }

    @Override
    public void addElement(ElementSC elementSC) {
        elementSCList.add(elementSC);
    }

    @Override
    public List<ElementSC> getElements() {
        return elementSCList;
    }

    @Override
    public void setURl(String url, int index) {
        ElementSC elementSC = elementSCList.get(index);
        elementSC.setUrl(url);
        elementSCList.set(index, elementSC);

    }
    @Override
    public ElementSC getElement(int index){
        if(index>=0 && index < elementSCList.size()){
            return elementSCList.get(index);
        }
        return null;
    }

    @Override
    public void addElements(List<ElementSC> elements) {
        for(ElementSC elementSC : elements){
            elementSCList.add(elementSC);
        }
    }

}
