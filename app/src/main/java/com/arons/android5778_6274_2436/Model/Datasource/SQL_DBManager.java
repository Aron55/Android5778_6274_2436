package com.arons.android5778_6274_2436.Model.Datasource;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.arons.android5778_6274_2436.Model.Backend.DBManager;
import com.arons.android5778_6274_2436.Model.Backend.MapsFunction;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SQL_DBManager implements DBManager {

    private FirebaseDatabase InitializeDB(Context context) {
        try {
            FirebaseApp.initializeApp(context);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            return database;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNewRide(Ride Ride, Context context) {

        FirebaseDatabase db = InitializeDB(context);
        if (db == null) return false;
        else {
            DatabaseReference myRef = db.getReference("Ride");
            //myRef.setValue(Ride);
            //myRef.child("Ride").setValue(Ride);

            myRef.push().setValue(Ride);







            return true;
        }
    }

    @Override
    public boolean updateRide(Ride Ride, Context context) {
        return false;
    }
}

