package com.degrizz.james.android_gkb.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String logTag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "MainActivity onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(logTag, "MainActivity onCreate()");
        updateCityTextViewText();

        Button citiesBtn = findViewById(R.id.citiesButton);
        citiesBtn.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "MainActivity citiesBtn clicked", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivity citiesBtn clicked");

            // @TODO !!! WTF RESULT_OK doesn't work
            startActivityForResult(new Intent(this, MainActivityCities.class), 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "MainActivity onActivityResult from MainActivityCities", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivity onActivityResult from MainActivityCities");
            updateCityTextViewText();
        }
    }

    private void updateCityTextViewText() {
        if (AppData.getInstance().city != null) {
            TextView cityView = findViewById(R.id.textViewChosenCity);
            String newValue = AppData.getInstance().city;
            cityView.setText(newValue);
            Log.d(logTag, "MainActivity update textViewChoosedCity with value " + newValue);
        }
    }
}