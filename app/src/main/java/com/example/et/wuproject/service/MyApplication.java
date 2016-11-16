package com.example.et.wuproject.service;

import android.app.Application;


import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * <p>
 * </p>
 *
 * @author:ET
 * @since：2016/11/4 14
 * @version: 2.5
 */

public class MyApplication extends Application {

    public static final String EAPP="MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
          String index=";dfds";

        Logger.init(EAPP).hideThreadInfo().methodCount(3);
        Realm.init(this);
        RealmConfiguration config=new RealmConfiguration.Builder()
                 .name("myRealm.realm")
                 .deleteRealmIfMigrationNeeded()
                .build();
         Realm.setDefaultConfiguration(config);
    }
}
