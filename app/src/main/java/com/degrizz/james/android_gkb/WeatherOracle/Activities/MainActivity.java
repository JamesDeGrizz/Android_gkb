package com.degrizz.james.android_gkb.WeatherOracle.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.CrystalBallView;
import com.degrizz.james.android_gkb.WeatherOracle.Database.HistoryDatabase;
import com.degrizz.james.android_gkb.WeatherOracle.Database.HistorySource;
import com.degrizz.james.android_gkb.WeatherOracle.Database.Model.HistoryRecord;
import com.degrizz.james.android_gkb.WeatherOracle.Fragments.FragmentCities;
import com.degrizz.james.android_gkb.WeatherOracle.Fragments.FragmentHistory;
import com.degrizz.james.android_gkb.WeatherOracle.Fragments.FragmentMain;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Constants {
    private final String lastCityValueKey = "last_city_value";
    private final String lastTemperatureValueKey = "last_temperature_value";
    private HistoryDatabase db;
    private HistorySource historySource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        setMainFragment();
        Toolbar tb = initToolbar();
        initDrawer(tb);
        initDatabase();
    }

    public void update(String cityName, String country, float temperatureValue) {
        TextView cityView = findViewById(R.id.textViewChosenCity);
        cityView.setText(cityName + ", " + country);

        CrystalBallView temperature = findViewById(R.id.temperatureView);
        temperature.setText(String.format("%.2f", temperatureValue));

        updateHistory(cityName + ", " + country, temperatureValue);
        updateLastResult(cityName + ", " + country, temperatureValue);
        updateBackground(temperatureValue);
    }

    private void updateHistory(String cityName, float temperatureValue) {
        HistoryRecord rd = new HistoryRecord();
        rd.location = cityName;
        rd.temperature = String.format("%.0f", temperatureValue);
        rd.date = DateFormat.format("dd.MM.yyyy", new java.util.Date()).toString();
        historySource.addHistoryRecord(rd);
    }

    private void updateLastResult(String cityName, float temperatureValue) {
        getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putString(lastCityValueKey, cityName)
                .putFloat(lastTemperatureValueKey, temperatureValue)
                .apply();
    }

    private void updateBackground(float temperatureValue) {
        LinearLayout layout = findViewById(R.id.fragment_main);
        String uri;
        if (temperatureValue < 0) {
            uri = "https://unsplash.com/photos/w8hWTFpGtpY/download?force=true";
        } else if (temperatureValue < 10) {
            uri = "https://unsplash.com/photos/LtWFFVi1RXQ/download?force=true";
        } else {
            uri = "https://unsplash.com/photos/9jBs7zSbZ1s/download?force=true";
        }
        Picasso.get()
                .load(uri)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        layout.setBackground(new BitmapDrawable(bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {}

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {}
                });
    }

    public String getLastCity() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(lastCityValueKey, "Unknown");
    }

    public String getLastTemperature() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        float lastTemperature = sharedPref.getFloat(lastTemperatureValueKey, -1000.0f);
        return lastTemperature == -1000.0 ? "Unknown" : String.format("%.2f", lastTemperature);
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.nav_main) {
                    setMainFragment();
                } else if (id == R.id.nav_location) {
                    FragmentCities fr = new FragmentCities();
                    fr.show(getSupportFragmentManager(), getResources().getString(R.string.bottom_menu_location));
                } else if (id == R.id.nav_history) {
                    setHistoryFragment();
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }

        });
    }

    private void initDatabase() {
        db = Room.databaseBuilder(
                getApplicationContext(),
                HistoryDatabase.class,
                "history_database")
                .allowMainThreadQueries()
                .build();

        historySource = new HistorySource(db.getHistoryDao());
    }

    public void setHistoryFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main_layout, new FragmentHistory(historySource));
        fragmentTransaction.commit();
    }

    public void setMainFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main_layout, new FragmentMain());
        fragmentTransaction.commit();
    }
}