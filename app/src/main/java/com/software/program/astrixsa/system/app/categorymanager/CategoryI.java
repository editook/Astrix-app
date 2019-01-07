package com.software.program.astrixsa.system.app.categorymanager;

import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;

import java.util.List;

public interface CategoryI {
    void addProduct(SubCategoryI secction);
    List<SubCategoryI> getProducts();
    SubCategoryI getProduct(int index);
}
