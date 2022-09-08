package com.example.first_project.network_service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomResponseApi {

    public static CustomResponseInterface getClient() {
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor()).build();
        // change your base URL

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://192.168.10.105/")
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)//Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        CustomResponseInterface api = adapter.create(CustomResponseInterface.class);
        return api; // return the APIInterface object
    }
}