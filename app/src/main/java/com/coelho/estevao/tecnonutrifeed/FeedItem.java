package com.coelho.estevao.tecnonutrifeed;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItem {

    private String image;
    private String date;
    private String energy;
    private Profile profile;


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

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }
}
