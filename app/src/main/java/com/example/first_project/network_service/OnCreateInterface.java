package com.example.first_project.network_service;

import com.example.first_project.model.Message;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OnCreateInterface {
    @GET("/oncreate")
    public Call<Message> getMessage();
}
