package com.coelho.estevao.tecnonutrifeed.presentation.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coelho.estevao.tecnonutrifeed.presentation.ui.main.adapter.FeedItemAdapter;
import com.coelho.estevao.tecnonutrifeed.domain.entity.Item;
import com.coelho.estevao.tecnonutrifeed.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    FeedItemAdapter feedItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MainPresenter mainPresenter = new MainPresenter();
        mainPresenter.onAttachView(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        feedItemAdapter = new FeedItemAdapter(mainPresenter);
        recyclerView.setAdapter(feedItemAdapter);


        mainPresenter.loadFeedItems();
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
    public void onFindFeedItemsSuccess(List<Item> list) {
        feedItemAdapter.setItems(list);
    }
}
