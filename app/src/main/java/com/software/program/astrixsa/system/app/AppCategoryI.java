package com.software.program.astrixsa.system.app;

import com.software.program.astrixsa.system.app.categorymanager.CategoryI;

import java.util.List;

public interface AppCategoryI {
    void addCategory(CategoryI category);
    List<CategoryI> getListCategories();
    CategoryI getCategory(int index);
}
