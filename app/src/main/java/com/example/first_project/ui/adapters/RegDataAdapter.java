package com.example.first_project.ui.adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_project.R;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.model.RegData;
import com.example.first_project.ui.DetailActivity;
import com.example.first_project.ui.FormActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.Executors;

public class RegDataAdapter extends RecyclerView.Adapter<RegDataAdapter.ViewHolder> {

    List<RegData> regDataArrayList;

    public RegDataAdapter(List<RegData> students) {
        this.regDataArrayList = students;
    }

    @NonNull
    @Override
    public RegDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.student_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;


    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RegData regData = regDataArrayList.get(position);
        holder.name.setText(regData.getName());
        String dateString;
        if(regData.getDob()!=null){
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(regData.getDob());
            System.out.println(date);
            dateString = date.toString();
        }else {
            dateString = "N/A";
        }

        holder.year.setText(dateString);
        holder.father.setText(regData.getEmail());
        holder.gr.setText(String.valueOf(regData.getDesignation()));
        holder.grade.setText(String.valueOf(regData.getAge()));
        holder.section.setText(regData.getCnic());

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "click on item: " + regData.getName(), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(view.getContext(), FormActivity.class);
//                intent.putExtra("id", regData.getId());
//                System.err.println(regData.getId());
//                view.getContext().startActivity(intent);
//            }
//        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), holder.detail);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.option_view:
                                Toast.makeText(view.getContext(), "click on item: " + regData.getName(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                                intent.putExtra("id", regData.getId());
                                System.err.println(regData.getId());
                                view.getContext().startActivity(intent);
                                return true;
                            case R.id.option_edit:
                                Toast.makeText(view.getContext(), "click on item: " + regData.getName(), Toast.LENGTH_LONG).show();
                                Intent intent2 = new Intent(view.getContext(), FormActivity.class);
                                intent2.putExtra("id", regData.getId());
                                System.err.println(regData.getId());
                                view.getContext().startActivity(intent2);
                                return true;
                            case R.id.option_delete:
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatabaseClient.getInstance(view.getContext()).getAppDatabase().regUserDao().deleteById(regData.getId());
                                    }
                                });



                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return regDataArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, year, father, grade, section, gr;
        public CardView cardView;
        public ImageView detail;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.cardView = itemView.findViewById(R.id.cv);
            this.year = itemView.findViewById(R.id.year);
            this.gr = itemView.findViewById(R.id.gr);
            this.section = itemView.findViewById(R.id.section);
            this.father = itemView.findViewById(R.id.father_name);
            this.grade = itemView.findViewById(R.id.grade);
            detail = itemView.findViewById(R.id.detail_iv);
        }
    }


}
