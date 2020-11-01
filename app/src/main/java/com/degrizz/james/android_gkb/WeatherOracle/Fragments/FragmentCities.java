package com.degrizz.james.android_gkb.WeatherOracle.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.degrizz.james.android_gkb.WeatherOracle.Adapters.CityChooserAdapter;
import com.degrizz.james.android_gkb.WeatherOracle.Adapters.WeeklyTempAdapter;
import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.Models.City;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class FragmentCities extends Fragment implements Constants {
    private String logTag = "FragmentCities";

    public FragmentCities() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cities, container, false);

        ArrayList<City> cities = loadCitiesList(view);
        initCitiesView(view, cities);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = new Intent();

        Button citiesCloseBtn = view.findViewById(R.id.buttonCitiesClose);
        citiesCloseBtn.setOnClickListener((View v) -> {
            Log.d(logTag, "MainActivityCities citiesCloseBtn clicked");
            getActivity().setResult(Activity.RESULT_CANCELED, intent);
            getActivity().finish();
        });

        final TextInputLayout cityFilterLayout = (TextInputLayout) view.findViewById(R.id.cityFilterLayout);
        cityFilterLayout.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {
                String filter = s.toString();
                RecyclerView citiesView = view.findViewById(R.id.citiesView);
                CityChooserAdapter adapter = (CityChooserAdapter)citiesView.getAdapter();
                adapter.setFilter(filter);
            }
        });
    }

    private void initCitiesView(View view, ArrayList<City> cities) {
        RecyclerView citiesView = view.findViewById(R.id.citiesView);
        citiesView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        citiesView.setLayoutManager(layoutManager);

        CityChooserAdapter adapter = new CityChooserAdapter(cities, this);
        citiesView.setAdapter(adapter);
    }

    private ArrayList<City> loadCitiesList(View view) {
        try {
            InputStream is = view.getContext().getAssets().open("city.list.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String citiesList = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            return new ArrayList<>(Arrays.asList(gson.fromJson(citiesList, City[].class)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}