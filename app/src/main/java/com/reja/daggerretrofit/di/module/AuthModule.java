package com.reja.daggerretrofit.di.module;

import com.reja.daggerretrofit.CustomInterceptor;
import com.reja.daggerretrofit.OkHttpUtils;
import com.reja.daggerretrofit.api.ApiService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Reja on 04,June,2019
 * Jakarta, Indonesia.
 */
@Module
public class AuthModule {
    private static String BASE_URL = "https://developers.zomato.com/api/v2.1/";

    @Named("okhttp-auth")
    @Provides
    static OkHttpClient provideokHttpClientAuth(Cache cache) {
        Map<String, String> header = new HashMap<>();
        header.put("user-key", "c78b14ad6c44bf00267839f253bdf65d");
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();
        okHttpUtils.initiate(new CustomInterceptor(header));
        return okHttpUtils.getClient();
    }

    @Named("retrofit-cat")
    @Provides
    static Retrofit provideRetrofitInstance(@Named("okhttp-auth") OkHttpClient okHttpClient) {
        String url = BASE_URL;
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    static ApiService provideAuthApi(@Named("retrofit-cat") Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
