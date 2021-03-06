package com.coelho.estevao.tecnonutrifeed.presentation.ui.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Meal;
import com.coelho.estevao.tecnonutrifeed.domain.persistence.ItemDatabaseRepository;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.base.BaseReloadActivity;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.item.adapter.FoodItemAdapter;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.ProfileActivity;
import com.coelho.estevao.tecnonutrifeed.util.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 07/11/17.
 */

public class FeedItemActivity extends BaseReloadActivity implements FeedItemContract.View {

    @BindView(R.id.textViewDescription)
    public TextView textViewDescription;
    @BindView(R.id.imageViewuserImage)
    public ImageView imageViewuserImage;
    @BindView(R.id.textViewUserName)
    public TextView textViewUserName;
    @BindView(R.id.textViewUserSubtitle)
    public TextView textViewUserSubtitle;
    @BindView(R.id.postImageView)
    public ImageView postImageView;
    @BindView(R.id.textViewKcal)
    public TextView textViewKcal;
    @BindView(R.id.viewHolderProfile)
    public RelativeLayout viewHolderProfile;
    @BindView(R.id.viewHolderItem)
    public LinearLayout viewHolderItem;
    @BindView(R.id.recyclerViewFoods)
    public RecyclerView recyclerViewFoods;
    @BindView(R.id.buttonLike)
    public ImageView buttonLike;
    @BindView(R.id.imageViewFavorite)
    public ImageView imageViewFavorite;
    @BindView(R.id.textViewMeal)
    public TextView textViewMeal;

    FoodItemAdapter foodItemAdapter;
    FeedItemPresenter feedItemPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_feed_item);
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        feedItemPresenter = new FeedItemPresenter();
        feedItemPresenter.onAttachView(this);

        feedItemPresenter.getExtras(getIntent().getExtras());

        recyclerViewFoods.setLayoutManager(new LinearLayoutManager(this));
        foodItemAdapter = new FoodItemAdapter();
        recyclerViewFoods.setAdapter(foodItemAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Activity getActivityFromView() {
        return this;
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(viewHolderItem, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFindFeedItemInformationSuccess(Item item) {
        foodItemAdapter.setItems(item.getFoods());
        setRefreshing(false);
    }

    @Override
    public void bindItemFields(final Item item) {
        textViewDescription.setText(item.getDate());
        textViewKcal.setText(item.getEnergy() + " Kcal");

        Picasso.with(postImageView.getContext())
                .load(item.getImage()).placeholder(R.drawable.ic_local_pizza)
                .into(postImageView);

        Picasso.with(imageViewuserImage.getContext())
                .load(item.getProfile().getImage()).placeholder(R.drawable.ic_person)
                .into(imageViewuserImage);

        textViewUserName.setText(item.getProfile().getName());
        if (item.getProfile().getGeneralGoal() != null) {
            textViewUserSubtitle.setVisibility(View.VISIBLE);
            textViewUserSubtitle.setText(item.getProfile().getGeneralGoal());
        } else
            textViewUserSubtitle.setVisibility(View.GONE);

        Item itemById = ItemDatabaseRepository.getItemById(item.getFeedHash());
        if (itemById != null) {
            boolean alreadyLiked = itemById.isLiked();
            item.setLiked(alreadyLiked);
            if (alreadyLiked) {
                imageViewFavorite.setScaleX(1);
                imageViewFavorite.setScaleY(1);
            } else {
                imageViewFavorite.setScaleX(0);
                imageViewFavorite.setScaleY(0);
            }
        }
        textViewMeal.setText(Meal.values()[item.getMeal()].getValue());

        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isLiked()) {
                    imageViewFavorite.animate().scaleX(0).scaleY(0).start();
                    item.setLiked(false);
                    feedItemPresenter.onButtonLikeClicked(item, false);
                } else {
                    imageViewFavorite.animate().scaleX(1).scaleY(1).start();
                    item.setLiked(true);
                    feedItemPresenter.onButtonLikeClicked(item, true);
                }
            }
        });

        viewHolderProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToProfileActivity = new Intent(FeedItemActivity.this, ProfileActivity.class);
                goToProfileActivity.putExtra(Constants.BUNDLE_PROFILE_NAME, item.getProfile());
                startActivity(goToProfileActivity);
            }
        });
    }

    @Override
    public void onRefresh() {
        feedItemPresenter.requestItemInformation();
    }

    @Override
    public void onBackPressed() {
        feedItemPresenter.onBackPressed();
    }
}
