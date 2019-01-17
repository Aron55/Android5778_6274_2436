package com.arons.android5778_6274_2436.Model.Entities.Classes;

import android.location.Location;

public class AddressAndLocation {
    public LatitudeAndLongitudeLocation getmLatitudeAndLongitudeLocation() {
        return mLatitudeAndLongitudeLocation;
    }

    public void setmLatitudeAndLongitudeLocation(LatitudeAndLongitudeLocation mLatitudeAndLongitudeLocation) {
        this.mLatitudeAndLongitudeLocation = mLatitudeAndLongitudeLocation;
    }

    private LatitudeAndLongitudeLocation mLatitudeAndLongitudeLocation;
    private String mAddress;


    public AddressAndLocation(AddressAndLocation addressAndLocation) {
        mLatitudeAndLongitudeLocation=addressAndLocation.mLatitudeAndLongitudeLocation;
        mAddress=addressAndLocation.mAddress;
    }

    public AddressAndLocation() {
    }

    public AddressAndLocation(Location location, String address) {
        mLatitudeAndLongitudeLocation =new LatitudeAndLongitudeLocation( location);
        mAddress = address;
    }


    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
