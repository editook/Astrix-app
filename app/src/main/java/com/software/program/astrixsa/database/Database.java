package com.software.program.astrixsa.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.model.Category;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.subcategorymanager.ElementSC;
import com.software.program.astrixsa.system.app.subcategorymanager.FileDownload;
import com.software.program.astrixsa.system.app.subcategorymanager.ListFormatSave;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategory;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;
import com.software.program.astrixsa.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static ConexionSQLiteHelper connection;

    public static void initDatabase(Context context){
        connectDatabase(context);
    }
    public static void connectDatabase(Context context){
        connection = new ConexionSQLiteHelper(context,Util.DB_NAME,null,1);
        connection.populateDatabase();
    }

    public static List<CategoryI> getCategories(){
        SQLiteDatabase sqlLectura = connection.getReadableDatabase();
        String [] campos          = {Util.ID_FIELD,Util.NAME_FIELD,Util.IMAGE_URL_FIELD};
        Cursor cursor             = sqlLectura.query("Category",campos,null,null,null,null,null);

        List<CategoryI> categoryList = new ArrayList<>();
        cursor.moveToFirst();
        do{
            int id      = cursor.getInt(0);
            String name = cursor.getString(1);
            String url  = cursor.getString(2);
            CategoryI categoryI = getCategory(id,name,url);
            categoryList.add(categoryI);
        }
        while(cursor.moveToNext());
        cursor.close();
        return categoryList;
    }

    public static CategoryI getCategory(int id, String name, String URL){
        String lavadoYCuidadoRopa = name;
        String categoria1 = "/astrix/image/category1.png";
        String producto1  = "/astrix/image/p1.png";

        CategoryI category = new com.software.program.astrixsa.system.app.categorymanager.Category(lavadoYCuidadoRopa,categoria1);

        SQLiteDatabase sqlLectura = connection.getReadableDatabase();
        Cursor cursor = sqlLectura.rawQuery(" SELECT product.id, product.name,product.description FROM category,product WHERE "+id+ "= Category.id",null);
        cursor.moveToFirst();

        do{
            int productId = cursor.getInt(0);
            String productName = cursor.getString(1);
            String description = cursor.getString(2);
            SubCategoryI product = getSubCategoryI(productId, productName,description,producto1);
            category.addProduct(product);
        }
        while(cursor.moveToNext());
        cursor.close();
        sqlLectura.close();
        return category;
    }

    public static SubCategoryI getSubCategoryI(int productId, String productName, String productDescription, String idImage){
        SubCategoryI subCategoryI = new SubCategory(productName,productDescription, idImage);
        List<ElementSC> productElements = getElements(productId);
        subCategoryI.addElements(productElements);

//        subCategoryI.addElement(elementSC1);
//        subCategoryI.addElement(elementSC2);
//        subCategoryI.addElement(elementSC3);
//        subCategoryI.addElement(elementSC4);
//        subCategoryI.addElement(elementSC5);
//        subCategoryI.addElement(elementSC6);
//        subCategoryI.addElement(elementSC7);
        return subCategoryI;
    }

    public static List<ElementSC> getElements(int productId){
        List<ElementSC> elementSCs = new ArrayList<>();

        SQLiteDatabase sqlLectura = connection.getReadableDatabase();
        Cursor cursor = sqlLectura.rawQuery(" SELECT item.id, item.position, item.urlYoutube, item.urlVideo, item.image_Url, item.fileName FROM item,product WHERE "+productId+ "= product.id order by item.position",null);
        cursor.moveToFirst();
        int i = 0;
        String [] arrayIdImages = {"/astrix/image/i1.png",
                                "/astrix/image/i2.jpeg",
                                "/astrix/image/i3.png",
                                "/astrix/image/i4.jpeg",
                                "/astrix/image/i5.jpeg",
                                "/astrix/image/i6.png",
                                "/astrix/image/i7.png"};
        do{
            int idItem = cursor.getInt(0);
            int itemPosition  = cursor.getInt(1);
            String urlYoutube = cursor.getString(2);
            String urlVideo   = cursor.getString(3);
            String urlImage   = cursor.getString(4);
            String fileName   = cursor.getString(5);


            //SubCategoryI product = getSubCategoryI(productId, productName,description,producto1);

            ElementSC elementSC = new ElementSC(urlYoutube,arrayIdImages[i]);
            ListFormatSave listFormatSave = getListFormatSave(idItem);
            elementSC.setListFormatSave(listFormatSave);
            elementSC.setFileName(fileName);
            elementSCs.add(elementSC);
            i++;
            //category.addProduct(product);
        }
        while(cursor.moveToNext());
        cursor.close();
        sqlLectura.close();
        return elementSCs;


       /* String url1 = "https://www.youtube.com/embed/dRwq9rAZGyA?rel=0;&autoplay=1";
        String url2 = "https://www.youtube.com/embed/5V6h1i-7I4s?rel=0;&autoplay=1";
        String url3 = "https://www.youtube.com/embed/oeYKN6dFOcA?rel=0;&autoplay=1";
        String url4 = "https://www.youtube.com/embed/6Xs6-j2DI  dY?rel=0;&autoplay=1";
        String url5 = "https://www.youtube.com/embed/eVzVxS5Pe9A?rel=0;&autoplay=1";
        String url6 = "https://www.youtube.com/embed/WwmYW3tAR7M?rel=0;&autoplay=1";//creado bolivia
        String url7 = "https://www.youtube.com/embed/sqHhk1d2nvA?rel=0;&autoplay=1";//medio

        Integer images1 = R.id.primero;
        Integer images2 = R.id.segundo;
        Integer images3 = R.id.tercero;
        Integer images4 = R.id.cuarto;
        Integer images5 = R.id.quinto;
        Integer images6 = R.id.sexto;
        Integer images7 = R.id.septimo;*/
        /*1*/


//
//        ElementSC elementSC1 = new ElementSC(url1,images1);
//        FileDownload format720_1 = new FileDownload("mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/1_1.mp4");//https://r1---sn-5h25gvcg-8j2e.googlevideo.com/videoplayback?sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&itag=22&ipbits=0&mm=31%2C29&mn=sn-5h25gvcg-8j2e%2Csn-bg0ezn7s&ms=au%2Crdu&source=youtube&mv=m&mime=video%2Fmp4&key=yt6&fvip=5&dur=89.861&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&initcwndbps=463750&txp=2211222&pl=24&mt=1546698354&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&ratebypass=yes&lmt=1544513492115151&ip=131.72.140.70&expire=1546720063&requiressl=yes&signature=AB1EE63D458C094D63B1C6A0657FFE8614CD4C11.DB3B0E4229191D56CCA40F0B8A50321C486DC9D0&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas");
//        FileDownload format360_1 = new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/1_2.mp4");//"https://r5---sn-vgqs7nlr.googlevideo.com/videoplayback?sparams=clen,dur,ei,expire,gir,id,ip,ipbits,itag,lmt,mime,mip,mm,mn,ms,mv,pl,ratebypass,requiressl,source&gir=yes&requiressl=yes&clen=4470948&key=cms1&txp=2211222&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&lmt=1544513478289018&ip=131.72.140.70&expire=1546720063&itag=18&ipbits=0&source=youtube&mime=video%2Fmp4&fvip=5&dur=89.861&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&pl=32&ratebypass=yes&signature=6BDD97D6501C1AA09F03490A0AC23A34685685FC.46C788CCF570EE81DE1B4F03F4D2D129430CADB2&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas&rm=sn-5h25gvcg-8j2e7e&req_id=7aabb580fbe9a3ee&redirect_counter=2&cm2rm=sn-bg0z77l&cms_redirect=yes&mip=2803:9400:f:9738:8515:95e:bec:5000&mm=34&mn=sn-vgqs7nlr&ms=ltu&mt=1546702490&mv=m");
//        FileDownload format240_1 = new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/1_3.3gp");//"https://r1---sn-5h25gvcg-8j2e.googlevideo.com/videoplayback?sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cexpire&gir=yes&requiressl=yes&clen=2418660&key=yt6&txp=2211222&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&lmt=1544513482196383&ip=131.72.140.70&expire=1546720063&itag=36&ipbits=0&mm=31%2C29&mn=sn-5h25gvcg-8j2e%2Csn-bg0ezn7s&source=youtube&mv=m&mime=video%2F3gpp&ms=au%2Crdu&fvip=5&dur=89.907&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&initcwndbps=463750&pl=24&mt=1546698354&signature=A148AC66AB37B3B094F5584D4BAED12754E021E5.40582F12CE5BD9B95F643F5382D74DA3F438918C&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas");
//        ListFormatSave list1 = new ListFormatSave();
//        list1.addUrl(format240_1);
//        list1.addUrl(format360_1);
//        list1.addUrl(format720_1);
//        elementSC1.setListFormatSave(list1);
//        elementSC1.setFileName("Detergente OLA FUTURO Polvo - Remoción d");
//        /*2*/
//        ElementSC elementSC2 = new ElementSC(url2,images2);
//        FileDownload format720_2 =new FileDownload("mp4","mp4\t\t\t\t720", "http://eduardperez.000webhostapp.com/video/2_1.mp4");//"https://r7---sn-xouxacv-a2ce.googlevideo.com/videoplayback?sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&expire=1546720170&mime=video%2Fmp4&ei=Sb8wXJ_bOsLMkASrl4vgCw&itag=22&ipbits=0&c=WEB&ratebypass=yes&fvip=5&initcwndbps=315000&ip=181.115.185.42&requiressl=yes&pl=20&source=youtube&dur=94.551&mv=m&lmt=1544513655939915&ms=au%2Crdu&mm=31%2C29&mt=1546698451&mn=sn-xouxacv-a2ce%2Csn-hp57ynez&id=o-AMszfX9o6WpLTJ_bB7ykC2pRBSjW0C3C92HYZMH-JPye&key=yt6&txp=2211222&signature=DD584782FA8CCE75B125CFE4735E010D4681398F.79A706E0F79D16988F4072A0C576E80E9119E20C&video_id=5V6h1i-7I4s&title=Detergente+OLA+FUTURO+Polvo+-+Quitamanchas");
//        FileDownload format360_2 = new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/2_2.mp4");//https://r3---sn-vgqsenek.googlevideo.com/videoplayback?sparams=clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,pl,ratebypass,requiressl,source&clen=4354162&ipbits=0&c=WEB&mime=video%2Fmp4&requiressl=yes&source=youtube&dur=94.551&lmt=1544513644311592&gir=yes&key=cms1&txp=2211222&expire=1546720170&itag=18&ratebypass=yes&fvip=5&ip=181.115.185.42&pl=32&ei=Sb8wXJ_bOsLMkASrl4vgCw&id=o-AMszfX9o6WpLTJ_bB7ykC2pRBSjW0C3C92HYZMH-JPye&signature=6D1C6F59B5546AF2E20B6719862FF5DACD550EFC.4986C80B1096FA0A80756BB2951E6589262BCA66&video_id=5V6h1i-7I4s&title=Detergente+OLA+FUTURO+Polvo+-+Quitamanchas&rm=sn-xouxacv-a2ce7k,sn-hp56r7s&req_id=8eb04161c834a3ee&ipbypass=yes&mip=2803:9400:f:9738:8515:95e:bec:5000&redirect_counter=3&cm2rm=sn-ab5yd76&cms_redirect=yes&mm=34&mn=sn-vgqsenek&ms=ltu&mt=1546702965&mv=m");
//        FileDownload format240_2 = new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/2_3.3gp");//https://r7---sn-xouxacv-a2ce.googlevideo.com/videoplayback?sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cexpire&clen=2175944&ipbits=0&c=WEB&mime=video%2F3gpp&initcwndbps=315000&requiressl=yes&source=youtube&dur=94.598&lmt=1544513648121216&gir=yes&key=yt6&txp=2211222&expire=1546720170&itag=36&fvip=5&ip=181.115.185.42&pl=20&mt=1546698451&mv=m&ei=Sb8wXJ_bOsLMkASrl4vgCw&ms=au%2Crdu&mm=31%2C29&mn=sn-xouxacv-a2ce%2Csn-hp57ynez&id=o-AMszfX9o6WpLTJ_bB7ykC2pRBSjW0C3C92HYZMH-JPye&signature=01430A191D349F0EAC4A8DDA34E6165D6E12D483.4931A5993EDAB781ACF27884CAA0BAA8B78C4012&video_id=5V6h1i-7I4s&title=Detergente+OLA+FUTURO+Polvo+-+Quitamanchas");
//        ListFormatSave list2 = new ListFormatSave();
//        list2.addUrl(format240_2);
//        list2.addUrl(format360_2);
//        list2.addUrl(format720_2);
//        elementSC2.setListFormatSave(list2);
//        elementSC2.setFileName("Detergente OLA FUTURO Polvo - Quitamanch");
//        /*3*/
//        ElementSC elementSC3 = new ElementSC(url3,images3);
//        FileDownload format720_3 = new FileDownload("mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/3_1.mp4");//https://r6---sn-jucj-0qps.googlevideo.com/videoplayback?key=yt6&mime=video%2Fmp4&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&expire=1546725017&lmt=1544513810387429&dur=77.067&signature=8A5C7C5B591BB9D1D00D31EBBD90AB48F1E93E56.16E87A6FB3D16F9CA6A3B5CDF357DB3B6E9316DF&source=youtube&txp=2211222&mv=m&mt=1546703324&ms=au%2Crdu&mn=sn-jucj-0qps%2Csn-bg0ezn7e&mm=31%2C29&id=o-AMh2tdxJYPmAXeUXzlubDI4aS610lZfZ1u-kA_s1PC1J&c=WEB&initcwndbps=378750&ipbits=0&pcm2cms=yes&pl=22&ip=187.72.166.10&ei=OdIwXPPdDpyBobIPv_uk2AI&itag=22&ratebypass=yes&requiressl=yes&fvip=2&video_id=oeYKN6dFOcA&title=Detergente+OLA+FUTURO+Polvo+-+Sistema+Blanqueador");
//        FileDownload format360_3 = new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/3_2.mp4");//https://r6---sn-vgqsrnel.googlevideo.com/videoplayback?key=cms1&mime=video%2Fmp4&sparams=clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,pl,ratebypass,requiressl,source&expire=1546725017&lmt=1544513799040289&dur=77.067&signature=6B77E4F889E88A734E55007A8C25469F21CA312A.7997B9D83454749199F351DFC28DF1EA5766CF7A&source=youtube&txp=2211222&clen=3669473&gir=yes&id=o-AMh2tdxJYPmAXeUXzlubDI4aS610lZfZ1u-kA_s1PC1J&c=WEB&ipbits=0&pl=32&ip=187.72.166.10&ei=OdIwXPPdDpyBobIPv_uk2AI&itag=18&ratebypass=yes&requiressl=yes&fvip=2&video_id=oeYKN6dFOcA&title=Detergente+OLA+FUTURO+Polvo+-+Sistema+Blanqueador&rm=sn-jucj-0qps7d,sn-bg0677d&req_id=9e0d3e80caaba3ee&ipbypass=yes&mip=2803:9400:f:9738:8515:95e:bec:5000&redirect_counter=3&cm2rm=sn-ab5e7d7d&cms_redirect=yes&mm=34&mn=sn-vgqsrnel&ms=ltu&mt=1546703330&mv=m");
//        FileDownload format240_3 = new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/3_3.3gp");//https://r6---sn-jucj-0qps.googlevideo.com/videoplayback?key=yt6&mime=video%2F3gpp&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Crequiressl%2Csource%2Cexpire&expire=1546725017&lmt=1544513804453525&dur=77.090&signature=CCFD5E1282FB57597ED7CE0D52DD4D41279196BF.017993A3CBD69C007BF82F39A6EBE28C8DD95ACF&source=youtube&txp=2211222&mv=m&mt=1546703324&ms=au%2Crdu&clen=1918866&mn=sn-jucj-0qps%2Csn-bg0ezn7e&mm=31%2C29&gir=yes&id=o-AMh2tdxJYPmAXeUXzlubDI4aS610lZfZ1u-kA_s1PC1J&c=WEB&initcwndbps=378750&ipbits=0&pcm2cms=yes&pl=22&ip=187.72.166.10&ei=OdIwXPPdDpyBobIPv_uk2AI&itag=36&requiressl=yes&fvip=2&video_id=oeYKN6dFOcA&title=Detergente+OLA+FUTURO+Polvo+-+Sistema+Blanqueador");
//        ListFormatSave list3 = new ListFormatSave();
//        list3.addUrl(format240_3);
//        list3.addUrl(format360_3);
//        list3.addUrl(format720_3);
//        elementSC3.setListFormatSave(list3);
//        elementSC3.setFileName("Detergente OLA FUTURO Polvo - Sistema Bl");
//        /*4*/
//        ElementSC elementSC4 = new ElementSC(url4,images4);
//        FileDownload format720_4=new FileDownload("mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/4_1.mp4");//https://r4---sn-cp1oxu-jb3e.googlevideo.com/videoplayback?fvip=2&c=WEB&ipbits=0&mn=sn-cp1oxu-jb3e%2Csn-i3belne6&mm=31%2C26&requiressl=yes&mime=video%2Fmp4&expire=1546725259&dur=72.260&ms=au%2Conr&ei=KtMwXJ2lLeeZoAOa6IygCQ&mv=m&mt=1546703520&lmt=1544513982526820&ratebypass=yes&pl=24&source=youtube&signature=437FAB4074D50A1046F9D9BB5360E45B207437C2.C13043A73AE8EB3425391C701E5DD9386ECA6C24&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&initcwndbps=386250&ip=112.78.178.4&key=yt6&txp=2211222&id=o-AJpk_zlglgD4_06WqPX4s6yfPbML8aoY-VvUURoKb3Fu&itag=22&video_id=6Xs6-j2DIdY&title=Detergente+OLA+FUTURO+Polvo+-+Baja+Espuma");
//        FileDownload format360_4=new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/4_2.mp4");//https://r2---sn-ab5szn7z.googlevideo.com/videoplayback?c=WEB&ipbits=0&requiressl=yes&expire=1546725259&ei=KtMwXJ2lLeeZoAOa6IygCQ&ratebypass=yes&pl=32&source=youtube&gir=yes&txp=2211222&fvip=2&sparams=clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,pl,ratebypass,requiressl,source&mime=video%2Fmp4&clen=3676493&lmt=1544513975308133&dur=72.260&signature=70754FFF7FAA95DB21F24144F8DA6518DA803B02.0E2A192288F080404ACA672ACF3D5C242AC6437C&ip=112.78.178.4&key=cms1&id=o-AJpk_zlglgD4_06WqPX4s6yfPbML8aoY-VvUURoKb3Fu&itag=18&video_id=6Xs6-j2DIdY&title=Detergente+OLA+FUTURO+Polvo+-+Baja+Espuma&rm=sn-cp1oxu-jb3e7z,sn-npoly7l&req_id=763f495ed622a3ee&redirect_counter=2&cms_redirect=yes&ipbypass=yes&mip=2803:9400:f:9738:8515:95e:bec:5000&mm=30&mn=sn-ab5szn7z&ms=nxu&mt=1546703567&mv=m");
//        FileDownload format240_4=new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/4_3.3gp");//https://r4---sn-cp1oxu-jb3e.googlevideo.com/videoplayback?c=WEB&ipbits=0&mn=sn-cp1oxu-jb3e%2Csn-i3belne6&mm=31%2C26&requiressl=yes&expire=1546725259&ms=au%2Conr&ei=KtMwXJ2lLeeZoAOa6IygCQ&mv=m&mt=1546703520&clen=2031462&pl=24&source=youtube&gir=yes&initcwndbps=386250&txp=2211222&fvip=2&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cexpire&mime=video%2F3gpp&lmt=1544513978212754&dur=72.306&signature=928A11174CA09ED27A64AB02F8D211F6CF2EDF50.AAF5BD3F4BA322595C1609C5554BEFFFE9A1563C&ip=112.78.178.4&key=yt6&id=o-AJpk_zlglgD4_06WqPX4s6yfPbML8aoY-VvUURoKb3Fu&itag=36&video_id=6Xs6-j2DIdY&title=Detergente+OLA+FUTURO+Polvo+-+Baja+Espuma");
//        ListFormatSave list4 = new ListFormatSave();
//        list4.addUrl(format240_4);
//        list4.addUrl(format360_4);
//        list4.addUrl(format720_4);
//        elementSC4.setListFormatSave(list4);
//        elementSC4.setFileName("Detergente OLA FUTURO Polvo - Baja Espum");
//        /*5*/
//        ElementSC elementSC5 = new ElementSC(url5,images5);
//        FileDownload format720_5= new FileDownload("mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/5_1.mp4");//https://r5---sn-f5f7ln7y.googlevideo.com/videoplayback?ms=au%2Conr&fvip=5&initcwndbps=662500&mv=m&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&source=youtube&pl=22&dur=98.684&id=o-AOsfl_8W_kjIOiK7QYL40YRPQcJHt0lzOq9NIJLuoiCd&mime=video%2Fmp4&mn=sn-f5f7ln7y%2Csn-4g5e6nle&key=yt6&ip=156.67.113.198&ipbits=0&expire=1546725535&mt=1546703836&ei=P9QwXJrpA4awVuSwi-gE&txp=2211222&mm=31%2C26&c=WEB&itag=22&ratebypass=yes&lmt=1544513316999720&requiressl=yes&signature=2F5570CAF0C5F3F33DE6ED9EEEFA506A6CD81F9D.52622E434D25712735272435F119F7266970221D&video_id=eVzVxS5Pe9A&title=Detergente+OLA+FUTURO+-+Acci%C3%B3n+Antibacterial");
//        FileDownload format360_5=new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/5_2.mp4");//https://r2---sn-jvhuxjv-jccl.googlevideo.com/videoplayback?source=youtube&dur=98.684&clen=4368808&expire=1546725535&ei=P9QwXJrpA4awVuSwi-gE&itag=18&requiressl=yes&fvip=5&sparams=clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,pl,ratebypass,requiressl,source&pl=32&mime=video%2Fmp4&id=o-AOsfl_8W_kjIOiK7QYL40YRPQcJHt0lzOq9NIJLuoiCd&gir=yes&key=cms1&ip=156.67.113.198&ipbits=0&txp=2211222&c=WEB&ratebypass=yes&lmt=1544513305069272&signature=707C78495E861591719A70FB2D81545B86D5C7E8.110B7B10B28DA0D4AD453987F92D20B9B7B41F2F&video_id=eVzVxS5Pe9A&title=Detergente+OLA+FUTURO+-+Acci%C3%B3n+Antibacterial&redirect_counter=1&rm=sn-f5f676&req_id=32780701d167a3ee&cms_redirect=yes&ipbypass=yes&mip=2803:9400:f:9738:8515:95e:bec:5000&mm=31&mn=sn-jvhuxjv-jccl&ms=au&mt=1546703836&mv=m");
//        FileDownload format240_5=new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/5_3.3gp");//https://r5---sn-f5f7ln7y.googlevideo.com/videoplayback?initcwndbps=662500&source=youtube&dur=98.731&clen=2505102&expire=1546725535&ei=P9QwXJrpA4awVuSwi-gE&mm=31%2C26&itag=36&requiressl=yes&ms=au%2Conr&fvip=5&mv=m&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cexpire&mt=1546703836&pl=22&mime=video%2F3gpp&id=o-AOsfl_8W_kjIOiK7QYL40YRPQcJHt0lzOq9NIJLuoiCd&gir=yes&mn=sn-f5f7ln7y%2Csn-4g5e6nle&key=yt6&ip=156.67.113.198&ipbits=0&txp=2211222&c=WEB&lmt=1544513307970928&signature=E3418C1F980BDEB2BC9A0A4E0F72007EC0B1B292.CB6FDEB84BF1AEE74C441BC1975795F51B8A9EC1&video_id=eVzVxS5Pe9A&title=Detergente+OLA+FUTURO+-+Acci%C3%B3n+Antibacterial");
//        ListFormatSave list5 = new ListFormatSave();
//        list5.addUrl(format240_5);
//        list5.addUrl(format360_5);
//        list5.addUrl(format720_5);
//        elementSC5.setListFormatSave(list5);
//        elementSC5.setFileName("Detergente OLA FUTURO - Acción Antibacte");
//
//        ElementSC elementSC6 = new ElementSC(url6,images6);
//        FileDownload format720_6 = new FileDownload("mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/6_1.mp4");
//        FileDownload format360_6 = new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/6_2.mp4");
//        FileDownload format240_6 = new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/6_3.mp4");
//        ListFormatSave list6 = new ListFormatSave();
//        list6.addUrl(format240_6);
//        list6.addUrl(format360_6);
//        list6.addUrl(format720_6);
//        elementSC6.setListFormatSave(list6);
//        elementSC6.setFileName("Detergente OLA FUTURO - creadoBolivia");
//
//
//        ElementSC elementSC7 = new ElementSC(url7,images7);
//        FileDownload format720_7 = new FileDownload("mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/7_1.mp4");
//        FileDownload format360_7 = new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/7_2.mp4");
//        FileDownload format240_7 = new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/7_3.mp4");
//        ListFormatSave list7 = new ListFormatSave();
//        list7.addUrl(format240_7);
//        list7.addUrl(format360_7);
//        list7.addUrl(format720_7);
//        elementSC7.setListFormatSave(list7);
//        elementSC7.setFileName("Detergente OLA FUTURO - beneficios");

    }

    public static ListFormatSave getListFormatSave(int idItem){
        ListFormatSave listFormatSave = new ListFormatSave();


        SQLiteDatabase sqlLectura = connection.getReadableDatabase();
        Cursor cursor = sqlLectura.rawQuery(" SELECT formatdownload.id, formatdownload.position,formatdownload.formatname ,formatdownload.resolution, formatdownload.urldownload FROM formatdownload,item WHERE "+idItem+ "= item.id order by formatDownload.position asc",null);
        cursor.moveToFirst();
        if(cursor.getCount() ==0){
            return listFormatSave;
        }
        do{
            String formatName           = cursor.getString(2);
            String formatResolution     = cursor.getString(3);
            String urlDownload          = cursor.getString(4);
            FileDownload format         = new FileDownload(formatName,formatResolution,urlDownload);
            listFormatSave.addUrl(format);
        }
        while(cursor.moveToNext());
        cursor.close();
        sqlLectura.close();
        return listFormatSave;

//        FileDownload format720_1 = new FileDownload("mp4","mp4\t\t\t\t720","http://eduardperez.000webhostapp.com/video/1_1.mp4");//https://r1---sn-5h25gvcg-8j2e.googlevideo.com/videoplayback?sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&itag=22&ipbits=0&mm=31%2C29&mn=sn-5h25gvcg-8j2e%2Csn-bg0ezn7s&ms=au%2Crdu&source=youtube&mv=m&mime=video%2Fmp4&key=yt6&fvip=5&dur=89.861&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&initcwndbps=463750&txp=2211222&pl=24&mt=1546698354&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&ratebypass=yes&lmt=1544513492115151&ip=131.72.140.70&expire=1546720063&requiressl=yes&signature=AB1EE63D458C094D63B1C6A0657FFE8614CD4C11.DB3B0E4229191D56CCA40F0B8A50321C486DC9D0&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas");
//        FileDownload format360_1 = new FileDownload("mp4","mp4\t\t\t\t360","http://eduardperez.000webhostapp.com/video/1_2.mp4");//"https://r5---sn-vgqs7nlr.googlevideo.com/videoplayback?sparams=clen,dur,ei,expire,gir,id,ip,ipbits,itag,lmt,mime,mip,mm,mn,ms,mv,pl,ratebypass,requiressl,source&gir=yes&requiressl=yes&clen=4470948&key=cms1&txp=2211222&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&lmt=1544513478289018&ip=131.72.140.70&expire=1546720063&itag=18&ipbits=0&source=youtube&mime=video%2Fmp4&fvip=5&dur=89.861&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&pl=32&ratebypass=yes&signature=6BDD97D6501C1AA09F03490A0AC23A34685685FC.46C788CCF570EE81DE1B4F03F4D2D129430CADB2&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas&rm=sn-5h25gvcg-8j2e7e&req_id=7aabb580fbe9a3ee&redirect_counter=2&cm2rm=sn-bg0z77l&cms_redirect=yes&mip=2803:9400:f:9738:8515:95e:bec:5000&mm=34&mn=sn-vgqs7nlr&ms=ltu&mt=1546702490&mv=m");
//        FileDownload format240_1 = new FileDownload("3gp","3gp\t\t\t\t240","http://eduardperez.000webhostapp.com/video/1_3.3gp");//"https://r1---sn-5h25gvcg-8j2e.googlevideo.com/videoplayback?sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cexpire&gir=yes&requiressl=yes&clen=2418660&key=yt6&txp=2211222&c=WEB&ei=374wXMLnHNiFobIPv5ORwAE&lmt=1544513482196383&ip=131.72.140.70&expire=1546720063&itag=36&ipbits=0&mm=31%2C29&mn=sn-5h25gvcg-8j2e%2Csn-bg0ezn7s&source=youtube&mv=m&mime=video%2F3gpp&ms=au%2Crdu&fvip=5&dur=89.907&id=o-AEOxWP08k2lP-FwHl4j272w3dWKea2_qHAZcFSfQlqSx&initcwndbps=463750&pl=24&mt=1546698354&signature=A148AC66AB37B3B094F5584D4BAED12754E021E5.40582F12CE5BD9B95F643F5382D74DA3F438918C&video_id=dRwq9rAZGyA&title=Detergente+OLA+FUTURO+Polvo+-+Remoci%C3%B3n+de+manchas");
//        ListFormatSave list1 = new ListFormatSave();
//        list1.addUrl(format240_1);
//        list1.addUrl(format360_1);
//        list1.addUrl(format720_1);
//        return listFormatSave;
    }
}
