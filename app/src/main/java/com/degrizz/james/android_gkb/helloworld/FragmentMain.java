package com.degrizz.james.android_gkb.helloworld;

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
import android.widget.Toast;

public class FragmentMain extends Fragment implements Constants {

    public FragmentMain() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button citiesBtn = view.findViewById(R.id.citiesButton);
        citiesBtn.setOnClickListener((View v) -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivityCities.class);
            getActivity().startActivityForResult(intent, citiesActivityRequest);
        });
    }
}