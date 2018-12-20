package com.arons.android5778_6274_2436.Model.Backend;

import android.content.ContentValues;
import android.content.Context;

import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;

public interface DBManager {

    boolean addNewRide(Ride Ride, Context context) throws Exception;
}
