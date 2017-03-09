package com.michalhornak.orderapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michal.hornak on 3/9/2017.
 */

public class Category implements Parcelable{

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    private Category(Parcel in){
        name = in.readString();
    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<Movie> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>(){
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
