package com.arons.android5778_6274_2436.Model.Backend;

import android.content.ContentValues;
import android.content.Context;

public interface DBManager {

    boolean addNewRide(ContentValues Ride, Context context);

    boolean updateRide(ContentValues Ride, Context context);
}
