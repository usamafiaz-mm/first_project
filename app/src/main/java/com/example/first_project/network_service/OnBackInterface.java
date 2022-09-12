package com.example.first_project.network_service;

import com.example.first_project.model.Message;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OnBackInterface {
    @GET("/onback")
    public Call<Message> getMessage();
}
