package com.arons.android5778_6274_2436;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arons.android5778_6274_2436.Model.Datasource.Tools;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Location;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;
import com.arons.android5778_6274_2436.Model.Entities.Enum.RideType;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

    EditText name;
    EditText phone;
    EditText mail;
    EditText location;
    EditText arrival;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editName);
        phone = (EditText) findViewById(R.id.editPhone);
        mail = (EditText) findViewById(R.id.editMail);
        location = (EditText) findViewById(R.id.editLoc);
        arrival = (EditText) findViewById(R.id.editArr);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.addNewClient(name, phone, mail, location, arrival);
                if (v.getId()==R.id.button){
                    Toast.makeText(MainActivity.this,"You have ordered a Taxi", Toast.LENGTH_SHORT).show();
                }

        }

    });

    }
}
