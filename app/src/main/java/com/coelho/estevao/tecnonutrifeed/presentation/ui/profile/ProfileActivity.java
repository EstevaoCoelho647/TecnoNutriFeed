package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.adapter.MiniItemAdapter;
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
    MiniItemAdapter miniItemAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        ProfilePresenter profilePresenter = new ProfilePresenter();
        profilePresenter.onAttachView(this);

        recyclerViewUserItems.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        miniItemAdapter = new MiniItemAdapter(profilePresenter);
        recyclerViewUserItems.setAdapter(miniItemAdapter);

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
    public void onFindProfileItemsSuccess(List<Item> list) {
        miniItemAdapter.setItems(list);
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
