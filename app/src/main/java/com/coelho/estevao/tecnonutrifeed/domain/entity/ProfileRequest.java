package com.coelho.estevao.tecnonutrifeed.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by estevao on 10/11/17.
 */

public class ProfileRequest{

    private Boolean success;
    @SerializedName("t")
    private Long timeMillis;
    @SerializedName("p")
    private int page;
    private Profile profile;
    private List<Item> items;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
