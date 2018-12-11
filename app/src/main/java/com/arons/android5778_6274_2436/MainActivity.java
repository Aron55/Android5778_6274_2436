package com.arons.android5778_6274_2436;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arons.android5778_6274_2436.Model.Backend.Const;
import com.arons.android5778_6274_2436.Model.Backend.DBManager_Factory;

public class MainActivity extends Activity implements View.OnClickListener {


    private EditText nameCus;
    private EditText phoneCus;
    private EditText mailCus;
    private EditText locationCus;
    private EditText destinationCus;
    private Button buttonGet;
    DBManager_Factory mydb ;



    private void findViews(){
        nameCus = (EditText)findViewById(R.id.editName);
        phoneCus = (EditText)findViewById(R.id.editPhone);
        mailCus = (EditText)findViewById(R.id.editMail);
        locationCus = (EditText)findViewById(R.id.editDep);
        destinationCus = (EditText)findViewById(R.id.editDes);
        buttonGet = (Button) findViewById(R.id.button);
        buttonGet.setOnClickListener(this);
    }

    private void addAOrder(){
        final ContentValues newRide = new ContentValues();
        try {
            newRide.put(Const.RideConst.NAME_OF_CUSTOMER,this.nameCus.getText().toString());
            newRide.put(Const.RideConst.PHONE_OF_CUSTOMER,this.phoneCus.getText().toString());
            newRide.put(Const.RideConst.MAIL_OF_CUSTOMER,this.mailCus.getText().toString());
            newRide.put(Const.RideConst.DEPART_ADDRESS,this.locationCus.getText().toString());
            newRide.put(Const.RideConst.DESTINATION_ADDRESS,this.destinationCus.getText().toString());

            mydb.getInstance().addNewRide(newRide);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }


    @Override
    public void onClick(View v) {
                //db.addNewClient(name, phone, mail, location, arrival); // Un truc du style qui appel DB
        if (v.getId()==R.id.button){
            addAOrder();
            Toast.makeText(MainActivity.this,"You have ordered a Taxi", Toast.LENGTH_SHORT).show();
        }
    }
}
