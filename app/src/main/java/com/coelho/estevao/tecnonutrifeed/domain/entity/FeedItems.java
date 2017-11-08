package com.coelho.estevao.tecnonutrifeed.domain.entity;

import java.util.List;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItems extends RequestClass {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
