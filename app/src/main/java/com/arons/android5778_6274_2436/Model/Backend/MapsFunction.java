package com.arons.android5778_6274_2436.Model.Backend;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.arons.android5778_6274_2436.Model.Entities.Classes.MyLocation;

import java.util.List;

public class MapsFunction {

    public MapsFunction(Context context) {


    }

    public static MyLocation StringToLocation(String address, Context context) throws Exception {

        try {
            Geocoder geocoder = new Geocoder(context);

            List<Address> location = geocoder.getFromLocationName(address, 10);
            MyLocation newLocation;

            if (location.size() == 0) // If no result found
            {
                // Add Israel to the end of the address for more precision
                address = address + " Israel";
                // Search again
                location = geocoder.getFromLocationName(address, 10);
            }
            if (location.get(0).getLocality()==null) // If still no result
            {
                // Save the string received in parameter into the newLocation object
                newLocation = new MyLocation(address);
            } else {
                // Take the first result
                Address formatted = location.get(0);
                // Create a new MyLocation
                newLocation = new MyLocation(formatted.getCountryName(), formatted.getLocality(), formatted.getThoroughfare(), formatted.getSubThoroughfare());
            }

            return newLocation;
        } catch (Exception e) {
            return null;
        }
    }
}
