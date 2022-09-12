package com.example.first_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.first_project.R;
import com.example.first_project.model.PostResponse;
import com.example.first_project.network_service.FlaskAPIPost;
import com.example.first_project.network_service.OnCreateAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostReqDemoActivity extends AppCompatActivity {
    EditText edtName, edtEmail, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_req_demo);
        edtName = findViewById(R.id.name);
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.err.println("LOGGIN IN");
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                System.out.println(name);
                System.out.println(email);
                System.out.println(password);
                Boolean check = true;
                if (name.equals("")) {
                    edtName.setError("Please Enter Your Name");
                    check = false;
                }
                if (email.equals("") || !email.contains(".") || !email.contains("@")) {
                    edtEmail.setError("Please Enter your email");
                    check = false;
                }
                if (password.equals("")) {
                    edtPassword.setError("Please enter your Password");
                    check = false;

                }
                if (password.length() < 7) {
                    edtPassword.setError("Password must be atleast 7 character long");
                    check = false;
                }
                if (check) {
//                    JsonObjectRequest
//                            jsonObjectRequest
//                            = new JsonObjectRequest(
//                            Request.Method.GET,
//                            url,
//                            null,
//                            new Response.Listener<JSONObject>() {
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    System.out.println(response);
////                            System.out.println(response.body());
////                            System.err.println(call.request().url());
////                            System.err.println(call.request().toString());
////                            if(response.body() != null)
////                            Toast.makeText(PostReqDemoActivity.this , response.body().getCompany().toString(),Toast.LENGTH_LONG );
//                                }
//                            },
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    System.out.println(error);
//                                }
//                            });
//
//                    queue.add(jsonObjectRequest);

                    FlaskAPIPost.getClient().login(name,email,password).enqueue(new Callback<PostResponse>() {
                        @Override
                        public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                            System.out.println("Header " + response.headers());
                            System.out.println("Body " +response.body());
                            System.err.println("URL " +call.request().url());
                            System.err.println(call.request().toString());
                            System.err.println(response.body().getCompany());
                            System.err.println(response.body().getEmail());
//                            if(response.body() != null)
//                            Toast.makeText(PostReqDemoActivity.this , response.body().getCompany().toString(),Toast.LENGTH_LONG );
                        }

                        @Override
                        public void onFailure(Call<PostResponse> call, Throwable t) {
                            t.printStackTrace();
                            System.err.println(call.request().url());
                            System.err.println(call.request().toString());
                        }
                    });
                }
            }
        });

    }


}