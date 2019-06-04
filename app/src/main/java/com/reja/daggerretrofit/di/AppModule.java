package com.reja.daggerretrofit.di;

import android.app.Application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by Reja on 04,June,2019
 * Jakarta, Indonesia.
 */
@Module
public class AppModule {


    @Singleton
    @Provides
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Named("okhttp-def")
    @Singleton
    @Provides
    static OkHttpClient provideokHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

}
