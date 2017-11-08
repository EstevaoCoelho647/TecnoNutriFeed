package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

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
    public void requestFeedItems() {
        new FeedRepository().findFeedItems(presenter);
    }
}
