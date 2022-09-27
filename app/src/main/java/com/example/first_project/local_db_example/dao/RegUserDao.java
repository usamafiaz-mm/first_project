package com.example.first_project.local_db_example.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.first_project.local_db_example.model.RegData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Dao
public interface RegUserDao {

    @Insert
    void insert(RegData regData);

    @Query("SELECT * FROM user_data order by age")
    LiveData<  List<RegData> > fetchALl();

    @Query("SELECT * FROM user_data where id = :id")
    RegData SearchById(int id);

    @Update
    void updateRegData(RegData data);
    @Query("DELETE  FROM user_data where id = :id")
    void deleteById(int id);
}
