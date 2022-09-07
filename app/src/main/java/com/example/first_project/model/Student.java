package com.example.first_project.model;

public class Student {
String name;
String father;
String grade;
String section;
int gr;
String year;

    public Student(String name, String father, String grade, String section, int gr, String year) {
        this.name = name;
        this.father = father;
        this.grade = grade;
        this.section = section;
        this.gr = gr;
        this.year = year;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getGr() {
        return gr;
    }

    public void setGr(int gr) {
        this.gr = gr;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
