package com.degrizz.james.android_gkb.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button citiesBtn = findViewById(R.id.citiesButton);
        citiesBtn.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MainActivityCities.class);

            // @TODO !!! WTF RESULT_OK doesn't work
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String labelText = data.getStringExtra("cityLabel");
            if (labelText == null) {
                return;
            }
            TextView cityLabel = findViewById(R.id.textViewChoosedCity);
            cityLabel.setText(labelText);
        }
    }
}