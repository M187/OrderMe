package com.michalhornak.persistence;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by michal.hornak on 3/9/2017.
 */

public class ItemsContract {

    public static final String CONTENT_AUTHORITY = "com.michalhornak.persistence";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PRODUCTS_DB = "items";


    public static final class CategoryEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PRODUCTS_DB).build();
        public static final String TABLE_NAME = "categories";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CATEGORY_NAME = "category_name";
    }


    public static final class ProductEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PRODUCTS_DB).build();
        public static final String TABLE_NAME = "products";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_PRICE = "product_price";
    }
}
