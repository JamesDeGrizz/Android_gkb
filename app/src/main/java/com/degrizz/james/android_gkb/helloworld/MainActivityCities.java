package com.degrizz.james.android_gkb.helloworld;

import androidx.appcompat.app.AppCompatActivity;

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

        Button citiesCloseBtn = findViewById(R.id.buttonCitiesClose);
        citiesCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
            }
        });

        ImageView moscowView = findViewById(R.id.imageViewMoscow);
        moscowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView cityLabel = findViewById(R.id.textViewChoosedCity);
                cityLabel.setText("Moscow");
                setContentView(R.layout.activity_main);
            }
        });
    }
}