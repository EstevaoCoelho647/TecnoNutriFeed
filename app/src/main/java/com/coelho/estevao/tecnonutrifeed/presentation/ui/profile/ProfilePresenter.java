package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import android.os.Bundle;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;

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
        if (extras != null){
            Profile profile = extras.getParcelable("PROFILE");
            view.bindProfileFields(profile);
        }
    }


}
