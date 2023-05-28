package com.example.application.poppintheatres;

public class PerformanceData {
    private PerformanceRecords performance;
    private SeatRecords seat;
    private AccessibilityRecords accessibility;
    private BookingRecords booking;

    public PerformanceData(PerformanceRecords performance, SeatRecords seat, AccessibilityRecords accessibility) {
        this.performance = performance;
        this.seat = seat;
        this.accessibility = accessibility;
        this.booking = booking;
    }

    public PerformanceRecords getPerformance() {
        return performance;
    }
    public SeatRecords getSeat() {
        return seat;
    }
    public AccessibilityRecords getAccessibility() {
        return accessibility;
    }
    public BookingRecords getBookings() {
        return booking;
    }

}