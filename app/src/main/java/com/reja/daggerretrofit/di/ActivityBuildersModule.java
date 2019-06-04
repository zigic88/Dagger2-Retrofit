package com.reja.daggerretrofit.di;

import com.reja.daggerretrofit.MainActivity;
import com.reja.daggerretrofit.di.module.AuthModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Reja on 09,May,2019
 * Jakarta, Indonesia.
 */

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
            modules = AuthModule.class
    )
    abstract MainActivity contributeMainActivity();

}
