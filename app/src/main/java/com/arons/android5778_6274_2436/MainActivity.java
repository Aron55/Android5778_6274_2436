package com.arons.android5778_6274_2436;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arons.android5778_6274_2436.Model.Backend.DBManager;
import com.arons.android5778_6274_2436.Model.Backend.DBManager_Factory;
import com.arons.android5778_6274_2436.Model.Backend.MapsFunction;
import com.arons.android5778_6274_2436.Model.Entities.Classes.MyLocation;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;

import java.util.Map;

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
        try {
            Ride newRide = new Ride();
            MyLocation endLocation = MapsFunction.StringToLocation(this.destinationCus.getText().toString(), getApplicationContext());
            MyLocation startLocation = MapsFunction.StringToLocation(this.locationCus.getText().toString(), getApplicationContext());

            newRide.setEndLocation(endLocation);
            newRide.setStartLocation(startLocation);
            newRide.setMailOfCustomer(this.mailCus.getText().toString());
            newRide.setNameOfCustomer(this.nameCus.getText().toString());
            newRide.setPhoneNumberOfCustomer(this.phoneCus.getText().toString());

            mydb.addNewRide(newRide, getApplicationContext());
        }
        catch (Exception e)
        {

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
    public void onClick(View v){

        if (v.getId()==R.id.button){
            addRide();
            Toast.makeText(MainActivity.this,"You have ordered a Taxi", Toast.LENGTH_SHORT).show();
        }
    }
}
