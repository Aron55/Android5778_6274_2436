package com.arons.android5778_6274_2436.Model.Datasource;

import android.content.ContentValues;

import com.arons.android5778_6274_2436.Model.Backend.Const;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;

public class Tools {

    public static ContentValues rideToContentValues(Ride myRide) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(Const.RideConst.ARRIVATION_TIME,myRide.getEndTime().toString());
        contentValues.put(Const.RideConst.DEPART_ADDRESS,myRide.getStartLocation().toString());
        contentValues.put(Const.RideConst.DEPART_TIME,myRide.getBeginningTime().toString());
        contentValues.put(Const.RideConst.DESTINATION_ADDRESS,myRide.getEndLocation().toString());
        contentValues.put(Const.RideConst.MAIL_OF_CUSTOMER,myRide.getMailOfCustomer());
        contentValues.put(Const.RideConst.NAME_OF_CUSTOMER,myRide.getNameOfCustomer());
        contentValues.put(Const.RideConst.PHONE_OF_CUSTOMER,myRide.getPhoneNumberOfCustomer());

        return contentValues;
    }
}
