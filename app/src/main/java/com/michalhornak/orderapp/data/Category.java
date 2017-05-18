package com.michalhornak.orderapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michal.hornak on 3/9/2017.
 */

public class Category implements Parcelable {

    public Category(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    private String name;

    public String getID() {
        return ID;
    }

    private String ID;




    protected Category(Parcel in) {
        name = in.readString();
        ID = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(ID);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
