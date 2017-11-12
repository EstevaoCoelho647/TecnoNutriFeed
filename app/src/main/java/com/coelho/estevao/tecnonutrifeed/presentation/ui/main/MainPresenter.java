package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.FeedItems;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.item.FeedItemActivity;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.ProfileActivity;
import com.coelho.estevao.tecnonutrifeed.util.Constants;

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
        model.saveOrUpdateDatabase(feedItems);
        requesting = false;
    }

    @Override
    public void onFindFeedItemsFailure() {
        view.showSnackBar(view.getActivityFromView().getResources().getString(R.string.request_error_feed));
        requesting = false;
    }

    @Override
    public void onItemClick(Item item, int position) {
        Intent goToItemViewActivity = new Intent(view.getActivityFromView(), FeedItemActivity.class);
        goToItemViewActivity.putExtra(Constants.BUNDLE_ITEM_NAME, item);
        goToItemViewActivity.putExtra(Constants.BUNDLE_POSITION_NAME, position);
        view.getActivityFromView().startActivityForResult(goToItemViewActivity, Constants.REQUEST_CODE_VIEW_ITEM);
    }

    @Override
    public void onProfileClick(Profile profile) {
        Intent goToProfileActivity = new Intent(view.getActivityFromView(), ProfileActivity.class);
        goToProfileActivity.putExtra(Constants.BUNDLE_PROFILE_NAME, profile);
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
                        loadFeedItems(feedItems.getPage()+1, feedItems.getTimeMillis(), false);
                    }
                }
            });
        }
    }

    @Override
    public void onButtonLikeClicked(Item item, boolean liked) {
        model.setItemLiked(item, liked);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.REQUEST_CODE_VIEW_ITEM) {
                int position = data.getIntExtra(Constants.BUNDLE_POSITION_NAME, 0);
                view.notifyItemOnAdapter(position);
            }
        }
    }
}
