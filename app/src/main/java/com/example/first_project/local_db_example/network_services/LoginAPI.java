package com.example.first_project.local_db_example.network_services;

import com.example.first_project.network_service.OnBackInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPI {
    public static LoginInterface getClient() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://192.168.10.193:3000/").client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        LoginInterface api = adapter.create(LoginInterface.class);


        return  api;
    }

}
