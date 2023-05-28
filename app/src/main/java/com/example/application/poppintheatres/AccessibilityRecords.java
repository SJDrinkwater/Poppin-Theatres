package com.example.application.poppintheatres;

public class AccessibilityRecords {
    private int id;
    private int performanceId;
    private boolean wheelchair;
    private boolean stairs;
    private boolean flashingLights;

    public AccessibilityRecords(int id, int performanceId, boolean wheelchair, boolean stairs, boolean flashingLights) {
        this.id = id;
        this.performanceId = performanceId;
        this.wheelchair = wheelchair;
        this.stairs = stairs;
        this.flashingLights = flashingLights;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public boolean getWheelchair() {

        return wheelchair;
    }

    public boolean getStairs() {

        return stairs;
    }

    public boolean getFlashingLights() {

        return flashingLights;
    }

}

