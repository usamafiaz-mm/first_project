package com.example.first_project.local_db_example.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.first_project.local_db_example.model.NotesModel;
import com.example.first_project.local_db_example.model.UserModel;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void insert(NotesModel notesModel);


    @Delete
    void delete(NotesModel notes);


    @Query("SELECT * FROM notes order by created")
    LiveData<List<NotesModel>> fetchAll();


    @Query("DELETE FROM notes")
    void clearTable();




    @Update
    void update(NotesModel notesModel);
}
