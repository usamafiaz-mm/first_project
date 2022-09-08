package com.example.first_project.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first_project.R;
import com.example.first_project.model.CustomResponse;
import com.example.first_project.network_service.CustomResponseApi;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeOutActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeout_activity);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(TimeOutActivity.this);
                progressDialog.setCancelable(false); // set cancelable to false
                progressDialog.setMessage("Please Wait"); // set message
                progressDialog.show();
                CustomResponseApi.getClient().getResults().enqueue(new Callback<CustomResponse>() {
                    @Override
                    public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                        System.err.println(response);
                        System.err.println(response.headers());
                        System.err.println("Inside Success");
                        System.out.println(response.body().getActiveInd());
                        System.out.println(response.body().getCreatedDate());
                        System.out.println(response.body().getVersionName());
                        System.out.println(response.body().getVersionID());
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<CustomResponse> call, Throwable t) {
                        progressDialog.dismiss();

                        System.err.println("INSIDE ERROR");
                        System.err.println(call);
                        t.printStackTrace();

                        if (t instanceof SocketTimeoutException) {
                            System.out.println("Socket Time out. Please try again.");


                            new AlertDialog.Builder(TimeOutActivity.this).setTitle("TIME OUT").setMessage(t.getMessage()).show();
                        }

                    }
                });
            }
        });

    }
}