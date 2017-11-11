package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.coelho.estevao.tecnonutrifeed.domain.entity.ProfileRequest;

import java.util.List;

/**
 * Created by estevao on 06/11/17.
 */

public class ProfileContract {

    public interface Model {

        void findProfileInformation(Profile profile, Integer p, Long t, boolean clear);
    }

    public interface View {

        Activity getActivityFromView();

        void showSnackBar(String message);

        void onFindProfileItemsSuccess(List<Item> list, boolean clear);

        void bindProfileFields(Profile profile);
    }

    public interface Presenter {
        void onAttachView(View view);

        void getExtras(Bundle extras);

        void findProfileInformation(Integer p, Long t, boolean b);

        void onMiniItemClick(Item item);

        void onFindProfileInformationSuccess(ProfileRequest body, boolean clear);

        void onFindProfileInformationFailure(String message);

        void addScrollListener(RecyclerView recyclerViewUserItems);
    }
}
