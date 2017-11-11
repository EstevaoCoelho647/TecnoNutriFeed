package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import android.app.Activity;
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
    }

    public interface View {

        Activity getActivityFromView();

        void showSnackBar(String message);

        void onFindFeedItemsSuccess(List<Item> list, boolean clear);
    }

    public interface Presenter {
        void onAttachView(View view);

        void loadFeedItems(Integer p, Long t, boolean clear);

        void onFindFeedItemsSuccess(FeedItems body, boolean clear);

        void onFindFeedItemsFailure(String message);

        void onItemClick(Item item);

        void onProfileClick(Profile profile);

        void addScrollListener(RecyclerView recyclerView);
    }
}
