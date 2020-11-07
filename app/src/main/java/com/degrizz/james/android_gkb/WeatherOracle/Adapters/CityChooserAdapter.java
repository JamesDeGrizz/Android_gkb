package com.degrizz.james.android_gkb.WeatherOracle.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.Helpers.WeatherHelper;
import com.degrizz.james.android_gkb.WeatherOracle.Models.City;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.subinkrishna.widget.CircularImageView;

import java.util.ArrayList;

public class CityChooserAdapter extends RecyclerView.Adapter<CityChooserAdapter.ViewHolder> implements Constants {
    private ArrayList<City> cities;
    private ArrayList<City> citiesFiltered;
    private BottomSheetDialogFragment parent;

    public CityChooserAdapter(ArrayList<City> cities, BottomSheetDialogFragment parent) {
        this.cities = cities;
        citiesFiltered = new ArrayList<>(this.cities);
        this.parent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_chooser_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String country = citiesFiltered.get(position).getCountry();
        String city = citiesFiltered.get(position).getName();
        int id = citiesFiltered.get(position).getId();
        holder.setTextToTextView(country, city, id);
        holder.city.setOnClickListener(v -> {
            City chosenCity = citiesFiltered.get(position);
            WeatherHelper.getTemperatureInfo(chosenCity.getName(), chosenCity.getCountry(), chosenCity.getId(), parent);
        });
    }

    @Override
    public int getItemCount() {
        return citiesFiltered == null ? 0 : citiesFiltered.size();
    }

    public void setFilter(String filter) {
        citiesFiltered.clear();
        if (filter.isEmpty()) {
            citiesFiltered = new ArrayList<>(this.cities);
            return;
        }
        for (City city : cities) {
            if (city.getName().contains(filter)) {
                citiesFiltered.add(city);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CircularImageView country;
        TextView city;
        int id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.countryName);
            city = itemView.findViewById(R.id.cityName);
        }

        public void setTextToTextView(String country, String city, int id) {
            this.country.setPlaceholder(country);
            this.city.setText(city);
            this.id = id;
        }
    }
}
