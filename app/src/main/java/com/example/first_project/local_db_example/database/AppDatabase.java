package com.example.first_project.local_db_example.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.first_project.local_db_example.dao.NotesDao;
import com.example.first_project.local_db_example.dao.RegUserDao;
import com.example.first_project.local_db_example.dao.UserDao;
import com.example.first_project.local_db_example.model.NotesModel;
import com.example.first_project.local_db_example.model.RegData;
import com.example.first_project.local_db_example.model.UserModel;
import com.example.first_project.local_db_example.util_classes.Converters;


@Database(entities = {UserModel.class, NotesModel.class, RegData.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract NotesDao notesDao();
    public abstract RegUserDao regUserDao();



    static final Migration MIGRATION_1_2 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'user_data' ADD COLUMN 'profile_image' TEXT ");
            database.execSQL("ALTER TABLE 'user_data' ADD COLUMN 'cover_image' TEXT ");

            Log.d("VROM","Migration");
        }


    };

}
