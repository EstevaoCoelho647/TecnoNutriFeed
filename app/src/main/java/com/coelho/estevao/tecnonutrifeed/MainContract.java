package com.coelho.estevao.tecnonutrifeed;

import java.util.List;

/**
 * Created by estevao on 06/11/17.
 */

public class MainContract {

    interface Model {

        void requestFeedItems();
    }

    interface View {

        void showSnackBar(String message);

        void onFindFeedItemsSuccess(List<Item> list);
    }

    interface Presenter {
        void onAttachView(View view);

        void loadFeedItems();

        void onFindFeedItemsSuccess(List<Item> body);

        void onFindFeedItemsFailure(String message);
    }
}
