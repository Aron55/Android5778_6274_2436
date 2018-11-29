package com.arons.android5778_6274_2436.Model.Entities.Classes;

import com.arons.android5778_6274_2436.Model.Entities.Enum.RideType;

import java.sql.Time;

public class Ride {

    private RideType RideType;
    private Location StartLocation;
    private Location EndLocation;
    private Time BeginningTime;
    private Time EndTime;
    private String NameOfCustomer;
    private String PhoneNumberOfCustomer;
    private String MailOfCustomer;

    public Ride(RideType rideType, Location startLocation, Location endLocation, Time beginingTime, Time endTime, String nameOfCustomer, String phoneNumberOfCustomer, String mailOfCustomer) {
        RideType = rideType;
        StartLocation = startLocation;
        EndLocation = endLocation;
        BeginningTime = beginingTime;
        EndTime = endTime;
        NameOfCustomer = nameOfCustomer;
        PhoneNumberOfCustomer = phoneNumberOfCustomer;
        MailOfCustomer = mailOfCustomer;
    }

    public Ride(RideType rideType) {

        RideType = rideType;
    }

    public RideType getRideType() {
        return RideType;
    }

    public void setRideType(RideType rideType) {
        RideType = rideType;
    }

    public Location getStartLocation() {
        return StartLocation;
    }

    public void setStartLocation(Location startLocation) {
        StartLocation = startLocation;
    }

    public Location getEndLocation() {
        return EndLocation;
    }

    public void setEndLocation(Location endLocation) {
        EndLocation = endLocation;
    }

    public Time getBeginningTime() {
        return BeginningTime;
    }

    public void setBeginningTime(Time beginningTime) {
        BeginningTime = beginningTime;
    }

    public Time getEndTime() {
        return EndTime;
    }

    public void setEndTime(Time endTime) {
        EndTime = endTime;
    }

    public String getNameOfCustomer() {
        return NameOfCustomer;
    }

    public void setNameOfCustomer(String nameOfCustomer) {
        NameOfCustomer = nameOfCustomer;
    }

    public String getPhoneNumberOfCustomer() {
        return PhoneNumberOfCustomer;
    }

    public void setPhoneNumberOfCustomer(String phoneNumberOfCustomer) {
        PhoneNumberOfCustomer = phoneNumberOfCustomer;
    }

    public String getMailOfCustomer() {
        return MailOfCustomer;
    }

    public void setMailOfCustomer(String mailOfCustomer) {
        MailOfCustomer = mailOfCustomer;
    }
}
