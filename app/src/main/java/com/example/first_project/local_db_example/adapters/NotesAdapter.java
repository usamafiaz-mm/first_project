package com.example.first_project.local_db_example.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_project.R;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.interfaces.OnItemClickListener;
import com.example.first_project.local_db_example.model.NotesModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    List<NotesModel> notesModelList;
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
        holder.bind(notesModelList.get(position), listener);


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure you want to delete this note?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseClient.getInstance(view.getContext()).getAppDatabase().notesDao().delete(notesModelList.get(position));

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

            }
        });
    }



    @Override
    public int getItemCount() {
        return notesModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView noteContent, dateEdt;
        public Button deleteButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteContent = itemView.findViewById(R.id.content_text);
            deleteButton = itemView.findViewById(R.id.delete_content);
            dateEdt = itemView.findViewById(R.id.note_date);
        }


        public void bind(final NotesModel item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


    }
}
