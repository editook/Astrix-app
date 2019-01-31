package com.software.program.astrixsa.util;

public class Util {

    public static final String DB_NAME = "DATABASEASTRIXAPP6";
    public static final String CURRENT_VERSION = "0";

    public static final String STATUS_TABLE = "STATUS";
    public static final String VERSION_FIELD= "VERSION";
    public static final String IS_DOWNLOADED= "IS_DOWNLOADED";

    //CATEGORY
    public static final String CATEGORY_TABLE   = "CATEGORY";
    public static final String ID_FIELD         = "ID";
    public static final String NAME_FIELD       = "NAME";
    public static final String IMAGE_URL_FIELD  = "IMAGE_URL";
    //PRODUCT
    public static final String PRODUCT_TABLE        = "PRODUCT";
    public static final String DESCRIPTION_FIELD    = "DESCRIPTION";
    public static final String PRODUCT_TITLE_FIELD  = "TITLEPRODUCT";
    //ITEM
    public static final String ITEM_TABLE   = "ITEM";
    public static final String POSITION     = "POSITION";
    public static final String URLYOUTUBE   = "URLYOUTUBE";
    public static final String URLVIDEO     = "URLVIDEO";
    public static final String FILE_NAME    = "FILENAME";
    //FORMATDOWNLOAD
    public static final String FORMATDOWNLOAD_TABLE = "FORMATDOWNLOAD";
    public static final String URLDOWNLOAD          = "URLDOWNLOAD";
    public static final String RESOLUTION           = "RESOLUTION";
    public static final String FORMATNAME           = "FORMATNAME";

    //STATIC DATA
    public static final String CATEGORY1 =  "LAVADO Y CUIDADO DE LA ROPA";
    public static final String CATEGORY2 =  "HIGIENE Y CUIDADO PERSONAL";

    public static final String PRODUCT1 =  "OLA FUTURO MAQUINA";
    public static final String PRODUCT2 =  "OLA PASADO";

    public static final String DESCRIPTION1 = "Detergente en Polvo";
    public static final String DESCRIPTION2 = "Detergente Liquido";

    public static final String CREATE_STATUS_TABLE = "CREATE TABLE "+
                                                    ""+STATUS_TABLE+" ("+ID_FIELD+" " +
                                                    "INTEGER PRIMARY KEY, "+VERSION_FIELD+" TEXT,"+IS_DOWNLOADED+" INTEGER)";
    public static final String INSERT_INITIAL_STATUS = "INSERT INTO STATUS (VERSION_FIELD, IS_DOWNLOADED) VALUES ('"+CURRENT_VERSION+"', 1)";
    public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE " +
                                                        ""+CATEGORY_TABLE+" ("+ID_FIELD+" " +
                                                        "INTEGER PRIMARY KEY, "+NAME_FIELD+" TEXT,"+IMAGE_URL_FIELD+" TEXT)";

    public static final String CREATE_PRODUCT_TABLE  = "CREATE TABLE PRODUCT("+
                                                        ""+ID_FIELD +" "+"INTEGER PRIMARY KEY,"+
                                                        ""+NAME_FIELD+" "+"TEXT,"+
                                                        ""+DESCRIPTION_FIELD+" "+"TEXT,"+
                                                        ""+IMAGE_URL_FIELD+" "+"TEXT,"+
                                                        ""+PRODUCT_TITLE_FIELD+" "+"TEXT,"+
                                                        ""+ID_FIELD+"_"+CATEGORY_TABLE+" "+"INTEGER,"+
                                                        ""+"FOREIGN KEY ("+ID_FIELD+"_"+CATEGORY_TABLE+") REFERENCES "+CATEGORY_TABLE+" ("+ID_FIELD+"))";

    public static final String CREATE_ITEM_TABLE = "CREATE TABLE ITEM("+
                                                    ""+ID_FIELD +" "+"INTEGER PRIMARY KEY,"+
                                                    ""+POSITION+" "+"INTEGER,"+
                                                    ""+URLYOUTUBE+" "+"TEXT,"+
                                                    ""+IMAGE_URL_FIELD+" "+"TEXT,"+
                                                    ""+URLVIDEO+" "+"TEXT,"+
                                                    ""+FILE_NAME+" "+"TEXT,"+
                                                    ""+ID_FIELD+"_"+PRODUCT_TABLE+" "+"INTEGER,"+
                                                    ""+"FOREIGN KEY ("+ID_FIELD+"_"+PRODUCT_TABLE+") REFERENCES "+PRODUCT_TABLE+" ("+ID_FIELD+"))";

    public static final String CREATE_FORMATDOWNLOAD_TABLE = "CREATE TABLE FORMATDOWNLOAD("+
                                                            ""+ID_FIELD +" "+"INTEGER PRIMARY KEY,"+
                                                            ""+POSITION+" "+"INTEGER,"+
                                                            ""+URLDOWNLOAD+" "+"TEXT,"+
                                                            ""+RESOLUTION+" "+"TEXT,"+
                                                            ""+FORMATNAME+" "+"TEXT,"+
                                                            ""+ID_FIELD+"_"+ITEM_TABLE+" "+"INTEGER,"+
                                                            ""+"FOREIGN KEY ("+ID_FIELD+"_"+ITEM_TABLE+") REFERENCES "+ITEM_TABLE+" ("+ID_FIELD+"))";



    public static final String url1 = "https://www.youtube.com/embed/dRwq9rAZGyA?rel=0;&autoplay=1";
    public static final String url2 = "https://www.youtube.com/embed/5V6h1i-7I4s?rel=0;&autoplay=1";
    public static final String url3 = "https://www.youtube.com/embed/oeYKN6dFOcA?rel=0;&autoplay=1";
    public static final String url4 = "https://www.youtube.com/embed/6Xs6-j2DI  dY?rel=0;&autoplay=1";
    public static final String url5 = "https://www.youtube.com/embed/eVzVxS5Pe9A?rel=0;&autoplay=1";
    public static final String url6 = "https://www.youtube.com/embed/WwmYW3tAR7M?rel=0;&autoplay=1";//creado bolivia
    public static final String url7 = "https://www.youtube.com/embed/sqHhk1d2nvA?rel=0;&autoplay=1";//medio


    public static final String fileName1 = "Detergente OLA FUTURO Polvo - Remoción d";
    public static final String fileName2 = "Detergente OLA FUTURO Polvo - Quitamanch";
    public static final String fileName3 = "Detergente OLA FUTURO Polvo - Sistema Bl";
    public static final String fileName4 = "Detergente OLA FUTURO Polvo - Baja Espum";
    public static final String fileName5 = "Detergente OLA FUTURO - Acción Antibacte";
    public static final String fileName6 = "Detergente OLA FUTURO - creadoBolivia";
    public static final String fileName7 = "Detergente OLA FUTURO - beneficios";





}
