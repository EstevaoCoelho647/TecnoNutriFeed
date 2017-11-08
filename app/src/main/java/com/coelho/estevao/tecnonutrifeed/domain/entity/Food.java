package com.coelho.estevao.tecnonutrifeed.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by estevao on 07/11/17.
 */

public class Food implements Parcelable {
    private String description;
    private String measure;
    private int amount;
    private int weight;
    private float energy;
    private float carbohydrate;
    private double fat;
    private float protein;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.measure);
        dest.writeInt(this.amount);
        dest.writeInt(this.weight);
        dest.writeFloat(this.energy);
        dest.writeFloat(this.carbohydrate);
        dest.writeDouble(this.fat);
        dest.writeFloat(this.protein);
    }

    public Food() {
    }

    protected Food(Parcel in) {
        this.description = in.readString();
        this.measure = in.readString();
        this.amount = in.readInt();
        this.weight = in.readInt();
        this.energy = in.readFloat();
        this.carbohydrate = in.readFloat();
        this.fat = in.readDouble();
        this.protein = in.readFloat();
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
