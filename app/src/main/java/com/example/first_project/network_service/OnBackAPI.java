package com.example.first_project.network_service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnBackAPI {

    public static OnBackInterface getClient() {

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://192.168.10.193:3000/")
                .addConverterFactory(GsonConverterFactory.create()) //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        OnBackInterface api = adapter.create(OnBackInterface.class);


        return api;
    }

}
