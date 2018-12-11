package com.arons.android5778_6274_2436.Model.Entities.Classes;

public class Location {
    public String location;

    @Override
    public String toString() {
        return "Location{" +
                "location='" + location + '\'' +
                '}';
    }

    public Location(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }
}
