package com.reja.daggerretrofit;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Reja on 24,January,2019
 * Jakarta, Indonesia.
 */
public class CustomInterceptor implements Interceptor {
    private Map<String, String> headers;

    /**
     * Customer header interceptor
     *
     * @param headers
     */
    public CustomInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Intercept customer header
     *
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        if(this.headers!=null && this.headers.size()>0){
            for (Map.Entry<String, String> map : headers.entrySet()) {
                builder.header(map.getKey(), map.getValue());
            }
        }
        return chain.proceed(builder.build());
    }

}
