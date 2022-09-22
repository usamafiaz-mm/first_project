package com.example.first_project.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.first_project.R;
import com.example.first_project.local_db_example.adapters.EmptyAdapter;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.model.RegData;
import com.example.first_project.ui.adapters.RegDataAdapter;

import java.util.List;
import java.util.concurrent.Executors;

public class ListingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listing);


//        ArrayList studentList = new Constant().STUDENTS;


        EmptyAdapter emptyAdapter = new EmptyAdapter();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(emptyAdapter);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<RegData> temp =       DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().regUserDao().fetchALl();
             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     RegDataAdapter adapter = new RegDataAdapter(temp);


                     recyclerView.setAdapter(adapter);
                 }
             });
            }
        });


    }
}