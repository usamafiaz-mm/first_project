package com.example.first_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.first_project.R;

public class MainActivity extends AppCompatActivity {
    Button timeOutButton, interceptButton,uiButton, postReqButton,getDemoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeOutButton = findViewById(R.id.toBtn);
        interceptButton = findViewById(R.id.interceptBtn);
        uiButton = findViewById(R.id.UIBtn);
        postReqButton = findViewById(R.id.postReqDemo);
        getDemoButton = findViewById(R.id.getDemoFlask);


        timeOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimeOutActivity.class);
                startActivity(intent);

            }
        });
        interceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InterceptorActivity.class);
                startActivity(intent);
            }
        });

        uiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListingActivity.class);
                startActivity(intent);
            }
        });

        postReqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostReqDemoActivity.class);
                startActivity(intent);
            }
        });

        getDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GetDemoScreen1.class);
                startActivity(intent);
            }
        });
    }
}