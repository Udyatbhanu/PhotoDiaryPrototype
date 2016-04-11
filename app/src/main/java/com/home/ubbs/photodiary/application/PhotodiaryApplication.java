package com.home.ubbs.photodiary.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by udyatbhanu-mac on 7/11/15.
 */
public class PhotodiaryApplication extends Application {

    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
