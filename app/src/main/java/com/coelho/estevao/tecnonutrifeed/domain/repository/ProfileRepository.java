package com.coelho.estevao.tecnonutrifeed.domain.repository;

import android.util.Log;

import com.coelho.estevao.tecnonutrifeed.domain.entity.ProfileRequest;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.ProfileContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by estevao on 06/11/17.
 */

public class ProfileRepository {

    private final APIInterface requestService;

    public ProfileRepository() {
        requestService = APIClient.ServiceGenerator.createService(APIInterface.class);
    }

    public void findProfileInformation(final ProfileContract.Presenter presenter, Long id, Integer p, Long t, final boolean clear) {
        Log.d("ProfileRepository", "findind profile information");
        requestService.findProfileInformation(id, p, t).enqueue(new Callback<ProfileRequest>() {
            @Override
            public void onResponse(Call<ProfileRequest> call, Response<ProfileRequest> response) {
                if (response.isSuccessful()) {
                    Log.d("findProfileInformation", "success");
                    presenter.onFindProfileInformationSuccess(response.body(), clear);
                } else {
                    Log.d("findProfileInformation", "failure");
                    presenter.onFindProfileInformationFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfileRequest> call, Throwable t) {
                Log.d("findProfileInformation", "failure");
                presenter.onFindProfileInformationFailure(t.getMessage());
            }
        });
    }
}
