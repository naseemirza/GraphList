package com.example.naseem.graphlist;

/**
 * Created by Naseem on 06-11-2016.
 */

public class MarksData {
    String date;
    int total;
    int obtained;
    float percentage;

    public MarksData(String date, int total, int obtained, float percentage) {
        this.date = date;
        this.total = total;
        this.obtained = obtained;
        this.percentage = percentage;
    }

    public String getDate() {
        return date;
    }

    public int getTotal() {
        return total;
    }

    public int getObtained() {
        return obtained;
    }

    public float getPercentage() {
        return percentage;
    }
}
