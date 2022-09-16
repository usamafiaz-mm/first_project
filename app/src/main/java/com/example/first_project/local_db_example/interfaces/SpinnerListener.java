package com.example.first_project.local_db_example.interfaces;

import android.view.View;
import android.widget.AdapterView;

public class SpinnerListener implements AdapterView.OnItemSelectedListener {


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        switch (pos){
            case 0:
                System.out.println("Show All");
                break;
            case 1:
                System.out.println("Show only mine");
                break;

        }

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
