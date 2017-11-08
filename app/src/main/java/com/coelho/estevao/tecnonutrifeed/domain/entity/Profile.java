package com.coelho.estevao.tecnonutrifeed.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by estevao on 06/11/17.
 */

public class Profile implements Parcelable {
    private String image;
    private String name;
    @SerializedName("general_goal")
    private String generalGoal;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneralGoal() {
        return generalGoal;
    }

    public void setGeneralGoal(String generalGoal) {
        this.generalGoal = generalGoal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeString(this.generalGoal);
    }

    public Profile() {
    }

    protected Profile(Parcel in) {
        this.image = in.readString();
        this.name = in.readString();
        this.generalGoal = in.readString();
    }

    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
