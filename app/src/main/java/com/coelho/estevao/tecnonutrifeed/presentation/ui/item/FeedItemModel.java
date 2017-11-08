package com.coelho.estevao.tecnonutrifeed.presentation.ui.item;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.repository.ItemRepository;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItemModel implements FeedItemContract.Model {
    FeedItemContract.Presenter presenter;

    public FeedItemModel(FeedItemContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestItemInformation(Item item) {
        ItemRepository itemRepository = new ItemRepository();
        itemRepository.findItemInformation(presenter, item.getFeedHash());
    }
}
