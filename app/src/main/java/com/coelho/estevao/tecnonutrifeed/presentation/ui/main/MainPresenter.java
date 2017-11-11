package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coelho.estevao.tecnonutrifeed.domain.entity.FeedItems;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.item.FeedItemActivity;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.ProfileActivity;

/**
 * Created by estevao on 06/11/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.Model model;
    private MainContract.View view;
    private FeedItems feedItems;
    private boolean requesting;

    public MainPresenter() {
        this.model = new MainModel(this);
        feedItems = new FeedItems();
    }

    @Override
    public void onAttachView(MainContract.View view) {
        this.view = view;

    }

    @Override
    public void loadFeedItems(Integer p, Long t, boolean clear) {
        model.requestFeedItems(p, t, clear);
    }

    @Override
    public void onFindFeedItemsSuccess(FeedItems feedItems, boolean clear) {
        this.feedItems = feedItems;
        view.onFindFeedItemsSuccess(feedItems.getItems(), clear);
        requesting = false;
    }

    @Override
    public void onFindFeedItemsFailure(String message) {
        view.showSnackBar(message);
        requesting = false;
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

    @Override
    public void addScrollListener(RecyclerView recyclerView) {
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                    int pos = layoutManager.findLastCompletelyVisibleItemPosition();
                    int numItems = recyclerView.getAdapter().getItemCount();
                    if (pos >= numItems - 2 && !requesting) {
                        requesting = true;
                        loadFeedItems(feedItems.getPage(), feedItems.getTimeMillis(), false);
                    }
                }
            });

        }
    }
}
