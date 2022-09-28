package com.example.first_project.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.first_project.R;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.model.RegData;
import com.example.first_project.ui.FragmentDemo;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;


public class DetailFragment extends Fragment {

    TextView name, father, year, section, grade, grv;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.EditMenuBtn:
                FragmentManager fragmentManager2 = getParentFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                FormFragment formFragment = new FormFragment();
                fragmentTransaction2.addToBackStack("xycz");
                fragmentTransaction2.hide(DetailFragment.this);
                Bundle args = new Bundle();
                Bundle bundle = this.getArguments();

                int id = bundle.getInt("id", 1);

                Log.d("TAG", "onOptionsItemSelected: " + id);
                args.putInt("id", id);
                formFragment.setArguments(args);
                fragmentTransaction2.add(R.id.frameLayout, formFragment);
                fragmentTransaction2.commit();
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FragmentDemo) getActivity())
                .setActionBarTitle("Detail Fragment");
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int id = bundle.getInt("id", 1);
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {


                    RegData data = DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase().regUserDao().SearchById(id);

                    name = view.findViewById(R.id.dname);
                    father = view.findViewById(R.id.dfather);
                    year = view.findViewById(R.id.dyear);
                    section = view.findViewById(R.id.dsection);
                    grade = view.findViewById(R.id.dgrade);
                    grv = view.findViewById(R.id.dgr);
                    String pattern = "dd-MM-yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    if (data.getDob() != null) {
                        String date = simpleDateFormat.format(data.getDob());
                        name.setText(date);
                    } else {
                        name.setText("N/A");
                    }

                    father.setText(data.getName());
                    year.setText(String.valueOf(data.getAge()));
                    section.setText(data.getEmail());
                    grade.setText(data.getCnic());
                    grv.setText(data.getDesignation());

                }
            });

        }
        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager2 = getParentFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                ListingFragment listingFragment = new ListingFragment();
                fragmentTransaction2.hide(DetailFragment.this);
                fragmentTransaction2.replace(R.id.frameLayout, listingFragment);
                fragmentTransaction2.commit();
            }
        });
    }
}