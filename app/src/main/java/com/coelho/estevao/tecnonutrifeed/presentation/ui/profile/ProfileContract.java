package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import android.app.Activity;
import android.os.Bundle;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;

import java.util.List;

/**
 * Created by estevao on 06/11/17.
 */

public class ProfileContract {

    public interface Model {

    }

    public interface View {

        Activity getActivityFromView();

        void showSnackBar(String message);

        void onFindFeedItemsSuccess(List<Item> list);

        void bindProfileFields(Profile profile);
    }

    public interface Presenter {
        void onAttachView(View view);

        void getExtras(Bundle extras);
    }
}
