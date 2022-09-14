package com.example.first_project.local_db_example.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.first_project.local_db_example.model.UserModel;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert (UserModel userModel);

    @Query("SELECT * FROM user_table")
    List<UserModel> fetchAll();
}
