package com.example.first_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first_project.R;
import com.example.first_project.model.Quote;
import com.example.first_project.network_service.QuoteAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterceptorActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interceptor);
        button =findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuoteAPI.getClient().getResults().enqueue(new Callback<Quote>() {
                    @Override
                    public void onResponse(Call<Quote> call, Response<Quote> response) {
                        System.out.println(" in on response \n\n\n" +  response.headers());
                        System.out.println(response.body().getEn());
                    }

                    @Override
                    public void onFailure(Call<Quote> call, Throwable t) {

                    }
                });
            }
        });

    }
}