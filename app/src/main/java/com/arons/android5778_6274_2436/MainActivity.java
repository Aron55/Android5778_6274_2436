package com.arons.android5778_6274_2436;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arons.android5778_6274_2436.Model.Backend.Const;
import com.arons.android5778_6274_2436.Model.Backend.DBManager;
import com.arons.android5778_6274_2436.Model.Backend.DBManager_Factory;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Location;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;

public class MainActivity extends Activity implements View.OnClickListener {


    private EditText nameCus;
    private EditText phoneCus;
    private EditText mailCus;
    private EditText locationCus;
    private EditText destinationCus;
    private Button buttonGet;
    DBManager mydb ;



    private void findViews(){
        nameCus = (EditText)findViewById(R.id.editName);
        phoneCus = (EditText)findViewById(R.id.editPhone);
        mailCus = (EditText)findViewById(R.id.editMail);
        locationCus = (EditText)findViewById(R.id.editDep);
        destinationCus = (EditText)findViewById(R.id.editDes);
        buttonGet = (Button) findViewById(R.id.button);
        buttonGet.setOnClickListener(this);
    }

        private void addRide(){
        Ride newRide = new Ride();
        Location endLocation = new Location(this.destinationCus.getText().toString());
        Location startLocation = new Location(this.locationCus.getText().toString());

        newRide.setEndLocation(endLocation);
        newRide.setStartLocation(startLocation);
        newRide.setMailOfCustomer(this.mailCus.getText().toString());
        newRide.setNameOfCustomer(this.nameCus.getText().toString());
        newRide.setPhoneNumberOfCustomer(this.phoneCus.getText().toString());

        mydb.addNewRide(newRide,getApplicationContext());

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

        if (v.getId()==R.id.button){
            addRide();
            Toast.makeText(MainActivity.this,"You have ordered a Taxi", Toast.LENGTH_SHORT).show();
        }
    }
}
