package com.example.first_project.local_db_example.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.first_project.local_db_example.model.RegData;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RegUserDao {

    @Insert
    void insert(RegData regData);

    @Query("SELECT * FROM user_data order by age")
    List<RegData> fetchALl();

    @Query("SELECT * FROM user_data where id = :id")
    RegData SearchById(int id);

    @Update
    void updateRegData(RegData data);
}
