package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import android.app.Activity;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;

import java.util.List;

/**
 * Created by estevao on 06/11/17.
 */

public class MainContract {

    public interface Model {

        void requestFeedItems();
    }

    public interface View {

        Activity getActivityFromView();

        void showSnackBar(String message);

        void onFindFeedItemsSuccess(List<Item> list);
    }

    public interface Presenter {
        void onAttachView(View view);

        void loadFeedItems();

        void onFindFeedItemsSuccess(List<Item> body);

        void onFindFeedItemsFailure(String message);

        void onItemClick(Item item);

        void onProfileClick(Profile profile);
    }
}
