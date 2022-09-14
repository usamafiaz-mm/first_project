package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.first_project.data.Constant;
import com.example.first_project.local_db_example.adapters.EmptyAdapter;
import com.example.first_project.local_db_example.adapters.UserAdapter;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.ui.adapters.StudentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class LocalDBFetchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_dbfetch);
        RecyclerView recyclerView = findViewById(R.id.rc_fetch);
        EmptyAdapter emptyAdapter = new EmptyAdapter();
        recyclerView.setAdapter(emptyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LocalDBFetchActivity.this));


        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List userModelList =  DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().userDao().fetchAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UserAdapter userAdapter = new UserAdapter(getApplicationContext(), userModelList);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(userAdapter);
                    }
                });
            }
        });
    }
}