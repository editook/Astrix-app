package com.software.program.astrixsa.util;

public class Util {
    public static final String CATEGORY_TABLE = "CATEGORY";
    private static final String ID_FIELD= "ID";
    private static final String NAME_FIELD= "NAME";
    private static final String IMAGE_FIELD= "IMAGE";

    public static final String CREATE_CATEGORY_TABLE="CREATE TABLE " +
                                                ""+CATEGORY_TABLE+" ("+ID_FIELD+" " +
                                                "INTEGER, "+NAME_FIELD+" TEXT,"+IMAGE_FIELD+" TEXT)";
}
