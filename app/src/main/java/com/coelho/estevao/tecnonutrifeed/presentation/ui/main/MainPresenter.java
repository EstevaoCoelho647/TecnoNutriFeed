package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import android.content.Intent;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.item.FeedItemActivity;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.ProfileActivity;

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

    @Override
    public void onItemClick(Item item) {
        Intent goToProfileActivity = new Intent(view.getActivityFromView(), FeedItemActivity.class);
        goToProfileActivity.putExtra("ITEM", item);
        view.getActivityFromView().startActivity(goToProfileActivity);
    }

    @Override
    public void onProfileClick(Profile profile) {
        Intent goToProfileActivity = new Intent(view.getActivityFromView(), ProfileActivity.class);
        goToProfileActivity.putExtra("PROFILE", profile);
        view.getActivityFromView().startActivity(goToProfileActivity);
    }
}
