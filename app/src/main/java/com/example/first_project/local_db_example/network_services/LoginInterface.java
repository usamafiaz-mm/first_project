package com.example.first_project.local_db_example.network_services;

import com.example.first_project.model.Quote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginInterface {
    @GET("/login")
    public Call<LoginModel> getResults(   @Query("email") String email,
                                          @Query("password") String password);
}
