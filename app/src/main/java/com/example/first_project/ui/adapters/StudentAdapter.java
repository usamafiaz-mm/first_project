package com.example.first_project.ui.adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_project.R;
import com.example.first_project.model.Student;
import com.example.first_project.ui.DetailActivity;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    ArrayList<Student> students;

    public StudentAdapter(ArrayList<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.student_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;


    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Student student = students.get(position);
        holder.name.setText(student.getName());
        holder.year.setText(student.getYear());
        holder.father.setText(student.getFather());
        holder.gr.setText(String.valueOf(student.getGr()));
        holder.grade.setText(student.getGrade());
        holder.section.setText(student.getSection());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + student.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("gr", student.getGr());
                System.err.println(student.getGr());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, year, father, grade, section, gr;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.cardView = itemView.findViewById(R.id.cv);
            this.year = itemView.findViewById(R.id.year);
            this.gr = itemView.findViewById(R.id.gr);
            this.section = itemView.findViewById(R.id.section);
            this.father = itemView.findViewById(R.id.father_name);
            this.grade = itemView.findViewById(R.id.grade);
        }
    }


}
