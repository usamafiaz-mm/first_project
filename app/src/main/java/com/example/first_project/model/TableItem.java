package com.example.first_project.model;

public class TableItem {
    String month;
    int attendance;

    public TableItem(String month, int attendance) {
        this.month = month;
        this.attendance = attendance;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
