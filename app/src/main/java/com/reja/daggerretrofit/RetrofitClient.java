package com.reja.daggerretrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Reja on 23,January,2019
 * Jakarta, Indonesia.
 */
public class RetrofitClient {
    private static Retrofit retrofit;

    /**
     * Create Retrofit instance
     *
     * @param okHttpClient
     * @return
     */
    public static Retrofit getRetrofitInstance(OkHttpClient okHttpClient) {
        String url = "https://developers.zomato.com/api/v2.1/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    /**
     * Create Retrofit instance
     *
     * @param okHttpClient
     * @param url
     * @return
     */
    public Retrofit getRetrofitInstance(OkHttpClient okHttpClient, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
