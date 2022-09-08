package com.example.first_project.network_service;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("appid", "practicing-retrofit-interceptor")
                .addHeader("deviceplatform", "android")
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
                .build();
        okhttp3.Response response = chain.proceed(request);
        System.err.println(response);
        System.err.println(request.headers());

        Log.e( "intercept: "," res " + response.header("appid") );
        return response;
    }
}