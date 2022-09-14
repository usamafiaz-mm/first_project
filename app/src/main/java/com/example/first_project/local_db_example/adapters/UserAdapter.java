package com.example.first_project.local_db_example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_project.R;
import com.example.first_project.local_db_example.model.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mCtx;
    private List<UserModel> userModelList;

    public UserAdapter(Context mCtx, List<UserModel> userModelList) {
        this.mCtx = mCtx;
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(userModelList.get(position).getName());
        holder.email.setText(userModelList.get(position).getEmail());
        holder.id.setText(userModelList.get(position).getId());
        holder.response.setText(userModelList.get(position).getData());
        holder.created.setText(String.valueOf(userModelList.get(position).getCreatedOn()));


    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, response, created, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.rt_name);
            this.email = itemView.findViewById(R.id.rt_email);
            this.response = itemView.findViewById(R.id.rt_email);
            this.created = itemView.findViewById(R.id.rt_email);
            this.id = itemView.findViewById(R.id.rt_email);

        }
    }
}
