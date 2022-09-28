package com.example.first_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.first_project.R;
import com.example.first_project.local_db_example.adapters.EmptyAdapter;
import com.example.first_project.local_db_example.database.DatabaseClient;
import com.example.first_project.local_db_example.model.RegData;
import com.example.first_project.ui.FragmentDemo;
import com.example.first_project.ui.ListingActivity;
import com.example.first_project.ui.adapters.RegDataAdapter;

import java.util.List;


public class ListingFragment extends Fragment {


    public ListingFragment() {
    }


    public static ListingFragment newInstance(String param1, String param2) {
        ListingFragment fragment = new ListingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addMenuBtn:
                Log.e("Add", "onOptionsItemSelected: ");
                FragmentManager fragmentManager2 = getParentFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                FormFragment fragment2 = new FormFragment();
                fragmentTransaction2.addToBackStack("xyz");
                fragmentTransaction2.hide(ListingFragment.this);
                fragmentTransaction2.add(R.id.frameLayout, fragment2);
                fragmentTransaction2.commit();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((FragmentDemo) getActivity())
                .setActionBarTitle("Listing Demo");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_listing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmptyAdapter emptyAdapter = new EmptyAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(emptyAdapter);

        DatabaseClient.getInstance(getActivity().getApplicationContext()).getAppDatabase().regUserDao().fetchALl().observe(getActivity(), new Observer<List<RegData>>() {
            @Override
            public void onChanged(List<RegData> regData) {
                if (regData.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    view.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    view.findViewById(R.id.empty_view).setVisibility(View.GONE);
                    RegDataAdapter adapter = new RegDataAdapter(regData);
                    recyclerView.setAdapter(adapter);
                }

            }
        });

    }
}