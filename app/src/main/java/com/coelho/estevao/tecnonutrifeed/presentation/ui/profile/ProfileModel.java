package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.coelho.estevao.tecnonutrifeed.domain.repository.ProfileRepository;

/**
 * Created by estevao on 06/11/17.
 */

public class ProfileModel implements ProfileContract.Model {
    ProfileContract.Presenter presenter;

    public ProfileModel(ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void findProfileInformation(Profile profile) {
        ProfileRepository profileRepository = new ProfileRepository();
        profileRepository.findProfileInformation(presenter, profile.getId());
    }
}
