/* Created 4/24/2023
 *
 * Author: Tiffany broz */

package com.zybooks.dinnerwiththebroz;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {
    private long mId;
    private String mTitle;
    private String mIngredients;
    private String mSteps;

    public Recipe() {
        mId = -1; // default value for new recipes
        mTitle = "";
        mIngredients = "";
        mSteps = "";
    }

    public Recipe(String title, String ingredients, String steps) {
        this();
        mTitle = title;
        mIngredients = ingredients;
        mSteps = steps;
    }

    // Constructor used by the Parcelable.Creator
    public Recipe(Parcel parcel) {
        mId = parcel.readLong();
        mTitle = parcel.readString();
        mIngredients = parcel.readString();
        mSteps = parcel.readString();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public void setIngredients(String ingredients) {
        mIngredients = ingredients;
    }

    public String getSteps() {
        return mSteps;
    }

    public void setSteps(String steps) {
        mSteps = steps;
    }

    // Parcelable implementation

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mIngredients);
        parcel.writeString(mSteps);
    }
}