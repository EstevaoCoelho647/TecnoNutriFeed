package com.coelho.estevao.tecnonutrifeed;

import java.util.List;

/**
 * Created by estevao on 06/11/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.Model model;
    private MainContract.View view;

    public MainPresenter() {
        this.model = new MainModel(this);

    }

    @Override
    public void onAttachView(MainContract.View view) {
        this.view = view;

    }

    @Override
    public void loadFeedItems() {
        model.requestFeedItems();
    }

    @Override
    public void onFindFeedItemsSuccess(List<Item> list) {
        view.onFindFeedItemsSuccess(list);
    }

    @Override
    public void onFindFeedItemsFailure(String message) {
        view.showSnackBar(message);
    }
}
