package com.example.application.poppintheatres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PerformanceListActivity extends AppCompatActivity {

    private ListView listView;
    private PerformanceListAdapter adapter;
    private List<PerformanceData> performanceList = new ArrayList<>();
    private Spinner performanceFilterSpinner;
    //performance id for performances that apply to the specific field
    private final List<Integer> shakespearePerformanceIds = Arrays.asList(1, 2, 3, 5);
    private final List<Integer> sophoclesPerformanceIds = Arrays.asList(4, 6);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_list);

        listView = findViewById(R.id.list_view);

        int userId = getIntent().getIntExtra("user_id", -1);

        adapter = new PerformanceListAdapter(this, performanceList, userId);
        listView.setAdapter(adapter);

        // Retrieve all performance records from the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        performanceList = dbHelper.getAllPerformances();

        // Update the adapter with the new data
        adapter.setPerformanceList(performanceList);

        // Find the spinner
        performanceFilterSpinner = findViewById(R.id.performance_filter_spinner);

        // Set up the spinner adapter
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.performance_filter_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        performanceFilterSpinner.setAdapter(spinnerAdapter);

        // Set the spinner OnItemSelectedListener
        performanceFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        Button viewBookingsButton = findViewById(R.id.view_bookings_button);
        viewBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerformanceListActivity.this, UserBookingsActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });
        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the login activity or any desired activity
                Intent intent = new Intent(PerformanceListActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
                startActivity(intent);
                finish();
            }
        });

    }
    private boolean isSophoclesPerformance(int performanceId) {
        return sophoclesPerformanceIds.contains(performanceId);
    }
    private boolean isShakespearePerformance(int performanceId) {
        return shakespearePerformanceIds.contains(performanceId);
    }

    private void applyFilters() {
        List<PerformanceData> filteredList = new ArrayList<>();
        String selectedFilter = performanceFilterSpinner.getSelectedItem().toString();

        for (PerformanceData data : performanceList) {
            if (selectedFilter.equals("All")) {
                filteredList.add(data);
            }
            else if (selectedFilter.equals("Plays By Shakespeare") && isShakespearePerformance(data.getPerformance().getId())) {
                filteredList.add(data);
            }
            else if (selectedFilter.equals("Plays By Sophocles") && isSophoclesPerformance(data.getPerformance().getId())) {
                filteredList.add(data);
            }
        }

        adapter.setPerformanceList(filteredList);
    }


}

