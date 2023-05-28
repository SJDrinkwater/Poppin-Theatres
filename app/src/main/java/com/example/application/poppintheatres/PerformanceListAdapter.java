package com.example.application.poppintheatres;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PerformanceListAdapter extends BaseAdapter {
    private Context context;
    private List<PerformanceData> performanceList;
    private int userId;

    public PerformanceListAdapter(Context context, List<PerformanceData> performanceList, int userId) {
        this.context = context;
        this.performanceList = performanceList;
        this.userId = userId;
    }

    public void setPerformanceList(List<PerformanceData> performanceList) {
        this.performanceList = performanceList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return performanceList.size();
    }

    @Override
    public Object getItem(int position) {
        return performanceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_performance, parent, false);
        }

        // Get the current performance data
        PerformanceData performanceData = (PerformanceData) getItem(position);
        PerformanceRecords performance = performanceData.getPerformance();
        SeatRecords seat = performanceData.getSeat();
        AccessibilityRecords accessibility = performanceData.getAccessibility();


        // Set the title, date, location, available seats, and seat price text views
        TextView titleTextView = convertView.findViewById(R.id.title_text_view);
        titleTextView.setText(performance.getTitle());

        TextView dateTextView = convertView.findViewById(R.id.date_text_view);
        dateTextView.setText(performance.getDate());

        TextView locationTextView = convertView.findViewById(R.id.location_text_view);
        locationTextView.setText(performance.getLocation());

        TextView seatsAvailableTextView = convertView.findViewById(R.id.seats_available_text_view);
        seatsAvailableTextView.setText(String.format("Seats available: %d", seat.getSeatsAvailable()));

        TextView seatPriceTextView = convertView.findViewById(R.id.seat_price_text_view);
        seatPriceTextView.setText(String.format("From £%s", seat.getSeatPrice()));

        // Set an OnClickListener to start the PerformanceDetailsActivity
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PerformanceDetailsActivity.class);
                intent.putExtra("performance_id", performance.getId());
                intent.putExtra("user_id", userId);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}



