package com.example.application.navigationapp.ui.exercises;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.widget.Button;

public class ExercisesViewModel extends ViewModel {

    private AppBarConfiguration mAppBarConfiguration;


    private MutableLiveData<String> mText;

    public ExercisesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Exercises fragment");
        mText.setValue("");
    }
    public LiveData<String> getText() {
        return mText;
    }


}