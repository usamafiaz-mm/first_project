package com.example.first_project.local_db_example.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Entity(tableName = "user_table")
public class UserModel {

    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @PrimaryKey()
    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "createdOn")
    private long   createdOn;


    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public UserModel(){

        createdOn = System.currentTimeMillis();


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
