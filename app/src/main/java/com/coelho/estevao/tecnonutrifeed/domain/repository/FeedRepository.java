package com.coelho.estevao.tecnonutrifeed.domain.repository;

import android.util.Log;

import com.coelho.estevao.tecnonutrifeed.domain.entity.FeedItems;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.main.MainContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedRepository {

    private final APIInterface requestService;

    public FeedRepository() {
        requestService = APIClient.ServiceGenerator.createService(APIInterface.class);
    }

    public void findFeedItems(final MainContract.Presenter presenter) {
        Log.d("FeedRepository", "findind Feed Items");
        requestService.findFeedItems().enqueue(new Callback<FeedItems>() {
            @Override
            public void onResponse(Call<FeedItems> call, Response<FeedItems> response) {
                if (response.isSuccessful()) {
                    Log.d("findFeedItems", "success");
                    presenter.onFindFeedItemsSuccess(response.body().getItems());
                } else {
                    Log.d("findFeedItems", "failure");
                    presenter.onFindFeedItemsFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<FeedItems> call, Throwable t) {
                Log.d("onFailure", "failure");
                presenter.onFindFeedItemsFailure(t.getMessage());
            }
        });
    }
}
