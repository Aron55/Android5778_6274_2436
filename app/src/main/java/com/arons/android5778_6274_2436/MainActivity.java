package com.arons.android5778_6274_2436;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;

import com.arons.android5778_6274_2436.Model.Datasource.Tools;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Location;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;
import com.arons.android5778_6274_2436.Model.Entities.Enum.RideType;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
