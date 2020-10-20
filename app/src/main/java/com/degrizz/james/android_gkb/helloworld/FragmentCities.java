package com.degrizz.james.android_gkb.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentCities extends Fragment implements Constants {
    private String logTag = "FragmentCities";

    public FragmentCities() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities, container, false);
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

        ImageView moscowView = view.findViewById(R.id.imageViewMoscow);
        moscowView.setOnClickListener((View v) -> {
            onClickListenerInternal("moscowView", getString(R.string.citiesMoscowLabelText), intent);
        });

        ImageView petersburgView = view.findViewById(R.id.imageViewPeterburg);
        petersburgView.setOnClickListener((View v) -> {
            onClickListenerInternal("petersburgView", getString(R.string.citiesPetersburgLabelText), intent);
        });

        ImageView volgogradView = view.findViewById(R.id.imageViewVolgograd);
        volgogradView.setOnClickListener((View v) -> {
            onClickListenerInternal("volgogradView", getString(R.string.citiesVolgogradLabelText), intent);
        });

        ImageView kazanView = view.findViewById(R.id.imageViewKazan);
        kazanView.setOnClickListener((View v) -> {
            onClickListenerInternal("kazanView", getString(R.string.citiesKazanLabelText), intent);
        });
    }

    private void onClickListenerInternal(String viewClicked, String newCityName, Intent result) {
        Log.d(logTag, "MainActivityCities " + viewClicked + " clicked");
        updateCity(newCityName);

        result.putExtra(updatedCityName, newCityName);
        result.putExtra(updatedTemperatureName, getTemperatureStub(newCityName));
        result.putExtra(updatedInfoLinkName, getCityInfoStub(newCityName));

        getActivity().setResult(citiesActivityResult, result);
        getActivity().finish();
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