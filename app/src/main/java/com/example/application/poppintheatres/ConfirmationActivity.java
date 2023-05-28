package com.example.application.poppintheatres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {


    //display all relevant information about purchased information
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        int userId = getIntent().getIntExtra("user_id", -1);

        String performanceName = getIntent().getStringExtra("performance_name");
        String performanceDate = getIntent().getStringExtra("performance_date");
        String ticketSummary = getIntent().getStringExtra("ticket_summary");
        String totalCost = getIntent().getStringExtra("total_cost");
        String notes = getIntent().getStringExtra("notes");
        String confirmationCode = getIntent().getStringExtra("confirmation_code");

        TextView performanceNameTextView = findViewById(R.id.confirmation_performance_name_text_view);
        performanceNameTextView.setText(performanceName);

        TextView performanceDateTextView = findViewById(R.id.confirmation_performance_date_text_view);
        performanceDateTextView.setText(performanceDate);

        TextView ticketSummaryTextView = findViewById(R.id.confirmation_ticket_summary_text_view);
        ticketSummaryTextView.setText(ticketSummary);

        TextView totalCostTextView = findViewById(R.id.confirmation_total_cost_text_view);
        totalCostTextView.setText(totalCost);

        TextView notesTextView = findViewById(R.id.confirmation_notes_text_view);
        notesTextView.setText(notes);

        TextView confirmationCodeTextView = findViewById(R.id.confirmation_code_text_view);
        confirmationCodeTextView.setText(confirmationCode);

        Button returnToPerformanceListButton = findViewById(R.id.return_to_performance_list_button);
        returnToPerformanceListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmationActivity.this, PerformanceListActivity.class);
                intent.putExtra("user_id", userId);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}

