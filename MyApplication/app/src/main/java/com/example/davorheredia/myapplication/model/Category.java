package com.example.davorheredia.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    public int id_Categoria;

    public String nombre_Categoria;
}