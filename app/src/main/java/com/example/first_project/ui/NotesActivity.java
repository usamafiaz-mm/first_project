package com.example.first_project.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.first_project.R;
import com.example.first_project.local_db_example.adapters.EmptyAdapter;
import com.example.first_project.local_db_example.adapters.NotesAdapter;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.interfaces.OnItemClickListener;
import com.example.first_project.local_db_example.model.NoteTuple;
import com.example.first_project.local_db_example.model.NotesModel;

import java.util.List;
import java.util.concurrent.Executors;

public class NotesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button saveBtn, clearBtn;
    EditText contentEdt;
    boolean updateSwitch = false;
    int userId;
    Switch aSwitch;
    RecyclerView recyclerView;
    TextView sizeTv;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_out, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {


            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences prefs = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                    prefs.edit().clear().commit();
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().userDao().clearTable();
                    finish();
                }
            });


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        saveBtn = findViewById(R.id.saveNote);
        clearBtn = findViewById(R.id.clearNote);
        contentEdt = findViewById(R.id.contentEt);
        aSwitch = findViewById(R.id.switchForActionBar);
        sizeTv = findViewById(R.id.size);


        recyclerView = findViewById(R.id.notes_rc);
        EmptyAdapter emptyAdapter = new EmptyAdapter();
        recyclerView.setAdapter(emptyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NotesActivity.this));

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.string_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



//        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().fetchAll().observe(NotesActivity.this, new Observer<List<NoteTuple>>() {
//            @Override
//            public void onChanged(List<NoteTuple> noteTuples) {
////
//
//
//                NotesAdapter notesAdapter = new NotesAdapter(noteTuples, new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(NoteTuple item) {
//                        String content = item.getContent();
//                        contentEdt.setText("");
//                        contentEdt.setText(content);
//                        updateSwitch = true;
//                        userId = item.getId();
//                    }
//
//
//                });
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setAdapter(notesAdapter);
//
//
//            }
//        });


                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().getSize().observe(NotesActivity.this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        System.out.println(integer);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sizeTv.setText(String.valueOf(integer));
                            }
                        });

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

                            NotesModel notesModel = new NotesModel(content, System.currentTimeMillis());
                            notesModel.setId(userId);
                            SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                            String email = preferences.getString("email", "");
                            notesModel.setUser(email);
                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().update(notesModel);

                            updateSwitch = false;
                            userId =-1;
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

                } else {


                    if (content.isEmpty()) {
                        contentEdt.setError("Content Field cant be empty!!!");
                        return;
                    }

                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {

                            SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                            String email = preferences.getString("email", "");
                            NotesModel note = new NotesModel(content, System.currentTimeMillis());
                            note.setUser(email);

                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().insert(note);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        switch (pos){
            case 0:
                System.out.println("Show All");
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().fetchAll().observe(NotesActivity.this, new Observer<List<NoteTuple>>() {
                    @Override
                    public void onChanged(List<NoteTuple> noteTuples) {
                        System.out.println(noteTuples);
//


                        NotesAdapter notesAdapter = new NotesAdapter(noteTuples, new OnItemClickListener() {
                            @Override
                            public void onItemClick(NoteTuple item) {
                                String content = item.getContent();
                                contentEdt.setText("");
                                contentEdt.setText(content);
                                updateSwitch = true;
                                userId = item.getId();
                            }


                        });
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(notesAdapter);


                    }
                });
                break;
            case 1:
                System.out.println("Show only mine");


                SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                String email = preferences.getString("email", "");
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().notesDao().fetchOnlyMine(email).observe(NotesActivity.this, new Observer<List<NoteTuple>>() {
                    @Override
                    public void onChanged(List<NoteTuple> noteTuples) {
//


                        NotesAdapter notesAdapter = new NotesAdapter(noteTuples, new OnItemClickListener() {
                            @Override
                            public void onItemClick(NoteTuple item) {
                                String content = item.getContent();
                                contentEdt.setText("");
                                contentEdt.setText(content);
                                updateSwitch = true;
                                userId = item.getId();
                            }


                        });
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(notesAdapter);


                    }
                });


                break;

        }

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}