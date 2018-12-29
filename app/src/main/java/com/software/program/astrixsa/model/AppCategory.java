package com.software.program.astrixsa.model;

import com.software.program.astrixsa.date.LoadDate;

import java.util.ArrayList;
import java.util.List;

public class AppCategory implements AppCategoryI{
    private List<CategoryI> listCategory;
    public AppCategory(){
        listCategory = new ArrayList<>();
        addCategory(LoadDate.getCategory());
    }
    @Override
    public CategoryI getCategory(int index){
        if(index>=0 && index < listCategory.size()){
            return listCategory.get(index);
        }
        return null;
    }
    @Override
    public void addCategory(CategoryI category) {
        listCategory.add(category);
    }

    @Override
    public List<CategoryI> getListCategories() {
        return listCategory;
    }
}
