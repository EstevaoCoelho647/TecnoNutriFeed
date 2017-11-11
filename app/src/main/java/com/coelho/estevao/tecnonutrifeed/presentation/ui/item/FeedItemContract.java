package com.coelho.estevao.tecnonutrifeed.presentation.ui.item;

import android.app.Activity;
import android.os.Bundle;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItemContract {

    public interface Model {

        void requestItemInformation(Item item);
    }

    public interface View {

        Activity getActivityFromView();

        void showSnackBar(String message);

        void onFindFeedItemInformationSuccess(Item item);

        void bindItemFields(Item item);
    }

    public interface Presenter {
        void onAttachView(View view);

        void getExtras(Bundle extras);

        void requestItemInformation();

        void onFindItemInformationSuccess(Item items);

        void onFindItemInformationFailure(String message);
    }
}
