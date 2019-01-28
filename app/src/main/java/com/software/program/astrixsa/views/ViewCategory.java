package com.software.program.astrixsa.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.database.ConexionSQLiteHelper;
import com.software.program.astrixsa.system.app.AppCategory;
import com.software.program.astrixsa.system.app.AppCategoryI;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.productmanager.Product;

import java.util.List;

public class ViewCategory extends AppCompatActivity {
    private String[] products;
    private String[] descriptions;
    private Integer[] images;
    private ListView listView;
    private AppCategoryI appCategory;
    private ConexionSQLiteHelper connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        connection = new ConexionSQLiteHelper(getApplicationContext(),"basedatos",null,1);
        insertarconsultar();
        listView = findViewById(R.id.listvideos);
        appCategory = new AppCategory();
        createListView();
    }
    public void insertarconsultar(){

        SQLiteDatabase sqlEscritura = connection.getWritableDatabase();

        sqlEscritura.execSQL("insert into Category (name) values ('Joalin')");
        sqlEscritura.execSQL("insert into Category (name) values ('Manolo')");
        sqlEscritura.execSQL("insert into Category (name) values ('Eustaquio')");
        sqlEscritura.close();



    }
    private void updateList(List<CategoryI>categories){
        int size = categories.size();
        products = new String[size];
        descriptions = new String[size];
        images = new Integer[size];
        int indexCategory = 0;
        SQLiteDatabase sqlLectura = connection.getReadableDatabase();
        String [] campos = {"name"};
        Cursor cursor = sqlLectura.query("Category",campos,null,null,null,null,null);
        cursor.moveToFirst();

        String myyy = "";
        do{
            String actual = cursor.getString(0);
            myyy+=actual+" ";

        }
        while(cursor.moveToNext());


        Toast.makeText(this,myyy, Toast.LENGTH_LONG).show();


        cursor.close();

        for (CategoryI category:categories){
            Product info = (Product) category;
            products[indexCategory] = info.getName();
            descriptions[indexCategory] = info.getDescripcion();
            images[indexCategory] = info.getImage();
            indexCategory++;
        }
    }
    private void createListView() {
        final List<CategoryI> categoryIList = appCategory.getListCategories();
        updateList(categoryIList);
        final ListAdapter listAdapter = new ListAdapter(this, images, descriptions, products);
        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewCategory.this, ViewProduct.class);
                intent.putExtra("category", position+"");
                startActivity(intent);
            }
        });

    }
}
