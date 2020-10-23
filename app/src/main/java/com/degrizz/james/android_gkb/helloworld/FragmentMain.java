package com.degrizz.james.android_gkb.helloworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        TextView chosenCity = view.findViewById(R.id.textViewChosenCity);
        chosenCity.setOnClickListener((View v) -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivityCities.class);
            getActivity().startActivityForResult(intent, citiesActivityRequest);
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