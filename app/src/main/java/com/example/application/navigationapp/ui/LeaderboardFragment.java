package com.example.application.navigationapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.application.navigationapp.R;


public class LeaderboardFragment extends Fragment {

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
        return rodView;
    }
}