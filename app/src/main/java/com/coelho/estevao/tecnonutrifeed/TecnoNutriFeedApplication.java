package com.coelho.estevao.tecnonutrifeed;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by estevao on 12/11/17.
 */

public class TecnoNutriFeedApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseApp.initializeApp(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
