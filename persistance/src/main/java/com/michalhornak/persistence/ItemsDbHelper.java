package com.michalhornak.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michal.hornak on 3/9/2017.
 */

public class ItemsDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "items.db";

    private final static int DATABASE_VERSION = 1;

    public ItemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
