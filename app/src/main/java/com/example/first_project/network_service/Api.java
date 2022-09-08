package com.example.first_project.network_service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static QuoteInterface getClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor()).build();
        // change your base URL
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://programming-quotes-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).client(client) //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        QuoteInterface api = adapter.create(QuoteInterface.class);
        return api; // return the APIInterface object
    }
}