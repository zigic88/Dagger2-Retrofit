package com.reja.daggerretrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.reja.daggerretrofit.api.ApiService;
import com.reja.daggerretrofit.model.Category;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;g

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivity {
    private final String TAG = MainActivity.class.getName();

    @Inject
    @Named("retrofit-cat")
    Retrofit retrofit;

    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callApi(View view) {
        Log.d(TAG, "Call API I");
        long startTime = System.currentTimeMillis();

        Log.d(TAG, retrofit.baseUrl().toString());
        Call<Category> categoryCall = apiService.getAllCategories();
        categoryCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response != null) {
                    Log.d(TAG, response.code()+"" + response.body());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.d(TAG, t.getMessage().toString());
            }
        });

        long estimatedTime = System.currentTimeMillis() - startTime;
        Log.d(TAG, "Total time I : " + estimatedTime);

        callApiWithoutDi();
    }

    private void callApiWithoutDi ( ) {
        Log.d(TAG, "==========================");

        Log.d(TAG, "Call API II");
        long startTime = System.currentTimeMillis();

        Map<String, String> header = new HashMap<>();
        header.put("user-key", "c78b14ad6c44bf00267839f253bdf65d");
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();
        okHttpUtils.initiate(new CustomInterceptor(header));

        ApiService apiService = RetrofitClient.getRetrofitInstance(okHttpUtils.getClient()).create(ApiService.class);
        Call<Category> categoryCall = apiService.getAllCategories();
        categoryCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response != null) {
                    Log.d(TAG, response.code()+"" + response.body());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.d(TAG, t.getMessage().toString());
            }
        });
        long estimatedTime = System.currentTimeMillis() - startTime;
        Log.d(TAG, "Total time II : " + estimatedTime);
    }
}
