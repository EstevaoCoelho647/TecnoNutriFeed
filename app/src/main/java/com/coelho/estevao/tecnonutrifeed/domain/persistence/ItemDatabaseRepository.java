package com.coelho.estevao.tecnonutrifeed.domain.persistence;

import android.util.Log;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;

/**
 * Created by estevao on 12/11/17.
 */

public class ItemDatabaseRepository {
    private static final String ID = "feedHash";

    public static void saveOrUpdate(final Item item) {
        Log.d(TAG, "Saving item...");
        Realm realm = Realm.getDefaultInstance();

        //Verify if item was liked to not overwrite attribute
        Item itemById = getItemById(item.getFeedHash());
        if (itemById != null && itemById.isLiked())
            item.setLiked(true);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(item);
            }
        });
    }

    public static void saveOrUpdate(List<Item> array) {
        Log.d(TAG, "Saving item array...");
        for (Item item : array) {
            saveOrUpdate(item);
        }
    }

    public static Item getItemById(String itemId) {
        Log.d(TAG, "Getting item by id...");

        Realm realm = Realm.getDefaultInstance();
        return realm.where(Item.class).equalTo(ItemDatabaseRepository.ID, itemId).findFirst();
    }

    public static RealmResults<Item> getAll() {
        Log.d(TAG, "Getting item array...");
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Item.class).findAll();
    }

    public static void updateItem(final Item item, final boolean liked) {
        item.setLiked(liked);
        saveOrUpdate(item);
    }
}
