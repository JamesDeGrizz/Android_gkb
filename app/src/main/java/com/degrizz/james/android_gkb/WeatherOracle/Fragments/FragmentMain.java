package com.degrizz.james.android_gkb.WeatherOracle.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.degrizz.james.android_gkb.WeatherOracle.Activities.MainActivityCities;
import com.degrizz.james.android_gkb.WeatherOracle.Adapters.DayTempAdapter;
import com.degrizz.james.android_gkb.WeatherOracle.Adapters.WeeklyTempAdapter;
import com.degrizz.james.android_gkb.WeatherOracle.BuildConfig;
import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.Models.City;
import com.degrizz.james.android_gkb.WeatherOracle.Models.WeatherRequest;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class FragmentMain extends Fragment implements Constants {
    public FragmentMain() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        String[] days = getResources().getStringArray(R.array.weekly_days);
        String[] temperatures = getResources().getStringArray(R.array.weekly_temperature);
        initWeeklyView(view, days, temperatures);

        String[] hours = getResources().getStringArray(R.array.daily_hours);
        String[] dailyTemps = getResources().getStringArray(R.array.daily_temperature);
        initDailyView(view, hours, dailyTemps);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView city = view.findViewById(R.id.textViewChosenCity);
        city.setOnClickListener((View v) -> {
            Snackbar.make(view, R.string.snackbar_non_clickable, Snackbar.LENGTH_LONG).show();
        });

        TextView temperature = view.findViewById(R.id.textViewTemperature);
        temperature.setOnClickListener((View v) -> {
            Snackbar.make(view, R.string.snackbar_non_clickable, Snackbar.LENGTH_LONG).show();
        });

        TextView info = view.findViewById(R.id.textViewInfo);
        info.setOnClickListener((View v) -> {
            Snackbar.make(view, R.string.snackbar_location_warning, Snackbar.LENGTH_LONG).show();
        });

        View location = view.findViewById(R.id.choose_location);
        location.setOnClickListener((View v) -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivityCities.class);
            getActivity().startActivityForResult(intent, citiesActivityRequest);
        });

        View about = view.findViewById(R.id.show_about);
        about.setOnClickListener((View v) -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle(R.string.snackbar_about_test)
                    .setMessage((R.string.about_developer))
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        });
    }

    private void initWeeklyView(View view, String[] days, String[] temperatures) {
        RecyclerView weeklyView = view.findViewById(R.id.weeklyTempView);
        weeklyView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        weeklyView.setLayoutManager(layoutManager);

        WeeklyTempAdapter adapter = new WeeklyTempAdapter(days, temperatures);
        weeklyView.setAdapter(adapter);
    }

    private void initDailyView(View view, String[] hours, String[] temperatures) {
        RecyclerView dailyView = view.findViewById(R.id.dayTempView);
        dailyView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        dailyView.setLayoutManager(layoutManager);

        DayTempAdapter adapter = new DayTempAdapter(hours, temperatures);
        dailyView.setAdapter(adapter);
    }
}