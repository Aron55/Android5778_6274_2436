package com.arons.android5778_6274_2436.Model.Backend;

import android.content.ContentValues;
import android.content.Context;

import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;

public interface DBManager {

    boolean addNewRide(Ride Ride, Context context);

    boolean updateRide(Ride Ride, Context context);

    //void addNewRide(ContentValues newRide); // Ajout par Yaacov , J'etais oblige de l'ajouter selon eux , j'espere ne pas avoir tout niquer ...
}
