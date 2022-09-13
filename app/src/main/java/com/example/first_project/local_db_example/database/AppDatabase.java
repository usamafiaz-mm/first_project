package com.example.first_project.local_db_example.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.first_project.local_db_example.dao.UserDao;
import com.example.first_project.local_db_example.model.UserModel;


@Database(entities = {UserModel.class}, version = 1)
public abstract   class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
