package com.michalhornak.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michal.hornak on 3/9/2017.
 */

public class ItemsDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "items.db";

    private final static int DATABASE_VERSION = 5;

    public ItemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_CATEGORY_ITEMS_TABLE =
                "CREATE TABLE " + ItemsContract.CategoryEntry.TABLE_NAME + " (" +
                        ItemsContract.CategoryEntry.COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                        ItemsContract.CategoryEntry.COLUMN_CATEGORY_NAME + " STRING DEFAULT '' )" +
                        ItemsContract.CategoryEntry.COLUMN_GROUP_ID + " STRING DEFAULT 'drink' )";

        final String SQL_CREATE_PRODUCT_ITEMS_TABLE =
                "CREATE TABLE " + ItemsContract.ProductEntry.TABLE_NAME + " (" +
                        ItemsContract.ProductEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        ItemsContract.ProductEntry.COLUMN_CATEGORY_ID + " INTEGER DEFAULT 0, " +
                        ItemsContract.ProductEntry.COLUMN_PRODUCT_NAME + " STRING DEFAULT '', " +
                        ItemsContract.ProductEntry.COLUMN_PRODUCT_PRICE + " DECIMAL(10, 2) DEFAULT '0,00', " +
                        "FOREIGN KEY (" + ItemsContract.ProductEntry.COLUMN_CATEGORY_ID + ") " +
                        "REFERENCES " + ItemsContract.CategoryEntry.TABLE_NAME + "(" + ItemsContract.CategoryEntry.COLUMN_ID + "))";

        db.execSQL(SQL_CREATE_CATEGORY_ITEMS_TABLE);
        db.execSQL(SQL_CREATE_PRODUCT_ITEMS_TABLE);

        //Test data:

        String testDataInsertionDrinkCategories = "INSERT INTO " + ItemsContract.CategoryEntry.TABLE_NAME + " (" +
                ItemsContract.CategoryEntry.COLUMN_CATEGORY_NAME + ") VALUES " +
                "('Beer')," +
                "('Shots')," +
                "('Vine')," +
                "('Tea')," +
                "('Coffee')," +
                "('Drinks')," +
                "('Non-Alco');";

        String testDataInsertionFoodCategories = "INSERT INTO " + ItemsContract.CategoryEntry.TABLE_NAME + " (" +
                ItemsContract.CategoryEntry.COLUMN_CATEGORY_NAME + ") VALUES " +
                "('Appetizers','food')," +
                "('Soups','food')," +
                "('Pork','food')," +
                "('Beef','food')," +
                "('Veggie','food')," +
                "('Fishes','food')," +
                "('Deserts','food');";

        String testDataInsertionBeer = "INSERT INTO " + ItemsContract.ProductEntry.TABLE_NAME + " (" +
                ItemsContract.ProductEntry.COLUMN_PRODUCT_NAME + ", " + ItemsContract.ProductEntry.COLUMN_PRODUCT_PRICE + ", " + ItemsContract.ProductEntry.COLUMN_CATEGORY_ID + ") VALUES " +
                "('Stella', '1,00', " + buildSelectCategoryString("Beer") + ")," +
                "('Kozel', '1,00', " + buildSelectCategoryString("Beer") + ")," +
                "('Saris', '1,00', " + buildSelectCategoryString("Beer") + ");";

        String testDataInsertionShots = "INSERT INTO " + ItemsContract.ProductEntry.TABLE_NAME + " (" +
                ItemsContract.ProductEntry.COLUMN_PRODUCT_NAME + ", " + ItemsContract.ProductEntry.COLUMN_PRODUCT_PRICE + ", " + ItemsContract.ProductEntry.COLUMN_CATEGORY_ID + ") VALUES " +
                "('Vodka', '1,00', " + buildSelectCategoryString("Shots") + ")," +
                "('Rum', '1,00', " + buildSelectCategoryString("Shots") + ")," +
                "('Borovicka', '1,00', " + buildSelectCategoryString("Shots") + ");";

        String testDataInsertionAppetizers = "INSERT INTO " + ItemsContract.ProductEntry.TABLE_NAME + " (" +
                ItemsContract.ProductEntry.COLUMN_PRODUCT_NAME + ", " + ItemsContract.ProductEntry.COLUMN_PRODUCT_PRICE + ", " + ItemsContract.ProductEntry.COLUMN_CATEGORY_ID + ") VALUES " +
                "('Omelet with bread of choice.', '5,00', " + buildSelectCategoryString("Appetizers") + ")," +
                "('Pork ham with cheese and bread.', '5,00', " + buildSelectCategoryString("Appetizers") + ")," +
                "('Ham and Egs with bread of choice.', '5,00', " + buildSelectCategoryString("Appetizers") + ");";

        String testDataInsertionSoups = "INSERT INTO " + ItemsContract.ProductEntry.TABLE_NAME + " (" +
                ItemsContract.ProductEntry.COLUMN_PRODUCT_NAME + ", " + ItemsContract.ProductEntry.COLUMN_PRODUCT_PRICE + ", " + ItemsContract.ProductEntry.COLUMN_CATEGORY_ID + ") VALUES " +
                "('0,3l Tomato soup with cheese.', '5,00', " + buildSelectCategoryString("Soups") + ")," +
                "('0,3l Chicken soup with pasta.', '5,00', " + buildSelectCategoryString("Soups") + ")," +
                "('0,5l Goulash with bread.', '10,00', " + buildSelectCategoryString("Soups") + ");";

        db.execSQL(testDataInsertionDrinkCategories);
        db.execSQL(testDataInsertionFoodCategories);
        db.execSQL(testDataInsertionBeer);
        db.execSQL(testDataInsertionShots);
        db.execSQL(testDataInsertionAppetizers);
        db.execSQL(testDataInsertionSoups);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ItemsContract.ProductEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ItemsContract.CategoryEntry.TABLE_NAME);
        onCreate(db);
    }

    private String buildSelectCategoryString(String categoryType){
        return "(SELECT " + ItemsContract.CategoryEntry.COLUMN_ID + " FROM " + ItemsContract.CategoryEntry.TABLE_NAME + " WHERE " + ItemsContract.CategoryEntry.COLUMN_CATEGORY_NAME + " = '" + categoryType + "')";
    }
}
