package com.example.first_project.ui.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_project.R;
import com.example.first_project.model.Student;
import com.example.first_project.model.TableItem;

import java.util.ArrayList;

public class TableItemAdapter extends RecyclerView.Adapter<TableItemAdapter.ViewHolder> {

    private ArrayList<TableItem> tableItemArrayList;

    public TableItemAdapter(ArrayList<TableItem> tableItemArrayList) {
        this.tableItemArrayList = tableItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.table_item, parent, false);
        TableItemAdapter.ViewHolder viewHolder = new TableItemAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TableItem tableItem = tableItemArrayList.get(position);
        holder.attendance.setText(String.valueOf(tableItem.getAttendance())); ;
        holder.month.setText(tableItem.getMonth()); ;


    }

    @Override
    public int getItemCount() {
        return tableItemArrayList.size();
    }


    public  static  class  ViewHolder extends RecyclerView.ViewHolder{
public TextView month, attendance;
        public ViewHolder( View itemView) {
            super(itemView);
            this.month = itemView.findViewById(R.id.month);
            this.attendance = itemView.findViewById(R.id.attendance);
        }
    }
}
