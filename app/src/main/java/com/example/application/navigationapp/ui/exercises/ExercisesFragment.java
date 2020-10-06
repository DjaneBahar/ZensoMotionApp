package com.example.application.navigationapp.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import android.widget.Button;
import android.widget.Toast;

import com.example.application.navigationapp.R;
import com.example.application.navigationapp.ui.Pushups;
import com.example.application.navigationapp.ui.Cardio;
import com.example.application.navigationapp.ui.Sensor;

public class ExercisesFragment extends Fragment {

    private ExercisesViewModel exercisesViewModel;
    private Button goCardio;
    private Button pushupsbtn;
    private Button sensorbtn;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "Hello from Fragment", Toast.LENGTH_SHORT).show();

        View view = inflater.inflate(R.layout.fragment_exercises,container,false);

        Button goCardio = (Button) view.findViewById(R.id.goCardio_button);
        goCardio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Cardio.class);
                intent.putExtra("some", "some data");
                startActivity(intent);
            }
        });



        Button pushupsbtn = (Button) view.findViewById(R.id.pushups_button);
        pushupsbtn.setOnClickListener(new View.OnClickListener() {


               @Override
                public void onClick(View v) {
                  Intent intent1 = new Intent(getActivity(), Pushups.class);
                  intent1.putExtra("some","some data");
                  startActivity(intent1);
                                         }
           });


        Button sensorbtn = (Button) view.findViewById(R.id.sensor_button);
        sensorbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), Sensor.class);
                intent2.putExtra("some", "some data");
                startActivity(intent2);
            }
        });



        return view;


}
}

