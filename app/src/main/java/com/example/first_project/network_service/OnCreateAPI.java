package com.example.first_project.network_service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnCreateAPI {

    public static OnCreateInterface getClient() {

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://192.168.10.193:3000/")
                .addConverterFactory(GsonConverterFactory.create()) //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        OnCreateInterface api = adapter.create(OnCreateInterface.class);


        return api;
    }




}
