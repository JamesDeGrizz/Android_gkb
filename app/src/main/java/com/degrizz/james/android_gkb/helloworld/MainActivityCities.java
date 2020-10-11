package com.degrizz.james.android_gkb.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityCities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cities);

        Intent intent = new Intent();

        Button citiesCloseBtn = findViewById(R.id.buttonCitiesClose);
        citiesCloseBtn.setOnClickListener((View v) -> {
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        ImageView moscowView = findViewById(R.id.imageViewMoscow);
        moscowView.setOnClickListener((View v) -> {
            intent.putExtra("cityLabel", getString(R.string.citiesMoscowLabelText));
            setResult(RESULT_OK, intent);
            finish();
        });

        ImageView petersburgView = findViewById(R.id.imageViewPeterburg);
        petersburgView.setOnClickListener((View v) -> {
            intent.putExtra("cityLabel", getString(R.string.citiesPetersburgLabelText));
            setResult(RESULT_OK, intent);
            finish();
        });

        ImageView volgogradView = findViewById(R.id.imageViewVolgograd);
        volgogradView.setOnClickListener((View v) -> {
            intent.putExtra("cityLabel", getString(R.string.citiesVolgogradLabelText));
            setResult(RESULT_OK, intent);
            finish();
        });

        ImageView kazanView = findViewById(R.id.imageViewKazan);
        kazanView.setOnClickListener((View v) -> {
            intent.putExtra("cityLabel", getString(R.string.citiesKazanLabelText));
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}