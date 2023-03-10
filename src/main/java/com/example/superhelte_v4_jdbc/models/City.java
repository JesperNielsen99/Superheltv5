package com.example.superhelte_v4_jdbc.models;

public class City {
    private int cityID;
    private String cityName;

    public City(int cityID, String cityName) {
        this.cityID = cityID;
        this.cityName = cityName;
    }

    public int getCityID() { return cityID; }
    public String getCityName() { return cityName; }
}
