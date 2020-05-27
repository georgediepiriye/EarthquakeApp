package com.example.earthquakeapp;

import androidx.appcompat.app.AppCompatActivity;

public class Earthquake {

    private double mMagnitude;
    private String mPlace;
    private long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake(double magnitude, String place, long timeInMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mPlace = place;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }


}
