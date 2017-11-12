package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import com.coelho.estevao.tecnonutrifeed.domain.entity.FeedItems;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.persistence.ItemDatabaseRepository;
import com.coelho.estevao.tecnonutrifeed.domain.repository.FeedRepository;

/**
 * Created by estevao on 06/11/17.
 */

public class MainModel implements MainContract.Model {
    MainContract.Presenter presenter;

    public MainModel(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestFeedItems(Integer p, Long t, boolean clear) {
        new FeedRepository().findFeedItems(presenter, p, t, clear);
    }

    @Override
    public void saveOrUpdateDatabase(FeedItems feedItems) {
        ItemDatabaseRepository.saveOrUpdate(feedItems.getItems());
    }

    @Override
    public void setItemLiked(Item item, boolean liked) {
        ItemDatabaseRepository.updateItem(item, liked);
    }
}
