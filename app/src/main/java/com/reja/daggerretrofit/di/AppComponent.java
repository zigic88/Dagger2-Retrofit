package com.reja.daggerretrofit.di;

import android.app.Application;

import com.reja.daggerretrofit.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Reja on 04,June,2019
 * Jakarta, Indonesia.
 */
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class}
)
public interface AppComponent extends AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
