package com.example.application.navigationapp.ui;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.application.navigationapp.R;

public class MapActivity extends AppCompatActivity {

    EditText etSource, etDestination;
    Button btTrack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapactivity_layout);

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            if(bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(),"data: " +
                        bundle.getString("some"),Toast.LENGTH_SHORT).show();}

        }

        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get value from edit text

                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //check condition

                if(sSource.equals("") && sDestination.equals("")){

                    //when both value blank

                    Toast.makeText(getApplicationContext(), "Enter both location",Toast.LENGTH_SHORT).show();


                } else{
                    //when both value fill
                    //display track
                    DisplayTrack(sSource,sDestination);

                }
            }
        });

    }

    private void DisplayTrack(String sSource, String sDestination){

        //If the device does not have a map installed, then redirect it to play store

        try{
        //When google map is installed
            //Initialize uri

            Uri uri = Uri.parse("https://www.google.co./in/,aås/dir/" + sSource + "/" + sDestination);
            //initialize intent
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //set package
            intent.setPackage("com.google.android.apps.maps");
            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity

            startActivity(intent);



        }catch (ActivityNotFoundException e){

            //when google map is not installed
            //initialize uri

            Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //initialize intent with action view

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //start activity
            startActivity(intent);


        }
    }


}
