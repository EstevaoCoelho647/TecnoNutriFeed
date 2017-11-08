package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 07/11/17.
 */

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {


    @BindView(R.id.imageViewUser)
    ImageView imageViewUser;
    @BindView(R.id.textViewUserName)
    TextView textViewUserName;
    @BindView(R.id.textViewUserGoal)
    TextView textViewUserGoal;
    @BindView(R.id.recyclerViewUserItems)
    RecyclerView recyclerViewUserItems;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        ProfilePresenter profilePresenter = new ProfilePresenter();
        profilePresenter.onAttachView(this);

        profilePresenter.getExtras(getIntent().getExtras());

    }

    @Override
    public Activity getActivityFromView() {
        return this;
    }

    @Override
    public void showSnackBar(String message) {

    }

    @Override
    public void onFindFeedItemsSuccess(List<Item> list) {

    }

    @Override
    public void bindProfileFields(Profile profile) {
        textViewUserName.setText(profile.getName());
        textViewUserGoal.setText(profile.getGeneralGoal());

        Picasso.with(this)
                .load(profile.getImage()).placeholder(R.drawable.ic_person)
                .into(imageViewUser);

    }
}
