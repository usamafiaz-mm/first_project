package com.example.first_project.local_db_example.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user_data")

public class RegData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private  String name;


    @ColumnInfo(name = "email")
    private String email;

    @Override
    public String toString() {
        return "RegData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", cnic='" + cnic + '\'' +
                ", designation='" + designation + '\'' +
                ", university='" + university + '\'' +
                ", company='" + company + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @ColumnInfo(name = "age")
    private  int age;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @ColumnInfo(name = "cnic")
    private  String cnic;

    @ColumnInfo(name = "designation")
    private  String designation;

    @ColumnInfo(name = "university")
    private  String university;

    @ColumnInfo(name = "company")
    private  String company;


    @ColumnInfo(name = "profile_image")
    private  String profileImage;

    @ColumnInfo(name = "cover_image")
    private  String coverImage;

    @ColumnInfo(name = "dob")
    private Date dob;

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "address")
    private  String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
