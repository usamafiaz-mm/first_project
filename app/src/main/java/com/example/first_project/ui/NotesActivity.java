package com.example.first_project.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.first_project.R;
import com.example.first_project.local_db_example.adapters.EmptyAdapter;
import com.example.first_project.local_db_example.adapters.NotesAdapter;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.interfaces.OnItemClickListener;
import com.example.first_project.local_db_example.model.NotesModel;

import java.util.List;
import java.util.concurrent.Executors;

public class NotesActivity extends AppCompatActivity {
    Button saveBtn, clearBtn;
    EditText contentEdt;
    boolean updateSwitch = false;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        saveBtn = findViewById(R.id.saveNote);
        clearBtn = findViewById(R.id.clearNote);
        contentEdt = findViewById(R.id.contentEt);


        RecyclerView recyclerView = findViewById(R.id.notes_rc);
        EmptyAdapter emptyAdapter = new EmptyAdapter();
        recyclerView.setAdapter(emptyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NotesActivity.this));
        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().fetchAll().observe(NotesActivity.this, new Observer<List<NotesModel>>() {
            @Override
            public void onChanged(List<NotesModel> notesModels) {
//


                NotesAdapter notesAdapter = new NotesAdapter(notesModels, new OnItemClickListener() {
                    @Override
                    public void onItemClick(NotesModel item) {
                        String content = item.getContent();
                        contentEdt.setText("");
                        contentEdt.setText(content);
                        updateSwitch = true;
                        id = item.getId();


                    }
                });
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(notesAdapter);


            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = contentEdt.getText().toString().trim();

                if (updateSwitch) {

                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {

                            NotesModel notesModel = new  NotesModel( content, System.currentTimeMillis());
                            notesModel.setId(id);

                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().update(notesModel);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    contentEdt.setText("");
                                    Toast.makeText(NotesActivity.this, "Updated", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        }
                    });

                }

                else {


                    if (content.isEmpty()) {
                        contentEdt.setError("Content Field cant be empty!!!");
                        return;
                    }

                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().insert(new NotesModel(content, System.currentTimeMillis()));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    contentEdt.setText("");
                                    Toast.makeText(NotesActivity.this, "Added to Database", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    });
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure you want to clear database")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().clearTable();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        //Set your icon here
                        .setTitle("Alert!")
                        .setIcon(R.drawable.ic_baseline_warning_24);
                AlertDialog alert = builder.create();
                alert.show();


            }
        });
    }
}