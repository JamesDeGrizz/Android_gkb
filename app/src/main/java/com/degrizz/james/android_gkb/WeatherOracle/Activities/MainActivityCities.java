package com.degrizz.james.android_gkb.WeatherOracle.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.R;

public class MainActivityCities extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cities);
    }
}