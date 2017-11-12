package com.coelho.estevao.tecnonutrifeed.domain.repository;

import android.util.Log;

import com.coelho.estevao.tecnonutrifeed.domain.entity.FeedItem;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.item.FeedItemContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by estevao on 06/11/17.
 */

public class ItemRepository {

    private final APIInterface requestService;

    public ItemRepository() {
        requestService = APIClient.ServiceGenerator.createService(APIInterface.class);
    }

    public void findItemInformation(final FeedItemContract.Presenter presenter, String itemHash) {
        Log.d("ItemRepository", "findind Item information");
        requestService.findFeedItem(itemHash).enqueue(new Callback<FeedItem>() {
            @Override
            public void onResponse(Call<FeedItem> call, Response<FeedItem> response) {
                if (response.isSuccessful()) {
                    Log.d("findItemInformation", "success");
                    presenter.onFindItemInformationSuccess(response.body().getItem());
                } else {
                    Log.d("findItemInformation", "failure");
                    presenter.onFindItemInformationFailure();
                }
            }

            @Override
            public void onFailure(Call<FeedItem> call, Throwable t) {
                Log.d("findItemInformation", "failure");
                presenter.onFindItemInformationFailure();
            }
        });
    }
}
