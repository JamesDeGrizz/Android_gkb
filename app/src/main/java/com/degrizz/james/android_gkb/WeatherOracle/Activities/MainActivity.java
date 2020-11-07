package com.degrizz.james.android_gkb.WeatherOracle.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.degrizz.james.android_gkb.WeatherOracle.BuildConfig;
import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.Helpers.WeatherHelper;
import com.degrizz.james.android_gkb.WeatherOracle.Models.City;
import com.degrizz.james.android_gkb.WeatherOracle.Models.WeatherRequest;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == citiesActivityRequest && resultCode == citiesActivityResult) {

            City city = (City) data.getSerializableExtra(updatedCity);
            if (city == null) {
                return;
            }

            TextView cityView = findViewById(R.id.textViewChosenCity);
            cityView.setText(city.getCountry() + ", " + city.getName());

            WeatherHelper.getTemperatureInfo(city.getId(), this);
        }
    }


}