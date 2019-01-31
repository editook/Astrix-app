package com.software.program.astrixsa.dao;

import com.software.program.astrixsa.model.Category;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
@Dao
public interface CategoryDao {
    @Query("select * from Category")
    List<Category> getAllCategories();
}
