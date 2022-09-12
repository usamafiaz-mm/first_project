package com.example.first_project.network_service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlaskAPIPost {

    public static ApiInterfacePost getClient() {
        // change your base URL
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://192.168.10.193:3000/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()) //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiInterfacePost api = adapter.create(ApiInterfacePost.class);


        return api; // return the APIInterface object
    }

    public static OnCreateInterface gg() {

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://192.168.10.193:3000/")
                .addConverterFactory(GsonConverterFactory.create()) //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        OnCreateInterface api = adapter.create(OnCreateInterface.class);


        return api;
    }




}
