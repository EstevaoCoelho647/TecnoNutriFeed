package com.coelho.estevao.tecnonutrifeed.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.baoyz.widget.PullRefreshLayout;
import com.coelho.estevao.tecnonutrifeed.BuildConfig;
import com.coelho.estevao.tecnonutrifeed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
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
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private static final String SHOW_AD_KEY = "show_ads";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        adview.setAdUnitId(BuildConfig.AD_UNIT_ID); // Enter your Ad Unit ID from www.mopub.com

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);


        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseRemoteConfig.fetch(0)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i("fetch", "success");
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Log.i("fetch", "failed");
                        }
                        showAd();
                    }
                });
    }

    private void showAd() {
        boolean aBoolean = mFirebaseRemoteConfig.getBoolean(SHOW_AD_KEY);
        if (aBoolean) {
            adview.setVisibility(View.VISIBLE);
            adview.loadAd();
        } else {
            adview.setVisibility(View.GONE);
            adview.destroy();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adview.destroy();
    }

    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }
}
