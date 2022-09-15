package com.example.first_project.local_db_example.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.first_project.local_db_example.dao.NotesDao;
import com.example.first_project.local_db_example.dao.UserDao;
import com.example.first_project.local_db_example.model.NotesModel;
import com.example.first_project.local_db_example.model.UserModel;


@Database(entities = {UserModel.class, NotesModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract NotesDao notesDao();



    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'notes' ADD COLUMN 'created' INTEGER NOT NULL DEFAULT 0");
            Log.d("VROM","Migration");
        }


    };

}
