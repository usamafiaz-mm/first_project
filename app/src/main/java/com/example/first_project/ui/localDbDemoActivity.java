package com.example.first_project.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.first_project.R;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.model.UserModel;
import com.example.first_project.local_db_example.network_services.LoginAPI;
import com.example.first_project.local_db_example.network_services.LoginModel;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class localDbDemoActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtPassword, edtEmail;
    String email, password;
    private HashMap<String, String> data;
    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_db_demo_acitivity);
        btnLogin = findViewById(R.id.login_btn_db);
        edtEmail = findViewById(R.id.email_db);
        edtPassword = findViewById(R.id.password_db);
        bar = findViewById(R.id.bar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {


//                        bar.show();
                System.err.println("Disabling Button");
                bar.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.GONE);

                Executors.newSingleThreadExecutor().execute(() -> {
                    CompletableFuture<HashMap<String, String>> completableFuture = CompletableFuture.supplyAsync(() -> {
                        try {
                            return fetchDataFromApi();
                        } catch (SocketTimeoutException st) {
                            runOnUiThread(() -> {
                                System.out.println("Dismissing Dialog 1");
                                bar.setVisibility(View.GONE);

                                new AlertDialog.Builder(localDbDemoActivity.this).setTitle("TIME OUT").setMessage(st.getMessage()).show();
                            });

                            return null;

                        } catch (IOException e) {
                            System.out.println("Dismissing Dialog 2");

                            bar.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                            return null;

                        }
                    });
                    while (!completableFuture.isDone()) {
                        System.out.println("CompletableFuture is not finished yet...");
                    }
                    try {
                        data = completableFuture.get();
                        if (data == null) {
                            System.out.println("Dismissing Dialog 3");

                            runOnUiThread(() -> {
                                bar.setVisibility(View.GONE);
                                btnLogin.setVisibility(View.VISIBLE);
                            });

                            Toast.makeText(localDbDemoActivity.this, "Data is null", Toast.LENGTH_LONG).show();
                        } else {
                            insertDataToDB(data);


                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });


            }
        });


    }


    private HashMap<String, String> fetchDataFromApi() throws IOException {
        data = new HashMap<>();

        if (validateData()) {

            Response<LoginModel> response = LoginAPI.getClient().getResults(email, password).execute();
            assert response.body() != null;
            String dbEmail = response.body().getEmail();
            String dbName = response.body().getName();
            String responseData = response.toString();
            System.err.println(email);
            System.err.println(dbName);
            System.err.println(dbName);
            System.err.println(dbName);
            data.put("data", responseData);
            data.put("name", dbName);
            data.put("email", dbEmail);

            return data;


        }
        return null;

    }

    void insertDataToDB(HashMap<String, String> data) {


        Executors.newSingleThreadExecutor().execute(() -> {
            System.err.println("INSERTING DATA TO DB");
            UserModel userModel = new UserModel();
            userModel.setName(data.get("name"));
            userModel.setEmail(data.get("email"));

            userModel.setData(data.get("data"));
            System.err.println("INSERTING DATA TO DB");
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .userDao()
                    .insert(userModel);
            System.out.println("Dismissing Dialog 4");

            runOnUiThread(() -> {
                bar.setVisibility(View.GONE);
                btnLogin.setVisibility(View.VISIBLE);
            });
        });


    }

    private Boolean validateData() {
        System.err.println("VALIDATING DATA");
        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        boolean check = true;

        if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            edtEmail.setError("Please enter a proper email");
            edtEmail.requestFocus();
            check = false;
        }
        if (password.isEmpty()) {
            edtPassword.setError("Please Enter password");
            edtPassword.requestFocus();
            check = false;
        }
        return check;
    }


    private void executorMethod() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            edtEmail.setError("Please enter a proper email");
            edtEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            edtPassword.setError("Please Enter password");
            edtPassword.requestFocus();
            return;
        }

        Executors.newSingleThreadExecutor().execute(() -> {

            LoginAPI.getClient().getResults(email, password).enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    String dbEmail = response.body().getEmail();
                    String dbName = response.body().getName();
                    String data = response.toString();
                    System.err.println(email);
                    System.err.println(dbName);

                    UserModel userModel = new UserModel();
                    userModel.setEmail(email);
                    userModel.setName(dbName);
                    userModel.setData(data);

                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {

                            System.err.println("EXECUTING QUERRY");
                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                                    .userDao()
                                    .insert(userModel);
                        }
                    });

//                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
//                            .userDao()
//                            .insert(userModel);

                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        });
    }


}