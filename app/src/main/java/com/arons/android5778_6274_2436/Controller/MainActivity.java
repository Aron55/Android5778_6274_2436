package com.arons.android5778_6274_2436.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.arons.android5778_6274_2436.R;


public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderRideActivity();
            }
        });
    }

    public void openOrderRideActivity(){
        Intent intent=new Intent(this,OrderRideActivity.class);
        startActivity(intent);
    }
}