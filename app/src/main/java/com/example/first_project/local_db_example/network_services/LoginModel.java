package com.example.first_project.local_db_example.network_services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("email")
    @Expose
    private String email;


    @SerializedName("name")
    @Expose
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
