package com.coelho.estevao.tecnonutrifeed.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by estevao on 06/11/17.
 */

public class Profile extends RealmObject implements Parcelable {
    @PrimaryKey
    private Long id;
    private String image;
    private String name;
    @SerializedName("general_goal")
    private String generalGoal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Profile() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeString(this.generalGoal);
    }

    protected Profile(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.image = in.readString();
        this.name = in.readString();
        this.generalGoal = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
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
