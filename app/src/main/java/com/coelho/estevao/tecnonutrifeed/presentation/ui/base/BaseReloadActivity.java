package com.coelho.estevao.tecnonutrifeed.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baoyz.widget.PullRefreshLayout;
import com.coelho.estevao.tecnonutrifeed.BuildConfig;
import com.coelho.estevao.tecnonutrifeed.R;
import com.mopub.mobileads.MoPubView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 11/11/17.
 */

public abstract class BaseReloadActivity extends AppCompatActivity implements PullRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;
    @BindView(R.id.adview)
    MoPubView adview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        adview.setAdUnitId(BuildConfig.AD_UNIT_ID); // Enter your Ad Unit ID from www.mopub.com
        adview.loadAd();

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }
}
