package com.degrizz.james.android_gkb.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Constants {
    private String logTag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "MainActivity onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(logTag, "MainActivity onCreate()");

        Button citiesBtn = findViewById(R.id.citiesButton);
        citiesBtn.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "MainActivity citiesBtn clicked", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivity citiesBtn clicked");

            startActivityForResult(new Intent(this, MainActivityCities.class), citiesActivityRequest);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == citiesActivityRequest && resultCode == citiesActivityResult) {
            Toast.makeText(getApplicationContext(), "MainActivity onActivityResult from MainActivityCities", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivity onActivityResult from MainActivityCities");

            String newName = data.getStringExtra(updatedCityName);
            if (newName != null) {
                TextView cityView = findViewById(R.id.textViewChosenCity);
                cityView.setText(newName);
                Log.d(logTag, "MainActivity update textViewChosenCity with value " + newName);
            }

            int newTemperature = data.getIntExtra(updatedTemperatureName, 1000);
            if (newTemperature != 1000) {
                TextView temperatureView = findViewById(R.id.textViewTemperature);
                temperatureView.setText(Integer.toString(newTemperature));
                Log.d(logTag, "MainActivity update textViewTemperature with value " + newTemperature);
            }

            String newInfoLink = data.getStringExtra(updatedInfoLinkName);
            if (newInfoLink != null) {
                TextView infoLinkView = findViewById(R.id.textViewInfo);
                infoLinkView.setText(R.string.infoTextViewClickMeText);
                infoLinkView.setOnClickListener((View v) -> {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(newInfoLink));
                    startActivity(i);
                });
            }
        }
    }
}