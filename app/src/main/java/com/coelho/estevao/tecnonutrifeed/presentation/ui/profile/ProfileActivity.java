package com.coelho.estevao.tecnonutrifeed.presentation.ui.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Profile;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.base.BaseReloadActivity;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.profile.adapter.MiniItemAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 07/11/17.
 */

public class ProfileActivity extends BaseReloadActivity implements ProfileContract.View {
    @BindView(R.id.imageViewUser)
    ImageView imageViewUser;
    @BindView(R.id.textViewUserName)
    TextView textViewUserName;
    @BindView(R.id.textViewUserGoal)
    TextView textViewUserGoal;
    @BindView(R.id.recyclerViewUserItems)
    RecyclerView recyclerViewUserItems;
    MiniItemAdapter miniItemAdapter;

    ProfilePresenter profilePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        profilePresenter = new ProfilePresenter();
        profilePresenter.onAttachView(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewUserItems.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        miniItemAdapter = new MiniItemAdapter(profilePresenter);
        recyclerViewUserItems.setAdapter(miniItemAdapter);

        profilePresenter.getExtras(getIntent().getExtras());

        profilePresenter.addScrollListener(recyclerViewUserItems);
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
        Snackbar.make(recyclerViewUserItems, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFindProfileItemsSuccess(List<Item> list, boolean clear) {
        miniItemAdapter.setItems(list, clear);
        setRefreshing(false);
    }

    @Override
    public void bindProfileFields(Profile profile) {
        textViewUserName.setText(profile.getName());
        textViewUserGoal.setText(profile.getGeneralGoal());

        Picasso.with(this)
                .load(profile.getImage()).placeholder(R.drawable.ic_person)
                .into(imageViewUser);

    }

    @Override
    public void onRefresh() {
        profilePresenter.findProfileInformation(null, null, true);
    }
}
