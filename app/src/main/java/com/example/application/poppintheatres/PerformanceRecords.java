package com.example.application.poppintheatres;

public class PerformanceRecords {
    private int id;
    private String title;
    private String date;
    private String location;

    public PerformanceRecords(int id, String title, String date, String location) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }


}

