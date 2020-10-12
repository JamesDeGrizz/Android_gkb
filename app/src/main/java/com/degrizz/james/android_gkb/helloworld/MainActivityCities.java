package com.degrizz.james.android_gkb.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityCities extends AppCompatActivity {
    private String logTag = "MainActivityCities";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "MainActivityCities onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(logTag, "MainActivityCities onCreate()");
        setContentView(R.layout.activity_main_cities);

        Intent intent = new Intent();

        Button citiesCloseBtn = findViewById(R.id.buttonCitiesClose);
        citiesCloseBtn.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "MainActivityCities citiesCloseBtn clicked", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivityCities citiesCloseBtn clicked");
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        ImageView moscowView = findViewById(R.id.imageViewMoscow);
        moscowView.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "MainActivityCities moscowView clicked", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivityCities moscowView clicked");
            updateCity(getString(R.string.citiesMoscowLabelText));
            setResult(RESULT_OK, intent);
            finish();
        });

        ImageView petersburgView = findViewById(R.id.imageViewPeterburg);
        petersburgView.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "MainActivityCities petersburgView clicked", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivityCities petersburgView clicked");
            updateCity(getString(R.string.citiesPetersburgLabelText));
            setResult(RESULT_OK, intent);
            finish();
        });

        ImageView volgogradView = findViewById(R.id.imageViewVolgograd);
        volgogradView.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "MainActivityCities volgogradView clicked", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivityCities volgogradView clicked");
            updateCity(getString(R.string.citiesVolgogradLabelText));
            setResult(RESULT_OK, intent);
            finish();
        });

        ImageView kazanView = findViewById(R.id.imageViewKazan);
        kazanView.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "MainActivityCities kazanView clicked", Toast.LENGTH_SHORT).show();
            Log.d(logTag, "MainActivityCities kazanView clicked");
            updateCity(getString(R.string.citiesKazanLabelText));
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void updateCity(String newValue) {
        if (newValue != null) {
            Log.d(logTag, "MainActivityCities update AppData.city with value " + newValue);
            AppData.getInstance().city = newValue;
        }
    }
}