package com.reja.daggerretrofit.api;

import com.reja.daggerretrofit.model.Category;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Reja on 04,June,2019
 * Jakarta, Indonesia.
 */
public interface ApiService {
    @GET("categories")
    Call<Category> getAllCategories();
}
