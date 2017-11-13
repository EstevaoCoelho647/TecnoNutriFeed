package com.coelho.estevao.tecnonutrifeed.presentation.ui.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Food;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.util.Constants;

/**
 * Created by estevao on 06/11/17.
 */

public class FeedItemPresenter implements FeedItemContract.Presenter {

    private final FeedItemContract.Model model;
    private FeedItemContract.View view;
    Item item;
    int itemPosition;

    public FeedItemPresenter() {
        this.model = new FeedItemModel(this);

    }

    @Override
    public void onAttachView(FeedItemContract.View view) {
        this.view = view;

    }

    @Override
    public void getExtras(Bundle extras) {
        if (extras != null) {
            item = extras.getParcelable(Constants.BUNDLE_ITEM_NAME);
            itemPosition = extras.getInt(Constants.BUNDLE_POSITION_NAME);

            view.bindItemFields(item);
            requestItemInformation();
        }
    }
    @Override
    public void requestItemInformation() {
        model.requestItemInformation(item);
    }

    @Override
    public void onFindItemInformationSuccess(Item item) {
        Food food = new Food();
        food.setDescription(view.getActivityFromView().getString(R.string.label_food_description));
        food.setEnergy(item.getEnergy());
        food.setCarbohydrate(item.getCarbohydrate());
        food.setFat(item.getFat());
        food.setProtein(item.getProtein());
        item.getFoods().add(food);
        view.onFindFeedItemInformationSuccess(item);
    }

    @Override
    public void onFindItemInformationFailure() {
        view.showSnackBar(view.getActivityFromView().getResources().getString(R.string.request_error_item));
    }

    @Override
    public void onButtonLikeClicked(Item item, boolean liked) {
        model.setItemLiked(item, liked);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = view.getActivityFromView().getIntent();
        returnIntent.putExtra(Constants.BUNDLE_POSITION_NAME, itemPosition);
        view.getActivityFromView().setResult(Activity.RESULT_OK, returnIntent);
        view.getActivityFromView().finish();
    }
}
