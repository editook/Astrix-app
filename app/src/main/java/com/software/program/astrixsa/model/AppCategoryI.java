package com.software.program.astrixsa.model;

import java.util.List;

public interface AppCategoryI {
    void addCategory(CategoryI category);
    List<CategoryI> getListCategories();
    CategoryI getCategory(int index);
}
