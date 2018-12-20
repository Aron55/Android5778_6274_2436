package com.arons.android5778_6274_2436;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arons.android5778_6274_2436.Model.Backend.DBManager;
import com.arons.android5778_6274_2436.Model.Backend.DBManager_Factory;
import com.arons.android5778_6274_2436.Model.Backend.MapsFunction;
import com.arons.android5778_6274_2436.Model.Entities.Classes.MyLocation;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;
import com.google.android.gms.tasks.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {


    private EditText nameCus;
    private EditText phoneCus;
    private EditText mailCus;
    private EditText locationCus;
    private EditText destinationCus;
    private Button buttonGet;
    DBManager mydb;


    private void findViews() {
        nameCus = (EditText) findViewById(R.id.editName);
        phoneCus = (EditText) findViewById(R.id.editPhone);
        mailCus = (EditText) findViewById(R.id.editMail);
        locationCus = (EditText) findViewById(R.id.editDep);
        destinationCus = (EditText) findViewById(R.id.editDes);
        buttonGet = (Button) findViewById(R.id.button);
        buttonGet.setOnClickListener(this);
    }

    @SuppressLint("StaticFieldLeak")
    private void addRide() {
        try {
            final Ride newRide = new Ride();

            // Google Maps Task + Firebase task
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        MyLocation endLocation = MapsFunction.StringToLocation(destinationCus.getText().toString(), getApplicationContext());
                        MyLocation startLocation = MapsFunction.StringToLocation(locationCus.getText().toString(), getApplicationContext());
                        newRide.setEndLocation(endLocation);
                        newRide.setStartLocation(startLocation);
                    } catch (Exception e) {
                        messageBox("Error", e.getMessage() + "\n\n" + e.getCause().getMessage());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);

                    // Firebase task
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                mydb.addNewRide(newRide, getApplicationContext());

                            } catch (Exception e) {
                                messageBox("Error", e.getMessage() + "\n\n" + e.getCause().getMessage());
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Toast.makeText(MainActivity.this, "You have ordered a Taxi", Toast.LENGTH_SHORT).show();
                        }
                    }.execute();

                }
            }.execute();


            newRide.setMailOfCustomer(this.mailCus.getText().toString());
            newRide.setNameOfCustomer(this.nameCus.getText().toString());
            newRide.setPhoneNumberOfCustomer(this.phoneCus.getText().toString());
            newRide.setBeginningTime(new Date());

        } catch (Exception e) {
            messageBox("Error", e.getMessage() + "\n\n" + e.getCause().getMessage());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = DBManager_Factory.getInstance();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViews();
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button) {
            addRide();
        }
    }


    private void messageBox(String method, String message) {
        Log.d("EXCEPTION: " + method, message);

        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }
}


