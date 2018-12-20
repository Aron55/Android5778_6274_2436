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

    private FirebaseDatabase InitializeDB(Context context) throws Exception {
        try {
            FirebaseApp.initializeApp(context);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            return database;

        } catch (Exception e) {
            throw new Exception("Error connecting to the DataBase",e);
        }

    }

    @Override
    public boolean addNewRide(Ride Ride, Context context) throws Exception {

        try {
            FirebaseDatabase db = InitializeDB(context);
            if (db == null) return false;
            else {
                DatabaseReference myRef = db.getReference("Ride");
                //myRef.setValue(Ride);
                //myRef.child("Ride").setValue(Ride);

                myRef.push().setValue(Ride);
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Server error - Error when trying to add the ride to the database",e);
        }
    }

    }

