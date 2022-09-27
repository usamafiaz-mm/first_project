package com.example.first_project.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_project.data.Constant;
import com.example.first_project.R;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.model.RegData;
import com.example.first_project.model.Student;
import com.example.first_project.ui.adapters.TableItemAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {

    TextView name, father, year, section, grade, grv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);


//        int id = getIntent().getExtras().getInt("id");
//        View parentLayout = findViewById(android.R.id.content);

//        Snackbar.make(parentLayout, String.valueOf(gr), Snackbar.LENGTH_LONG).show();

//        Student student = new Student();
//        ArrayList students = new Constant().STUDENTS;


//        Iterator iterator = students.iterator();
//        while (iterator.hasNext()) {
//            Student i = (Student) iterator.next();
//            if (i.getGr() == gr) {
//                student = i;
//            }
//
//        }

        if ( getIntent().getExtras()!=null) {

            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                  int  id = getIntent().getExtras().getInt("id");

                    RegData data = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().regUserDao().SearchById(id);

                    name = findViewById(R.id.dname);
                    father = findViewById(R.id.dfather);
                    year = findViewById(R.id.dyear);
                    section = findViewById(R.id.dsection);
                    grade = findViewById(R.id.dgrade);
                    grv = findViewById(R.id.dgr);
                    String pattern = "dd-MM-yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String date = simpleDateFormat.format(data.getDob());
                    name.setText(date);
                    father.setText(data.getName());
                    year.setText(String.valueOf(data.getAge()));
                    section.setText(data.getEmail());
                    grade.setText(data.getCnic());
                    grv.setText(data.getDesignation());



                }
            });



//            name = findViewById(R.id.dname);
//            father = findViewById(R.id.dfather);
//            year = findViewById(R.id.dyear);
//            section = findViewById(R.id.dsection);
//            grade = findViewById(R.id.dgrade);
//            grv = findViewById(R.id.dgr);
//
//            name.setText(student.getName());
//            father.setText(student.getFather());
//            year.setText(student.getYear());
//            section.setText(student.getSection());
//            grade.setText(student.getGrade());
//            grv.setText(String.valueOf(student.getGr()));
//            this.getSupportActionBar().setTitle(student.getName());
//


//            ArrayList tableItems = new Constant().TABLE_ITEMS;
//            TableItemAdapter adapter = new TableItemAdapter(tableItems);
//            RecyclerView recyclerView = findViewById(R.id.table_rc);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}