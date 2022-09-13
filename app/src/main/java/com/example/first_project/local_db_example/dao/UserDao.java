package com.example.first_project.local_db_example.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.first_project.local_db_example.model.UserModel;

@Dao
public interface UserDao {
    @Insert
    void insert (UserModel userModel);
}
