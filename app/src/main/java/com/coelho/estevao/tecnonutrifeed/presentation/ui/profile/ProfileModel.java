package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

/**
 * Created by estevao on 06/11/17.
 */

public class ProfileModel implements ProfileContract.Model {
    ProfileContract.Presenter presenter;

    public ProfileModel(ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
