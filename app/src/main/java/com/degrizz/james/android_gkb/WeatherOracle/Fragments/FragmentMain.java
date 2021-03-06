package com.degrizz.james.android_gkb.WeatherOracle.Fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.degrizz.james.android_gkb.WeatherOracle.Activities.MainActivity;
import com.degrizz.james.android_gkb.WeatherOracle.Adapters.DayTempAdapter;
import com.degrizz.james.android_gkb.WeatherOracle.Adapters.WeeklyTempAdapter;
import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.CrystalBallView;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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

        View locationMenuButton = view.findViewById(R.id.choose_location);
        locationMenuButton.setOnClickListener((View v) -> {
            FragmentCities fr = new FragmentCities();
            fr.show(getActivity().getSupportFragmentManager(), getResources().getString(R.string.bottom_menu_location));
        });

        ImageView locationImage = view.findViewById(R.id.imageChosenCityLocation);
        Picasso.get()
                .load(R.drawable.location)
                .into(locationImage);

        View about = view.findViewById(R.id.show_about);
        about.setOnClickListener((View v) -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle(R.string.snackbar_about_test)
                    .setMessage((R.string.about_developer))
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        });

        setLastCityAndTempValues();
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

    void setLastCityAndTempValues() {
        MainActivity act = (MainActivity) getActivity();

        CrystalBallView temperatureView = act.findViewById(R.id.temperatureView);
        String temperature = act.getLastTemperature();
        temperatureView.setText(temperature);

        TextView cityTextView = act.findViewById(R.id.textViewChosenCity);
        cityTextView.setText(act.getLastCity());
    }
}