package com.arons.android5778_6274_2436.Controller;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arons.android5778_6274_2436.Model.Backend.DBManager;
import com.arons.android5778_6274_2436.Model.Backend.DBManager_Factory;
import com.arons.android5778_6274_2436.Model.Entities.Classes.AddressAndLocation;
import com.arons.android5778_6274_2436.Model.Entities.Classes.ClientRequestStatus;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Exceptions;
import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;
import com.arons.android5778_6274_2436.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

public class OrderRideActivity extends AppCompatActivity {

    final int PLACE_PICKER_REQUEST_DESTINATION = 1;
    final int PLACE_PICKER_REQUEST_PICKUP = 2;
    final int REQUEST_CHECK_SETTINGS = 3;
    private Button newRideButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private EditText destinationAddressEditText;
    private EditText pickUpAddressEditText;
    private Ride ride;

    private static DBManager db;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private Geocoder mGeocoder;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ride);

        ride = new Ride();
        db = DBManager_Factory.getInstance();
        findViews();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Finding your location...");
        progressDialog.show();
        getLocation();


    }

    private void getLocation() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CHECK_SETTINGS);
        } else {
            //if permission is granted
            enableGps();
            buildLocationRequest();
            buildLocationCallBack();

            //create FusedProviderClient
            mFusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CHECK_SETTINGS);
                return;
            }
            mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }
    }

    private void enableGps() {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick( final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        Toast.makeText(OrderRideActivity.this,"Sorry, you must allow gps location to use this app",LENGTH_LONG).show();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void buildLocationCallBack() {
        mLocationCallback=new LocationCallback()
        {
            Location fLocation;
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for(Location location: locationResult.getLocations())
                    fLocation=location;
                String address=locationToAddress(fLocation);
                ride.setPickupAddress(new AddressAndLocation(fLocation,address));
                progressDialog.dismiss();
                pickUpAddressEditText.setText(address);
            }
        };
    }

    private String locationToAddress(Location location) {
        mGeocoder = new Geocoder(OrderRideActivity.this, Locale.getDefault());
        Address address;
        List<Address> addresses;
        try {
            addresses = mGeocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
            if(addresses!=null && addresses.size()>0) {
                address = addresses.get(0);
                return address.getAddressLine(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void buildLocationRequest() {
        mLocationRequest=LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setSmallestDisplacement(10);
    }

    private void findViews() {
        newRideButton = (Button) findViewById(R.id.orderTaxiButton);
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkOnlyLetters(s.toString()))
                {
                    firstNameEditText.setError("Only letters");
                }
                else
                {
                    firstNameEditText.setError(null);

                }
            }
        });

        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkOnlyLetters(s.toString())){
                    lastNameEditText.setError("Only letters");
                }
                else{
                    lastNameEditText.setError(null);
                }
            }
        });

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkEmail(s.toString())){
                    emailEditText.setError("Email not valid");
                }
                else{
                    emailEditText.setError(null);
                }

            }
        });

        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Exceptions.checkOnlyNumbers(s.toString())){
                    phoneNumberEditText.setError("Only numbers");
                }
                else{
                    phoneNumberEditText.setError(null);
                }
            }
        });

        destinationAddressEditText = (EditText) findViewById(R.id.destinationEditText);
        destinationAddressEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(OrderRideActivity.this), PLACE_PICKER_REQUEST_DESTINATION);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        pickUpAddressEditText = (EditText) findViewById(R.id.pickupAddressEditText);
        pickUpAddressEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(OrderRideActivity.this), PLACE_PICKER_REQUEST_PICKUP);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        newRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isErrorInput()){
                    return;
                }
                if(isEmptyInput()){
                    Toast.makeText(getBaseContext(), R.string.all_fields_required,LENGTH_LONG).show();
                    return;
                }
                setRide();
                addClientRequestToDataBase(ride);
            }
        });
    }

    private boolean isEmptyInput() {
        return TextUtils.isEmpty(firstNameEditText.getText())||
                TextUtils.isEmpty(lastNameEditText.getText())||
                TextUtils.isEmpty(emailEditText.getText())||
                TextUtils.isEmpty(phoneNumberEditText.getText())||
                TextUtils.isEmpty(pickUpAddressEditText.getText())||
                TextUtils.isEmpty(destinationAddressEditText.getText());
    }

    private boolean isErrorInput() {
        return firstNameEditText.getError()!=null||lastNameEditText.getError()!=null
                ||emailEditText.getError()!=null||phoneNumberEditText.getError()!=null;
    }

    private void setRide() {
        ride.setClientFirstName(firstNameEditText.getText().toString());
        ride.setClientLastName(lastNameEditText.getText().toString());
        ride.setClientEmail(emailEditText.getText().toString());
        ride.setClientTelephone(phoneNumberEditText.getText().toString());
        ride.setRideState(ClientRequestStatus.WAITING);
    }

    private void addClientRequestToDataBase(Ride ride) {
        newRideButton.setEnabled(false);
        db.addNewClientRequestToDataBase(ride, new DBManager.Action() {
            @Override
            public void onSuccess() {
                newRideButton.setEnabled(true);
                Toast.makeText(getBaseContext(),"We got your Order!",LENGTH_LONG).show();
            }
            @Override
            public void onFailure() {
                newRideButton.setEnabled(true);
                Toast.makeText(getBaseContext(),"We are sorry! The order didn't success\n Try Again!",LENGTH_LONG).show();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST_DESTINATION) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                destinationAddressEditText.setText(place.getAddress());
                Location location=new Location("destination");
                location.setLatitude(place.getLatLng().latitude);
                location.setLongitude(place.getLatLng().longitude);
                ride.setDestinationAddress(new AddressAndLocation(location,place.getAddress().toString()));
            }
        }
        if (requestCode == PLACE_PICKER_REQUEST_PICKUP) {
            if (resultCode == RESULT_OK) {
                mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
                Place place = PlacePicker.getPlace(data, this);
                pickUpAddressEditText.setText(place.getAddress());
                Location location=new Location("pickup");
                location.setLatitude(place.getLatLng().latitude);
                location.setLongitude(place.getLatLng().longitude);
                ride.setPickupAddress(new AddressAndLocation(location,place.getAddress().toString()));
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case REQUEST_CHECK_SETTINGS:
            {
                if(grantResults.length>0)
                {
                    if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    {

                    }
                    else if(grantResults[0]==PackageManager.PERMISSION_DENIED)
                    {

                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }
}

