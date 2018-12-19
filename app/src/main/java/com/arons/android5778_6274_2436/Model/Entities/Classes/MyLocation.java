package com.arons.android5778_6274_2436.Model.Entities.Classes;


import com.arons.android5778_6274_2436.Model.Backend.MapsFunction;

public class MyLocation {
    private String Location;
    private String Country;
    private String City;
    private String Street;
    private String StreetNumber;

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        StreetNumber = streetNumber;
    }

    public MyLocation(String country, String city, String street, String streetNumber) {
        Country = country;
        City = city;
        Street = street;
        StreetNumber = streetNumber;
    }

    public MyLocation(String location) {
        Location = location;
    }


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public void FormatLocation()
    {

    }
}
