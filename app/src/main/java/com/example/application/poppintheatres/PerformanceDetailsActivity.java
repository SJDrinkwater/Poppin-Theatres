package com.example.application.poppintheatres;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PerformanceDetailsActivity extends AppCompatActivity {

    private PerformanceData performanceData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_details);

        // Get the performance record ID from the intent
        int performanceId = getIntent().getIntExtra("performance_id", -1);
        int userId = getIntent().getIntExtra("user_id", -1);


        // Get the performance record from the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<PerformanceData> performanceList = dbHelper.getAllPerformances();

        for (PerformanceData data : performanceList) {
            if (data.getPerformance().getId() == performanceId) {
                performanceData = data;
                break;
            }
        }

        // Set the performance details in the UI
        TextView titleTextView = findViewById(R.id.title_text_view);
        titleTextView.setText(performanceData.getPerformance().getTitle());

        TextView dateTextView = findViewById(R.id.date_text_view);
        dateTextView.setText(performanceData.getPerformance().getDate());

        TextView locationTextView = findViewById(R.id.location_text_view);
        locationTextView.setText(performanceData.getPerformance().getLocation());

        TextView seatsAvailableTextView = findViewById(R.id.seats_available_text_view);
        seatsAvailableTextView.setText(String.format("Seats available: %d", performanceData.getSeat().getSeatsAvailable()));

        TextView seatPriceTextView = findViewById(R.id.seat_price_text_view);
        seatPriceTextView.setText(String.format("Starting from £%s", performanceData.getSeat().getSeatPrice()));

        TextView accessibilityTextView = findViewById(R.id.accessibility_text_view);
        StringBuilder accessibilityText = new StringBuilder();
        if (performanceData.getAccessibility().getWheelchair()) {
            accessibilityText.append("This Performance is Wheelchair Accessible\n");
        }
        if (performanceData.getAccessibility().getFlashingLights()) {
            accessibilityText.append("This Performance Contains Flashing Lights\n");
        }
        if (performanceData.getAccessibility().getStairs()) {
            accessibilityText.append("You Must Be Able to Climb 20 Steps\n");
        }
        accessibilityTextView.setText(accessibilityText.toString());



        Button getTicketsButton = findViewById(R.id.get_tickets_button);
        getTicketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerformanceDetailsActivity.this, BookTicketsActivity.class);
                intent.putExtra("performance_id", performanceId);
                intent.putExtra("user_id", userId);
                startActivity(intent);

            }
        });
        Button goBack = findViewById(R.id.go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerformanceDetailsActivity.this, PerformanceListActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);

            }
        });
    }
}


