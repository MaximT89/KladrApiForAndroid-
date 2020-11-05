package com.example.testkladrapi.kladrAPI.models;

public class StreetModel {
    String streetName;
    String streetId;

    public StreetModel(String streetName, String streetId) {
        this.streetName = streetName;
        this.streetId = streetId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetId() {
        return streetId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    @Override
    public String toString() {
        return streetName;
    }
}
