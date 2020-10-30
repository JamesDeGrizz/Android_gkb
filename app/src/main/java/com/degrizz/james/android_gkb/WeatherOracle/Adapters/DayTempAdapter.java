package com.degrizz.james.android_gkb.WeatherOracle.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.degrizz.james.android_gkb.WeatherOracle.R;

public class DayTempAdapter extends RecyclerView.Adapter<DayTempAdapter.ViewHolder> {
    private String[] hours;
    private String[] temperatures;

    public DayTempAdapter(String[] hours, String[] temperatures) {
        this.hours = hours;
        this.temperatures = temperatures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_temp_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String hour = hours[position];
        String temp = temperatures[position];
        holder.setTextToTextView(hour, temp);
    }

    @Override
    public int getItemCount() {
        return hours == null ? 0 : hours.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView hour;
        private TextView temperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.dayHour);
            temperature = itemView.findViewById(R.id.dayTemp);
        }

        public void setTextToTextView(String hour, String temperature) {
            this.hour.setText(hour);
            this.temperature.setText(temperature);
        }
    }
}
