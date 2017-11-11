package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coelho.estevao.tecnonutrifeed.R;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.base.BaseReloadActivity;
import com.coelho.estevao.tecnonutrifeed.presentation.ui.main.adapter.FeedItemAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseReloadActivity implements MainContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    FeedItemAdapter feedItemAdapter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        mainPresenter = new MainPresenter();
        mainPresenter.onAttachView(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        feedItemAdapter = new FeedItemAdapter(mainPresenter);
        recyclerView.setAdapter(feedItemAdapter);

        mainPresenter.addScrollListener(recyclerView);

        mainPresenter.loadFeedItems(null, null, true);
    }

    @Override
    public Activity getActivityFromView() {
        return this;
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void onFindFeedItemsSuccess(List<Item> list, boolean clear) {
        feedItemAdapter.setItems(list, clear);
        setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mainPresenter.loadFeedItems(null, null, true);
    }
}
