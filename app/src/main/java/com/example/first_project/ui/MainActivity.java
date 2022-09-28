package com.example.first_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.first_project.R;

public class MainActivity extends AppCompatActivity {
    Button timeOutButton, interceptButton,uiButton, postReqButton,getDemoButton, localDbDemoInsert,localDbDemoFetch, notesButton,
    formDemoActivity, imageDemoActivity,fragmentActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeOutButton = findViewById(R.id.toBtn);
        interceptButton = findViewById(R.id.interceptBtn);
        uiButton = findViewById(R.id.UIBtn);
        postReqButton = findViewById(R.id.postReqDemo);
        getDemoButton = findViewById(R.id.getDemoFlask);
        localDbDemoInsert = findViewById(R.id.localDbDemoInsert);
        localDbDemoFetch = findViewById(R.id.localDbDemoFetch);
        notesButton = findViewById(R.id.notesApp);
        formDemoActivity = findViewById(R.id.formDemo);
        imageDemoActivity = findViewById(R.id.imageDemo);
        fragmentActivity = findViewById(R.id.fragmentDemo);


        fragmentActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentDemo.class);
                startActivity(intent);

            }
        });


        imageDemoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ImageDemo.class);
                startActivity(intent);

            }
        });


        formDemoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });






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
    localDbDemoInsert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    });

    localDbDemoFetch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, LocalDBFetchActivity.class);
            startActivity(intent);
        }
    });


    notesButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(intent);
        }
    });

    }
}