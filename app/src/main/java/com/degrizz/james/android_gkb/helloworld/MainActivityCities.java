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

public class MainActivityCities extends AppCompatActivity implements Constants {
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
            onClickListenerInternal("moscowView", getString(R.string.citiesMoscowLabelText), intent);
        });

        ImageView petersburgView = findViewById(R.id.imageViewPeterburg);
        petersburgView.setOnClickListener((View v) -> {
            onClickListenerInternal("petersburgView", getString(R.string.citiesPetersburgLabelText), intent);
        });

        ImageView volgogradView = findViewById(R.id.imageViewVolgograd);
        volgogradView.setOnClickListener((View v) -> {
            onClickListenerInternal("volgogradView", getString(R.string.citiesVolgogradLabelText), intent);
        });

        ImageView kazanView = findViewById(R.id.imageViewKazan);
        kazanView.setOnClickListener((View v) -> {
            onClickListenerInternal("kazanView", getString(R.string.citiesKazanLabelText), intent);
        });
    }

    private void onClickListenerInternal(String viewClicked, String newCityName, Intent result) {
        Toast.makeText(getApplicationContext(), "MainActivityCities " + viewClicked + " clicked", Toast.LENGTH_SHORT).show();
        Log.d(logTag, "MainActivityCities " + viewClicked + " clicked");
        updateCity(newCityName);

        result.putExtra(updatedCityName, newCityName);
        result.putExtra(updatedTemperatureName, getTemperatureStub(newCityName));
        result.putExtra(updatedInfoLinkName, getCityInfoStub(newCityName));

        setResult(citiesActivityResult, result);
        finish();
    }

    private void updateCity(String newValue) {
        if (newValue != null) {
            Log.d(logTag, "MainActivityCities update AppData.city with value " + newValue);
            AppData.getInstance().city = newValue;
        }
    }

    private int getTemperatureStub(String cityName) {
        if (cityName == getString(R.string.citiesMoscowLabelText)) {
            return 20;
        }
        if (cityName == getString(R.string.citiesPetersburgLabelText)) {
            return 15;
        }
        if (cityName == getString(R.string.citiesVolgogradLabelText)) {
            return 25;
        }
        if (cityName == getString(R.string.citiesKazanLabelText)) {
            return 22;
        }
        return 1000;
    }

    private String getCityInfoStub(String cityName) {
        if (cityName == getString(R.string.citiesMoscowLabelText)) {
            return "https://en.wikipedia.org/wiki/Moscow";
        }
        if (cityName == getString(R.string.citiesPetersburgLabelText)) {
            return "https://en.wikipedia.org/wiki/Saint_Petersburg";
        }
        if (cityName == getString(R.string.citiesVolgogradLabelText)) {
            return "https://en.wikipedia.org/wiki/Volgograd";
        }
        if (cityName == getString(R.string.citiesKazanLabelText)) {
            return "https://en.wikipedia.org/wiki/Kazan";
        }
        return "https://www.merriam-webster.com/dictionary/in%20the%20middle%20of%20nowhere";
    }
}