package com.software.program.astrixsa.server;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.software.program.astrixsa.database.ConexionSQLiteHelper;
import com.software.program.astrixsa.database.Database;
import com.software.program.astrixsa.model.Category;
import com.software.program.astrixsa.model.FormatDownload;
import com.software.program.astrixsa.model.Item;
import com.software.program.astrixsa.model.Product;
import com.software.program.astrixsa.util.Util;
import com.software.program.astrixsa.views.ViewCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Server  extends AsyncTask<Void,Void,Void> {
    String data="";
    ViewCategory activity;
    List<Category> categories;
    List<Product> products;
    List<Item> items;
    List<FormatDownload> formats;
    String localVersion;
    public Server(ViewCategory activity){
        this.activity = activity;
        this.categories = new ArrayList<>();
        this.products = new ArrayList<>();
        this.items = new ArrayList<>();
        this.formats = new ArrayList<>();
        localVersion = Database.getCurrentDBVersion();
    }
    public JSONObject getJSONFromURL(String URL){
        JSONObject jsonObject = null;
        data = "";
        try{
            URL url = new URL(URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                if(line!=null){
                    data = data + line;
                }
            }
            jsonObject = new JSONObject(data);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    @Override
    protected Void doInBackground(Void... voids) {

        try {

            JSONObject versionJSON = getJSONFromURL("https://astrixserviceapp.000webhostapp.com/api/version/");
            if(versionJSON == null){
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(activity, "Error en la descarga", Toast.LENGTH_SHORT).show();
                    }
                });
                return null;
            }
            JSONArray jsonArray = versionJSON.getJSONArray("versions");
            JSONObject currentVersionJSON = (JSONObject) jsonArray.get(0);
            String versionName = currentVersionJSON.get("version").toString();
            boolean res = false;
            if(!versionName.equals(localVersion)){
                String direccion = Environment.getExternalStorageDirectory().toString()+"/"+Environment.DIRECTORY_DCIM+"/astrix/images/";
                File dir = new File(direccion);

                if(dir.isDirectory()){
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++)
                    {
                        new File(dir, children[i]).delete();
                    }
                }

                JSONObject jsonData = getJSONFromURL("https://astrixserviceapp.000webhostapp.com/api/all/");
                loadCategories(jsonData.getJSONArray("categories"));
                loadProducts(jsonData.getJSONArray("products"));
                loadItems(jsonData.getJSONArray("items"));
                loadFormats(jsonData.getJSONArray("formats"));
                localVersion = versionName;

                ConexionSQLiteHelper connection = new ConexionSQLiteHelper(activity.getApplicationContext(),Util.DB_NAME,null,1);
                connection.updateDatabase(localVersion,categories,products,items,formats);
                Database.downloadImages(activity);

                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(activity, "Actualizacion realizada con exito", Toast.LENGTH_LONG).show();
                    }
                });
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        activity.resetView();
                      //  activity.createListView();
                    }
                });
            }else{
                /*activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(activity, "No hay actualizaciones disponibles", Toast.LENGTH_SHORT).show();
                    }
                });*/
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        activity.make();
                    }
                });

            }



        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    protected void loadCategories(JSONArray jsonObjectCategories){
        try {
            for (int i = 0; i < jsonObjectCategories.length(); i++) {
                JSONObject categoryJson = (JSONObject) jsonObjectCategories.get(i);
                String categoryName = categoryJson.get("name").toString();
                String categoryImageUrl= categoryJson.get("image_url").toString();
                Category category = new Category(categoryName, categoryImageUrl);
                categories.add(category);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    protected void loadProducts(JSONArray jsonObjectProducts){
        try {
            for (int i = 0; i < jsonObjectProducts.length(); i++) {
                JSONObject productJson = (JSONObject) jsonObjectProducts.get(i);
                String productName = productJson.get("name").toString();
                String imageUrl = productJson.get("image_url").toString();
                String description = productJson.get("description").toString();
                int id_category = productJson.getInt("id_category");
                String titleProduct  = productJson.get("titleProduct").toString();

                Product product = new Product(id_category,productName,description,imageUrl,titleProduct);
                products.add(product);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    protected void loadItems(JSONArray jsonObjectItems){
        try {
            for (int i = 0; i < jsonObjectItems.length(); i++) {
                JSONObject itemJSON= (JSONObject) jsonObjectItems.get(i);
                String itemName = itemJSON.get("name").toString();
                String imageUrl = itemJSON.get("image_Url").toString();
                String urlYoutube = itemJSON.get("UrlYoutube").toString();
                String fileName = itemJSON.get("fileName").toString();
                String urlVideo = itemJSON.get("UrlVideo").toString();
                int id_product = itemJSON.getInt("id_Product");
                int position= itemJSON.getInt("position");
                Item item = new Item(id_product,position,urlYoutube,imageUrl,urlVideo,fileName);
                items.add(item);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    protected void loadFormats(JSONArray jsonObjectFormats){
        try {
            for (int i = 0; i < jsonObjectFormats.length(); i++) {
                JSONObject formatJSON= (JSONObject) jsonObjectFormats.get(i);
                String urlDownload = formatJSON.get("UrlDownload").toString();
                String formatName = formatJSON.get("formatName").toString();
                String resolution = formatJSON.get("resolution").toString();
                int id_item = formatJSON.getInt("id_Item");
                int position= formatJSON.getInt("position");
                FormatDownload format = new FormatDownload(id_item,position,formatName,resolution,urlDownload);
                formats.add(format);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onPostExecute(Void avoid){
        super.onPostExecute(avoid);

    }

}
