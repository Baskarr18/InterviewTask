package com.example.root.jsonparsingwithrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by root on 6/8/17.
 */

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(getApplicationContext());

        // create your Realm configuration
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);
    }
}
