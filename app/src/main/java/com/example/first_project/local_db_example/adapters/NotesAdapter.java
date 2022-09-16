package com.example.first_project.local_db_example.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_project.R;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.interfaces.OnItemClickListener;
import com.example.first_project.local_db_example.model.NoteTuple;
import com.example.first_project.local_db_example.model.NotesModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    List<NoteTuple> notesModelList;
    private final OnItemClickListener listener;


    public NotesAdapter(List notesModelList, OnItemClickListener listener) {
        this.notesModelList = notesModelList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.note_item, parent, false);
        NotesAdapter.ViewHolder viewHolder = new NotesAdapter.ViewHolder(listItem);


        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.noteContent.setText(notesModelList.get(position).getContent());
        String formattedDate;
        long created = notesModelList.get(position).getCreatedOn();
        if (created == 0) {
            formattedDate = "N/A";

        } else {
            DateFormat obj = new SimpleDateFormat("MMM dd, H:mm");
            Date res = new Date(created);
            formattedDate = obj.format(res).toString();
        }
        holder.dateEdt.setText(formattedDate);

        holder.name.setText(notesModelList.get(position).getName()==null?"N/A": notesModelList.get(position).getName());
        holder.bind(notesModelList.get(position), listener);


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = view.getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
                String email = preferences.getString("email", "");

                System.out.println( preferences.getString("email", ""));
                System.out.println(notesModelList.get(position).getUser());
                if (email.equals(notesModelList.get(position).getUser())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Are you sure you want to delete this note?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {

                                            NotesModel temp = new NotesModel(notesModelList.get(position).getContent(), notesModelList.get(position).getCreatedOn());
                                            temp.setId(notesModelList.get(position).getId());
                                            temp.setUser(notesModelList.get(position).getUser());
                                            DatabaseClient.getInstance(view.getContext()).getAppDatabase().notesDao().delete(temp);

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
                    alert.show();//showing the dialog
                } else {
                    Toast.makeText(view.getContext(),"You do not have enough privileges",Toast.LENGTH_LONG).show();
                }









            }
        });
    }


    @Override
    public int getItemCount() {
        return notesModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView noteContent, dateEdt, name;
        public Button deleteButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteContent = itemView.findViewById(R.id.content_text);
            deleteButton = itemView.findViewById(R.id.delete_content);
            dateEdt = itemView.findViewById(R.id.note_date);
            name = itemView.findViewById(R.id.owner_name);
        }


        public void bind(final NoteTuple item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


    }
}
