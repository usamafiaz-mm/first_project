package com.example.first_project.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.first_project.data.Constant;
import com.example.first_project.R;
import com.example.first_project.ui.adapters.StudentAdapter;

import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listing);
        ArrayList studentList = new Constant().STUDENTS;
        StudentAdapter adapter = new StudentAdapter(studentList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
}