package com.michalhornak.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by michal.hornak on 5/18/2017.
 */

public class ProductItemsProvider extends ContentProvider {


    private static final int GET_CATEGORIES = 10;
    private static final int GET_CATEGORIES_FOOD = 11;
    private static final int GET_CATEGORIES_DRINKS = 12;
    private static final int GET_PRODUCTS = 20;

    private static final UriMatcher URI_MATCHER;

    // prepare the uri matcher
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(ItemsContract.CONTENT_AUTHORITY,
                ItemsContract.PATH_PRODUCTS_DB + "/category",
                GET_CATEGORIES);
        URI_MATCHER.addURI(ItemsContract.CONTENT_AUTHORITY,
                ItemsContract.PATH_PRODUCTS_DB + "/category/food",
                GET_CATEGORIES_FOOD);
        URI_MATCHER.addURI(ItemsContract.CONTENT_AUTHORITY,
                ItemsContract.PATH_PRODUCTS_DB + "/category/drinks",
                GET_CATEGORIES_DRINKS);
        URI_MATCHER.addURI(ItemsContract.CONTENT_AUTHORITY,
                ItemsContract.PATH_PRODUCTS_DB + "/products",
                GET_PRODUCTS);
    }

    private ItemsDbHelper mHelper;

    @Override
    public boolean onCreate() {
        mHelper = new ItemsDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (URI_MATCHER.match(uri)) {
            case GET_PRODUCTS:
                builder.setTables(ItemsContract.ProductEntry.TABLE_NAME);
                break;
            case GET_CATEGORIES_FOOD:
                builder.setTables(ItemsContract.ProductEntry.TABLE_NAME);
                selection = "group_id = 'food'";
                break;
            case GET_CATEGORIES_DRINKS:
                builder.setTables(ItemsContract.ProductEntry.TABLE_NAME);
                selection = "group_id = 'drink'";
                break;
            case GET_CATEGORIES:
                builder.setTables(ItemsContract.CategoryEntry.TABLE_NAME);
                break;
            default:
                return null;
        }

        Cursor cursor = builder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
