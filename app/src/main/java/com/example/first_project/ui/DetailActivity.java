package com.example.first_project.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.first_project.data.Constant;
import com.example.first_project.R;
import com.example.first_project.model.Student;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    TextView name, father, year, section, grade, grv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);


        int gr = getIntent().getExtras().getInt("gr");
        View parentLayout = findViewById(android.R.id.content);

        Snackbar.make(parentLayout, String.valueOf(gr), Snackbar.LENGTH_LONG).show();

        ArrayList students = new Constant().STUDENTS;
        Student student = new Student();


        Iterator iterator = students.iterator();
        while (iterator.hasNext()) {
            Student i = (Student) iterator.next();
            if (i.getGr() == gr) {
                student = i;
            }

        }

        if (!Objects.equals(student.getName(), "")) {
            name = findViewById(R.id.dname);
            father = findViewById(R.id.dfather);
            year = findViewById(R.id.dyear);
            section = findViewById(R.id.dsection);
            grade = findViewById(R.id.dgrade);
            grv = findViewById(R.id.dgr);

            name.setText(student.getName());
            father.setText(student.getFather());
            year.setText(student.getYear());
            section.setText(student.getSection());
            grade.setText(student.getYear());
            grv.setText(String.valueOf(student.getGr()));
            this.getSupportActionBar().setTitle(student.getName());
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}