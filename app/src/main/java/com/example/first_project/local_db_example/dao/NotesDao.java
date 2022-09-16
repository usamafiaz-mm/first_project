package com.example.first_project.local_db_example.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.first_project.local_db_example.model.NoteTuple;
import com.example.first_project.local_db_example.model.NotesModel;
import com.example.first_project.local_db_example.model.UserModel;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void insert(NotesModel notesModel);


    @Delete
    void delete(NotesModel notes);


    @Query("SELECT user_table.name , content, created,notes.id,user  FROM notes LEFT JOIN  user_table ON user_table.id = notes.user order by created;")
    LiveData<List<NoteTuple>> fetchAll();


    @Query("DELETE FROM notes")
    void clearTable();




    @Update
    void update(NotesModel notesModel);

//    @Query("SELECT user_table.name , content, created,notes.id,user  FROM notes,user_table WHERE user_table.id = notes.user AND :id = notes.user  order by created")
//    LiveData<List<NoteTuple>> fetchOnlyMine(String id);


    @Query("SELECT user_table.name , content, created,notes.id,user  FROM notes INNER JOIN user_table ON user_table.id = notes.user WHERE  :id = notes.user  order by created")
    LiveData<List<NoteTuple>> fetchOnlyMine(String id);



    @Query("SELECT COUNT (*) from notes")
    LiveData<Integer> getSize();

}
