package com.arons.android5778_6274_2436;

import android.content.Context;

import com.arons.android5778_6274_2436.Model.Entities.Classes.Ride;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void AddNewRideTest(Context c){

       // SQL_DBManager db = new SQL_DBManager();

        Ride myRide = new Ride(RideType.FREE);
        myRide.setBeginningTime(new java.util.Date(2018,12,03,10,0,0));
        myRide.setEndTime(new java.util.Date(2018,12,03,10,30,0));
        myRide.setStartLocation(new MyLocation("Har Homa"));
        myRide.setEndLocation(new MyLocation("Giva't Mordehai"));
        myRide.setMailOfCustomer("aronscemama@gmail.com");
        myRide.setNameOfCustomer("Aron Scemama");
        myRide.setPhoneNumberOfCustomer("0522969696");

        FirebaseApp.initializeApp(c);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue(myRide);
    }

    @Test
    public void DatabaseAcces(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
    }
}