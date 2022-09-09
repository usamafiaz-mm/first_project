package com.example.first_project.network_service;

import com.example.first_project.model.PostResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfacePost {

    @FormUrlEncoded
    @POST("postrecord")
    public Call<PostResponse> login(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password);
}
