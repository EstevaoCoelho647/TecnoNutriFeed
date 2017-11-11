package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

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
    Profile profile;
    private boolean requesting;
    private ProfileRequest body;

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
            profile = extras.getParcelable("PROFILE");
            view.bindProfileFields(profile);
            findProfileInformation(null, null, true);
        }
    }

    @Override
    public void findProfileInformation(Integer p, Long t, boolean clear) {
        model.findProfileInformation(profile, p, t, clear);
    }

    @Override
    public void onMiniItemClick(Item item) {
        Intent goToProfileActivity = new Intent(view.getActivityFromView(), FeedItemActivity.class);
        goToProfileActivity.putExtra("ITEM", item);
        view.getActivityFromView().startActivity(goToProfileActivity);
    }

    @Override
    public void onFindProfileInformationSuccess(ProfileRequest body, boolean clear) {
        this.body = body;
        view.onFindProfileItemsSuccess(body.getItems(), clear);
        requesting = false;
    }

    @Override
    public void onFindProfileInformationFailure(String message) {
        view.showSnackBar(message);
        requesting = false;
    }

    @Override
    public void addScrollListener(RecyclerView recyclerViewUserItems) {
        if (recyclerViewUserItems != null) {
            recyclerViewUserItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    StaggeredGridLayoutManager layoutManager = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager());

                    int totalItemCount = layoutManager.getItemCount();
                    int[] lastPositions = new int[layoutManager.getSpanCount()];
                    lastPositions = layoutManager.findLastCompletelyVisibleItemPositions(lastPositions);
                    int lastVisibleItem = Math.max(lastPositions[0], lastPositions[2]);//findMax(lastPositions);

                    if (totalItemCount <= lastVisibleItem+6 && !requesting) {
                        requesting = true;
                        findProfileInformation(body.getPage(), body.getTimeMillis(), false);
                    }
                }
            });

        }
    }


}
