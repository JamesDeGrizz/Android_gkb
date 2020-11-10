package com.degrizz.james.android_gkb.WeatherOracle.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.Fragments.FragmentHistory;
import com.degrizz.james.android_gkb.WeatherOracle.Fragments.FragmentMain;
import com.degrizz.james.android_gkb.WeatherOracle.Helpers.WeatherHelper;
import com.degrizz.james.android_gkb.WeatherOracle.Models.City;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements Constants {
    private final String historyKey = "history";
    private final String lastCityValueKey = "last_city_value";
    private final String lastTemperatureValueKey = "last_temperature_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        setMainFragment();
        Toolbar tb = initToolbar();
        initDrawer(tb);
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

    public void updateHistory(String cityName, float temperatureValue) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        Set<String> history = sharedPref.getStringSet(historyKey, new LinkedHashSet<>());
        history.add(cityName + "__" + temperatureValue);
        if (history.size() > 20) {
            for (Iterator<String> it = history.iterator(); it.hasNext() && history.size() > 20;) {
                it.next();
                it.remove();
            }
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(historyKey, history);
        editor.putString(lastCityValueKey, cityName);
        editor.putFloat(lastTemperatureValueKey, temperatureValue);
        editor.apply();
    }

    public String getLastTemperature() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(lastCityValueKey, "Unknown");
    }

    public String getLastCity() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        float lastTemperature = sharedPref.getFloat(lastTemperatureValueKey, -1000.0f);
        return lastTemperature == -1000.0 ? "Unknown" : String.format("%.2f", lastTemperature);
    }

    public Set<String> getHistory() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getStringSet(historyKey, new LinkedHashSet<>());
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
                    Intent intent = new Intent();
                    intent.setClass(getBaseContext(), MainActivityCities.class);
                    startActivityForResult(intent, citiesActivityRequest);
                } else if (id == R.id.nav_history) {
                    setHistoryFragment();
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }

        });
    }

    public void setHistoryFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main_layout, new FragmentHistory());
        fragmentTransaction.commit();
    }

    public void setMainFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main_layout, new FragmentMain());
        fragmentTransaction.commit();
    }
}