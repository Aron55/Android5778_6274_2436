package com.arons.android5778_6274_2436.Model.Datasource;

import android.support.annotation.NonNull;

import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;
import com.arons.android5778_6274_2436.Model.Backend.DBManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase_DBManager implements DBManager {

    private static Firebase_DBManager firebase_dbManager=null;

    /**
     * Private Constructor, to ensure only one instance of the database
     */
    public Firebase_DBManager() {
    }

    /**
     * Getter of FireBase_DbManager, singleton implemented
     * @return Firebase_DBManager
     */
    public static Firebase_DBManager getFirebase_dbManager() {
        if (firebase_dbManager == null)
            firebase_dbManager = new Firebase_DBManager();

        return firebase_dbManager;
    }

    private static DatabaseReference OrdersTaxiRef;
    static {
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        OrdersTaxiRef=database.getReference("Rides");
    }


    /*--------------------OPERATIONS--------------------*/

    @Override
    public void addNewClientRequestToDataBase(final Ride ride, final Action action) {
        String key=OrdersTaxiRef.push().getKey();
        ride.setKey(key);
        OrdersTaxiRef.child(key).setValue(ride).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure();
            }
        });
    }

}
