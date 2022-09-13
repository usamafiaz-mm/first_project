package com.example.first_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.first_project.R;
import com.example.first_project.model.Message;
import com.example.first_project.network_service.*;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDemoScreen1 extends AppCompatActivity {
    Button btnChangeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_demo_screen1);
        btnChangeScreen = findViewById(R.id.getDemoScreen1button);


        Executor executor = Runnable::run;

        executor.execute(() -> OnCreateAPI.getClient().getMessage().enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Toast.makeText(GetDemoScreen1.this ,response.body().getMessage(), Toast.LENGTH_LONG ).show();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                t.printStackTrace();

            }
        }));



        btnChangeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetDemoScreen1.this, GetDemoScreen2.class);
                startActivity(intent);
            }


        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
new Executor() {
    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }
}.execute(new Runnable() {
    @Override
    public void run() {
        OnBackAPI.getClient().getMessage().enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Toast.makeText(GetDemoScreen1.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
});



    }
}