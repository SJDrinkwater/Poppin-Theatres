package com.example.application.poppintheatres;

public class BookingRecords {
    private int id;
    private int userId;
    private int performanceId;
    private int seatTypeId;
    private int quantity;
    private String confirmationCode;
    private String notes;


    public BookingRecords() {
        this.id = id;
        this.userId = userId;
        this.performanceId = performanceId;
        this.seatTypeId = seatTypeId;
        this.quantity = quantity;
        this.confirmationCode = confirmationCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(int performanceId) {
        this.performanceId = performanceId;
    }

    public int getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(int seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

}

