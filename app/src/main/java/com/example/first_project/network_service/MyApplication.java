package com.example.first_project.network_service;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    ExecutorService executorService = Executors.newFixedThreadPool(4);
}