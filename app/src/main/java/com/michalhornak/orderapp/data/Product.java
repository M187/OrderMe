package com.michalhornak.orderapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michal.hornak on 5/18/2017.
 */

public class Product implements Parcelable {

    private String ID;
    private String CATEGORY_ID;
    private String NAME;
    private String PRICE;

    public Product(String ID, String CATEGORY_ID, String NAME, String PRICE) {
        this.ID = ID;
        this.CATEGORY_ID = CATEGORY_ID;
        this.NAME = NAME;
        this.PRICE = PRICE;
    }

    public String getID() {
        return ID;
    }

    public String getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getPRICE() {
        return PRICE;
    }

    protected Product(Parcel in) {
        ID = in.readString();
        CATEGORY_ID = in.readString();
        NAME = in.readString();
        PRICE = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(CATEGORY_ID);
        dest.writeString(NAME);
        dest.writeString(PRICE);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
