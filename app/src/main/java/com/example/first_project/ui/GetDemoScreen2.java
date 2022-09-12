package com.example.first_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.first_project.R;

public class GetDemoScreen2 extends AppCompatActivity {
Button btnScreen2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_demo_screen2);

        btnScreen2 = findViewById(R.id.getDemoScreen2button);
        btnScreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}