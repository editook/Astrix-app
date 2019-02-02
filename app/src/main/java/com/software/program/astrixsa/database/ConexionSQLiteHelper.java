package com.software.program.astrixsa.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.software.program.astrixsa.model.Category;
import com.software.program.astrixsa.model.FormatDownload;
import com.software.program.astrixsa.model.Item;
import com.software.program.astrixsa.model.Product;
import com.software.program.astrixsa.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    private Context context;


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
      //  populateDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS "+Util.FORMATDOWNLOAD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Util.ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Util.PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Util.CATEGORY_TABLE);
        onCreate(db);
    }
    public void createTables(SQLiteDatabase db){
        db.execSQL(Util.CREATE_STATUS_TABLE);
        db.execSQL(Util.CREATE_CATEGORY_TABLE);
        db.execSQL(Util.CREATE_PRODUCT_TABLE);
        db.execSQL(Util.CREATE_ITEM_TABLE);
        db.execSQL(Util.CREATE_FORMATDOWNLOAD_TABLE);
        db.execSQL(Util.INSERT_INITIAL_STATUS);
        //db.close();
    }
    public void updateDatabase(String newVersion, List<Category> categoryList, List<Product> products, List<Item>items, List<FormatDownload>formats){
       SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+Util.FORMATDOWNLOAD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Util.ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Util.PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Util.CATEGORY_TABLE);
        db.execSQL(Util.CREATE_CATEGORY_TABLE);
        db.execSQL(Util.CREATE_PRODUCT_TABLE);
        db.execSQL(Util.CREATE_ITEM_TABLE);
        db.execSQL(Util.CREATE_FORMATDOWNLOAD_TABLE);
        db.close();
        insertCategories(categoryList);
        insertProducts(products);
        insertItems(items);
        insertFormats(formats);
        updateVersion(newVersion);
    }
    public void updateVersion(String newVersion){
        SQLiteDatabase db = this.getWritableDatabase();
        int isDownloaded = 1;
        db.execSQL("UPDATE STATUS SET VERSION = '"+newVersion+"', IS_DOWNLOADED= '"+isDownloaded+"' WHERE ID=1");
        db.close();
    }
    public void populateDatabase(){
        if(checkDataBase()){
            return;
        }

        ArrayList<Category> categories = new ArrayList<>();
        Category c1 = new Category(Util.CATEGORY1,"");
        Category c2 = new Category(Util.CATEGORY2,"");
        categories.add(c1);
        categories.add(c2);
        insertCategories(categories);

        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product(1,Util.PRODUCT1,Util.DESCRIPTION1,"");
        Product p2 = new Product(1,Util.PRODUCT2,Util.DESCRIPTION2,"");
        products.add(p1);
        products.add(p2);
        insertProducts(products);

        ArrayList<Item> items = new ArrayList<>();
        Item i1 = new Item(1,1,Util.url1, "","",Util.fileName1);
        Item i2 = new Item(1,2,Util.url2, "","",Util.fileName2);
        Item i3 = new Item(1,3,Util.url3, "","",Util.fileName3);
        Item i4 = new Item(1,4,Util.url4, "","",Util.fileName4);
        Item i5 = new Item(1,5,Util.url5, "","",Util.fileName5);
        Item i6 = new Item(1,6,Util.url6, "","",Util.fileName6);
        Item i7 = new Item(1,7,Util.url7, "","",Util.fileName7);
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        items.add(i5);
        items.add(i6);
        items.add(i7);
        insertItems(items);
  //      FileDownload format720_1 = new FileDownload("mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/1_1.mp4");//https://r1---sn-5h25gvcg-8j2e.googlevideo.com/videoplayback?sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&itag=22&ipbits=0&mm=31%2C29&mn=sn-5h25gvcg-8j2e%2Csn-bg0ezn7s&ms=au%2Crdu&source=youtube&mv=m&mime=video%2Fmp4&key=yt6&fvip=5&dur=89.861&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&initcwndbps=463750&txp=2211222&pl=24&mt=1546698354&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&ratebypass=yes&lmt=1544513492115151&ip=131.72.140.70&expire=1546720063&requiressl=yes&signature=AB1EE63D458C094D63B1C6A0657FFE8614CD4C11.DB3B0E4229191D56CCA40F0B8A50321C486DC9D0&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas");
//        FileDownload format360_1 = new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/1_2.mp4");//"https://r5---sn-vgqs7nlr.googlevideo.com/videoplayback?sparams=clen,dur,ei,expire,gir,id,ip,ipbits,itag,lmt,mime,mip,mm,mn,ms,mv,pl,ratebypass,requiressl,source&gir=yes&requiressl=yes&clen=4470948&key=cms1&txp=2211222&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&lmt=1544513478289018&ip=131.72.140.70&expire=1546720063&itag=18&ipbits=0&source=youtube&mime=video%2Fmp4&fvip=5&dur=89.861&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&pl=32&ratebypass=yes&signature=6BDD97D6501C1AA09F03490A0AC23A34685685FC.46C788CCF570EE81DE1B4F03F4D2D129430CADB2&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas&rm=sn-5h25gvcg-8j2e7e&req_id=7aabb580fbe9a3ee&redirect_counter=2&cm2rm=sn-bg0z77l&cms_redirect=yes&mip=2803:9400:f:9738:8515:95e:bec:5000&mm=34&mn=sn-vgqs7nlr&ms=ltu&mt=1546702490&mv=m");
//        FileDownload format240_1 = new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/1_3.3gp");//"https://r1---sn-5h25gvcg-8j2e.googlevideo.com/videoplayback?sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cexpire&gir=yes&requiressl=yes&clen=2418660&key=yt6&txp=2211222&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&lmt=1544513482196383&ip=131.72.140.70&expire=1546720063&itag=36&ipbits=0&mm=31%2C29&mn=sn-5h25gvcg-8j2e%2Csn-bg0ezn7s&source=youtube&mv=m&mime=video%2F3gpp&ms=au%2Crdu&fvip=5&dur=89.907&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&initcwndbps=463750&pl=24&mt=1546698354&signature=A148AC66AB37B3B094F5584D4BAED12754E021E5.40582F12CE5BD9B95F643F5382D74DA3F438918C&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas");
//
        FormatDownload f1 = new FormatDownload(1,1,"3gp","3gp\t\t\t\t240","http://eduardperezz.000webhostapp.com/video/1_3.3gp");
        FormatDownload f2 = new FormatDownload(1,1,"mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/1_2.mp4");
        FormatDownload f3 = new FormatDownload(1,1,"mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/1_1.mp4");
        ArrayList<FormatDownload>formats = new ArrayList<>();
        formats.add(f1);
        formats.add(f2);
        formats.add(f3);
        insertFormats(formats);
    }
    public void insertFormats(List<FormatDownload>formats){

        SQLiteDatabase sqlLectura = this.getReadableDatabase();
        Cursor cursor = sqlLectura.rawQuery(" SELECT * FROM formatdownload",null);

        int tamano = cursor.getCount();
        int tamanoFormats = formats.size();
        SQLiteDatabase sqlEscritura = this.getWritableDatabase();
        for(FormatDownload f : formats){
            int idItem = f.getIdItem();
            int position = f.getPosition();
            String formatName = f.getFormatDownload();
            String urlDownload = f.getUrlDownload();
            String resolution = f.getResolution();
            sqlEscritura.execSQL("insert into formatdownload (id_Item,position,formatname,resolution,urldownload) values ('"+idItem+"', '"+position+"', '"+formatName+"', '"+resolution+"', '"+urlDownload+"')");
        }
        sqlEscritura.close();
    }
    public void insertCategories(List<Category> categories){
        SQLiteDatabase sqlEscritura = this.getWritableDatabase();
        for(Category c : categories){
            String categoryName = c.getName();
            String categoryImage = c.getImage();
            sqlEscritura.execSQL("insert into Category (NAME ,IMAGE_URL) values ('"+categoryName+"', '"+categoryImage+"')");
        }
        sqlEscritura.close();

    }
    public void insertProducts(List<Product> products){
        SQLiteDatabase sqlEscritura = this.getWritableDatabase();


        for(Product p : products){
            int idCategory = p.getIdCategory();
            String productDescription = p.getDescription();
            String productName = p.getName();
            String productImage = p.getImage();
            sqlEscritura.execSQL("insert into Product (ID_CATEGORY, NAME, DESCRIPTION ,IMAGE_URL) values ('"+idCategory+"','"+productName+"', '"+productDescription+"','"+productImage+"')");
        }
        sqlEscritura.close();

    }

    public void insertItems(List<Item> items){
        SQLiteDatabase sqlEscritura = this.getWritableDatabase();

        for(Item i : items){
            int idProduct      = i.getIdProduct();
            int position       = i.getPosition();
            String urlYoutube  = i.getURLYoutube();
            String imageUrl    = i.getImageURL();
            String urlVideo    = i.getURLVideo();
            String fileName    = i.getFileName();

            sqlEscritura.execSQL("insert into Item (ID_PRODUCT, POSITION, URLYOUTUBE,URLVIDEO,IMAGE_URL,FILENAME) values ('"+idProduct+"','"+position+"', '"+urlYoutube+"','"+urlVideo+"','"+imageUrl+"','"+fileName+"')");

        }
        sqlEscritura.close();

    }
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = context.getDatabasePath(Util.DB_NAME).toString();

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }
}
