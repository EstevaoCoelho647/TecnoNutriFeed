package com.coelho.estevao.tecnonutrifeed.domain.entity;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItem extends RequestClass {

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
