package com.coelho.estevao.tecnonutrifeed.presentation.ui.item;

import android.os.Bundle;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItemPresenter implements FeedItemContract.Presenter {

    private final FeedItemContract.Model model;
    private FeedItemContract.View view;

    public FeedItemPresenter() {
        this.model = new FeedItemModel(this);

    }

    @Override
    public void onAttachView(FeedItemContract.View view) {
        this.view = view;

    }

    @Override
    public void getExtras(Bundle extras) {
        if (extras != null) {
            Item item = extras.getParcelable("ITEM");
            view.bindItemFields(item);
            model.requestItemInformation(item);
        }
    }

    @Override
    public void onFindItemInformationSuccess(Item item) {
        view.onFindFeedItemInformationSuccess(item);
    }

    @Override
    public void onFindItemInformationFailure(String message) {
        view.showSnackBar(message);
    }
}
