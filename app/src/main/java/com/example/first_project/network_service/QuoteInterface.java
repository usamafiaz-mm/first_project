package com.example.first_project.network_service;

import com.example.first_project.model.Quote;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;


public interface QuoteInterface {

    // API's endpoints
    @GET("/quotes/random")
    public Call<Quote> getResults();

// UserListResponse is POJO class to get the data from API, In above method we use List<UserListResponse> because the data in our API is starting from JSONArray and callback is used to get the response from api and it will set it in our POJO class

}