package com.example.isabe.ramemichiraku;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by isabe on 05/03/2018.
 */

public class IchirakuApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder().name("ichirakurealm.realm").build();
        Realm.setDefaultConfiguration(config);

    }

}
