package com.example.first_project.local_db_example.dao;


import androidx.room.Dao;
import androidx.room.Insert;

import com.example.first_project.local_db_example.model.NotesModel;

@Dao
public interface NotesDao {

    @Insert
    void insert(NotesModel notesModel);

}
