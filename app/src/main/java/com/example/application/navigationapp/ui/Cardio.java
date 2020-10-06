package com.example.application.navigationapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.widget.Toast;
import com.example.application.navigationapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;


public class Cardio extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;


    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button mButtonSet;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.cardio_layout);


            mEditTextInput = findViewById(R.id.edit_text_input);
            mTextViewCountDown = findViewById(R.id.text_view_countdown);
            mButtonSet = findViewById(R.id.button_set);
            mButtonStartPause = findViewById(R.id.button_start_pause);
            mButtonReset = findViewById(R.id.button_reset);

            mButtonSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String input = mEditTextInput.getText().toString();
                    if (input.length() == 0) {
                        Toast.makeText(Cardio.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    long millisInput = Long.parseLong(input) * 60000;
                    if (millisInput == 0) {
                        Toast.makeText(Cardio.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    setTime(millisInput);
                    mEditTextInput.setText("");
                }
            });
            mButtonStartPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTimerRunning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            });
            mButtonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTimer();
                }
            });

            Bundle bundle = getIntent().getExtras();
            if(bundle !=null){
                if(bundle.getString("some") != null){
                    Toast.makeText(getApplicationContext(),"data: " +
                            bundle.getString("some"),Toast.LENGTH_SHORT).show();}

            }
        }



        private void setTime(long milliseconds) {
            mStartTimeInMillis = milliseconds;
            resetTimer();
            closeKeyboard();
        }


        private void startTimer() {
            mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }
                @Override
                public void onFinish() {
                    mTimerRunning = false;
                    updateWatchInterface();
                }
            }.start();
            mTimerRunning = true;
            updateWatchInterface();
        }


        private void pauseTimer() {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            updateWatchInterface();
        }


        private void resetTimer() {
            mTimeLeftInMillis = mStartTimeInMillis;
            updateCountDownText();
            updateWatchInterface();
        }


        private void updateCountDownText() {
            int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
            int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
            String timeLeftFormatted;
            if (hours > 0) {
                timeLeftFormatted = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, seconds);
            } else {
                timeLeftFormatted = String.format(Locale.getDefault(),
                        "%02d:%02d", minutes, seconds);
            }
            mTextViewCountDown.setText(timeLeftFormatted);
        }


        private void updateWatchInterface() {
            if (mTimerRunning) {
                mEditTextInput.setVisibility(View.INVISIBLE);
                mButtonSet.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.INVISIBLE);
                mButtonStartPause.setText("Pause");
            } else {
                mEditTextInput.setVisibility(View.VISIBLE);
                mButtonSet.setVisibility(View.VISIBLE);
                mButtonStartPause.setText("Start");
                if (mTimeLeftInMillis < 1000) {
                    mButtonStartPause.setVisibility(View.INVISIBLE);
                } else {
                    mButtonStartPause.setVisibility(View.VISIBLE);
                }
                if (mTimeLeftInMillis < mStartTimeInMillis) {
                    mButtonReset.setVisibility(View.VISIBLE);
                } else {
                    mButtonReset.setVisibility(View.INVISIBLE);
                }
            }
        }


        private void closeKeyboard() {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
        @Override
        protected void onStop() {
            super.onStop();
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("startTimeInMillis", mStartTimeInMillis);
            editor.putLong("millisLeft", mTimeLeftInMillis);
            editor.putBoolean("timerRunning", mTimerRunning);
            editor.putLong("endTime", mEndTime);
            editor.apply();
            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
            }
        }
        @Override
        protected void onStart() {
            super.onStart();
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
            mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
            mTimerRunning = prefs.getBoolean("timerRunning", false);
            updateCountDownText();
            updateWatchInterface();
            if (mTimerRunning) {
                mEndTime = prefs.getLong("endTime", 0);
                mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
                if (mTimeLeftInMillis < 0) {
                    mTimeLeftInMillis = 0;
                    mTimerRunning = false;
                    updateCountDownText();
                    updateWatchInterface();
                } else {
                    startTimer();
                }
            }
        }
    }