package com.coelho.estevao.tecnonutrifeed.domain.entity;

import java.util.List;

/**
 * Created by estevao on 10/11/17.
 */

public class ProfileRequest extends RequestClass {

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
}
