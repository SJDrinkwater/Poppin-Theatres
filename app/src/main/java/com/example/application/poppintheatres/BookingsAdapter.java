package com.example.application.poppintheatres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookingsAdapter extends ArrayAdapter<BookingRecords> {
    private final Context context;
    private List<BookingRecords> bookings;
    private DatabaseHelper dbHelper;

    public BookingsAdapter(Context context, int resource, List<BookingRecords> bookings, DatabaseHelper dbHelper) {
        super(context, resource, bookings);
        this.context = context;
        this.bookings = bookings;
        this.dbHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.booking_list_item, parent, false);

        TextView bookingTitle = rowView.findViewById(R.id.booking_title);
        TextView bookingDate = rowView.findViewById(R.id.booking_date);
        TextView bookingQuantity = rowView.findViewById(R.id.booking_quantity);
        TextView bookingNotes = rowView.findViewById(R.id.booking_notes);
        TextView bookingConfirmationCode = rowView.findViewById(R.id.booking_confirmation_code);

        BookingRecords booking = bookings.get(position);

        // Fetch the performance details using the performanceId from the booking
        PerformanceData performance = dbHelper.getPerformance(booking.getPerformanceId());

        bookingTitle.setText(performance.getPerformance().getTitle());
        bookingDate.setText(performance.getPerformance().getDate());
        bookingQuantity.setText("Quantity: " + booking.getQuantity());
        bookingNotes.setText("Notes: " + booking.getNotes());
        bookingConfirmationCode.setText("Confirmation Code: " + booking.getConfirmationCode());

        return rowView;
    }
}


