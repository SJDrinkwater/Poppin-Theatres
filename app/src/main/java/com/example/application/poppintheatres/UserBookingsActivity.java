package com.example.application.poppintheatres;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class UserBookingsActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private int userId;
    private BookingsAdapter bookingAdapter;
    private List<BookingRecords> bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bookings);

        // Get the user ID from the intent
        userId = getIntent().getIntExtra("user_id", -1);

        dbHelper = new DatabaseHelper(this);
        displayUserBookings();
        Button viewBookingsButton = findViewById(R.id.back_button);
        viewBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserBookingsActivity.this, PerformanceListActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });
    }

    //grab all the booking from the bookings table and display them
    private void displayUserBookings() {
        bookings = dbHelper.getUserBookings(userId);

        bookingAdapter = new BookingsAdapter(this, R.layout.booking_list_item, bookings, dbHelper);

        ListView listView = findViewById(R.id.booking_list_view);
        listView.setAdapter(bookingAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookingRecords booking = bookings.get(position);
                showConfirmationDialog(booking);
            }
        });

        TextView noBookingsTextView = findViewById(R.id.no_bookings_text_view);

        if (bookings.isEmpty()) {
            noBookingsTextView.setVisibility(View.VISIBLE);
        } else {
            noBookingsTextView.setVisibility(View.GONE);
        }
    }

    //Pop dialog to confirm if a usr wants to actually cancel the booking or not
    private void showConfirmationDialog(final BookingRecords booking) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel Booking");
        builder.setMessage("Are you sure you want to cancel this booking?");

        // Set the positive (yes) button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelBooking(booking);
            }
        });

        // Set the negative (no) button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //cancel the booking and update the performances with adding back seat amount etc
    private void cancelBooking(BookingRecords booking) {
        int bookingId = booking.getId();
        int performanceId = booking.getPerformanceId();
        int seatTypeId = booking.getSeatTypeId();
        int quantity = booking.getQuantity();

        dbHelper.updateSeatsAfterCancellation(performanceId, seatTypeId, quantity);
        dbHelper.deleteBooking(bookingId);
        Toast.makeText(this, "Booking has been canceled", Toast.LENGTH_SHORT).show();

        // Update the bookings list and notify the adapter
        bookings.remove(booking);
        bookingAdapter.notifyDataSetChanged();
    }

}
