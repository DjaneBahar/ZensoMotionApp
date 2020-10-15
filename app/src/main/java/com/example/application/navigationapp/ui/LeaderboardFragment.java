package com.example.application.navigationapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.application.navigationapp.R;


public class LeaderboardFragment extends Fragment implements View.OnClickListener {

    Button suneKnap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rodView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        suneKnap = rodView.findViewById(R.id.demoknap);
        suneKnap.setOnClickListener(this);
        return rodView;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "Demoknap trykket", Toast.LENGTH_LONG).show();
    }
}