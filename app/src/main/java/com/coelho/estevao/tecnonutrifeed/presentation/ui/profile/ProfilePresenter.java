package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.coelho.estevao.tecnonutrifeed.domain.entity.ProfileRequest;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.item.FeedItemActivity;

/**
 * Created by estevao on 06/11/17.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private final ProfileContract.Model model;
    private ProfileContract.View view;

    public ProfilePresenter() {
        this.model = new ProfileModel(this);

    }

    @Override
    public void onAttachView(ProfileContract.View view) {
        this.view = view;

    }

    @Override
    public void getExtras(Bundle extras) {
        if (extras != null) {
            Profile profile = extras.getParcelable("PROFILE");
            view.bindProfileFields(profile);
            model.findProfileInformation(profile);
        }
    }

    @Override
    public void onMiniItemClick(Item item) {
        Intent goToProfileActivity = new Intent(view.getActivityFromView(), FeedItemActivity.class);
        goToProfileActivity.putExtra("ITEM", item);
        view.getActivityFromView().startActivity(goToProfileActivity);
    }

    @Override
    public void onFindProfileInformationSuccess(ProfileRequest body) {
        view.onFindProfileItemsSuccess(body.getItems());
    }

    @Override
    public void onFindProfileInformationFailure(String message) {
        view.showSnackBar(message);
    }


}
