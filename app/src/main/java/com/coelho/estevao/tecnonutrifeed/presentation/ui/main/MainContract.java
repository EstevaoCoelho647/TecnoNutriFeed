package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.coelho.estevao.tecnonutrifeed.domain.entity.FeedItems;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;

import java.util.List;

/**
 * Created by estevao on 06/11/17.
 */

public class MainContract {

    public interface Model {

        void requestFeedItems(Integer p, Long t, boolean clear);

        void saveOrUpdateDatabase(FeedItems feedItems);

        void setItemLiked(Item item, boolean liked);
    }

    public interface View {

        Activity getActivityFromView();

        void showSnackBar(String message);

        void onFindFeedItemsSuccess(List<Item> list, boolean clear);

        void notifyItemOnAdapter(int position);
    }

    public interface Presenter {
        void onAttachView(View view);

        void loadFeedItems(Integer p, Long t, boolean clear);

        void onFindFeedItemsSuccess(FeedItems body, boolean clear);

        void onFindFeedItemsFailure();

        void onItemClick(Item item, int position);

        void onProfileClick(Profile profile);

        void addScrollListener(RecyclerView recyclerView);

        void onButtonLikeClicked(Item item, boolean liked);

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
