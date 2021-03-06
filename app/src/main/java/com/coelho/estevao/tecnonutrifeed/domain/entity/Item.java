package com.coelho.estevao.tecnonutrifeed.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by estevao on 06/11/17.
 */


public class Item extends RealmObject implements Parcelable {
    @PrimaryKey
    private String feedHash;
    private String image;
    private String date;
    private float energy;
    private float carbohydrate;
    private double fat;
    private float protein;
    private Profile profile;
    private int meal;
    private RealmList<Food> foods;
    private boolean liked;

    public int getMeal() {
        return meal;
    }

    public void setMeal(int meal) {
        this.meal = meal;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getFeedHash() {
        return feedHash;
    }

    public void setFeedHash(String feedHash) {
        this.feedHash = feedHash;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public Item() {
        foods = new RealmList<>();
    }

    public RealmList<Food> getFoods() {
        return foods;
    }

    public void setFoods(RealmList<Food> foods) {
        this.foods = foods;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.feedHash);
        dest.writeString(this.image);
        dest.writeString(this.date);
        dest.writeFloat(this.energy);
        dest.writeFloat(this.carbohydrate);
        dest.writeDouble(this.fat);
        dest.writeFloat(this.protein);
        dest.writeParcelable(this.profile, flags);
        dest.writeInt(this.meal);
        dest.writeTypedList(this.foods);
        dest.writeByte(this.liked ? (byte) 1 : (byte) 0);
    }

    protected Item(Parcel in) {
        this.feedHash = in.readString();
        this.image = in.readString();
        this.date = in.readString();
        this.energy = in.readFloat();
        this.carbohydrate = in.readFloat();
        this.fat = in.readDouble();
        this.protein = in.readFloat();
        this.profile = in.readParcelable(Profile.class.getClassLoader());
        this.meal = in.readInt();
        this.foods = new RealmList<>();
        this.foods.addAll(in.createTypedArrayList(Food.CREATOR));
        this.liked = in.readByte() != 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
