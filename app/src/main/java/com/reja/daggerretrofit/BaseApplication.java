package com.reja.daggerretrofit;

import com.reja.daggerretrofit.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 *
 * Created by Reja on 19,January,2019
 * Jakarta, Indonesia.
 */
public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    /**
     * Base Application Configuration
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
