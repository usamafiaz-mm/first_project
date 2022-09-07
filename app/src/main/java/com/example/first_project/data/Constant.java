package com.example.first_project.data;


import com.example.first_project.model.Student;
import com.example.first_project.model.TableItem;

import java.util.ArrayList;

public class Constant {
  public   ArrayList<Student> STUDENTS = new ArrayList<>();
    public   ArrayList<TableItem> TABLE_ITEMS = new ArrayList<>();


    public Constant() {
        STUDENTS.add(new Student("Ali", "Ahmed", "X", "B", 123, "2022-23"));
        STUDENTS.add(new Student("Tayyib", "Ahmed", "X", "B", 124, "2022-23"));

        STUDENTS.add(new Student("Usman", "Ahmed", "X", "B", 125, "2022-23"));

        STUDENTS.add(new Student("Ahmad", "Ahmed", "X", "B", 126, "2022-23"));

        STUDENTS.add(new Student("Mohsin", "Ahmed", "X", "B", 127, "2022-23"));

        STUDENTS.add(new Student("Nabeel", "Ahmed", "X", "B", 128, "2022-23"));

        TABLE_ITEMS.add(new TableItem("August", 19));
        TABLE_ITEMS.add(new TableItem("September", 20));
        TABLE_ITEMS.add(new TableItem("October", 23));
        TABLE_ITEMS.add(new TableItem("November", 22));
        TABLE_ITEMS.add(new TableItem("December", 25));

        TABLE_ITEMS.add(new TableItem("August", 19));
        TABLE_ITEMS.add(new TableItem("September", 20));
        TABLE_ITEMS.add(new TableItem("October", 23));
        TABLE_ITEMS.add(new TableItem("November", 22));
        TABLE_ITEMS.add(new TableItem("December", 25));

        TABLE_ITEMS.add(new TableItem("August", 19));
        TABLE_ITEMS.add(new TableItem("September", 20));
        TABLE_ITEMS.add(new TableItem("October", 23));
        TABLE_ITEMS.add(new TableItem("November", 22));
        TABLE_ITEMS.add(new TableItem("December", 25));

        TABLE_ITEMS.add(new TableItem("August", 19));
        TABLE_ITEMS.add(new TableItem("September", 20));
        TABLE_ITEMS.add(new TableItem("October", 23));
        TABLE_ITEMS.add(new TableItem("November", 22));
        TABLE_ITEMS.add(new TableItem("December", 25));


    }

}