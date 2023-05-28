package com.example.application.poppintheatres;

public class SeatRecords {
    private int id;
    private int performanceId;
    private String seatName;
    private int seatsAvailable;
    private String seatPrice;

    public SeatRecords(int id, int performanceId, String seatName, int seatsAvailable, String seatPrice) {
        this.id = id;
        this.performanceId = performanceId;
        this.seatName = seatName;
        this.seatsAvailable = seatsAvailable;
        this.seatPrice = seatPrice;
    }

    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id = id;
    }
    public int getSeatsAvailable() {

        return seatsAvailable;
    }
    public String getSeatPrice() {

        return seatPrice;
    }
    @Override
    public String toString() {

        return seatName;
    }

}
