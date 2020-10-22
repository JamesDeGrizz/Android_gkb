package com.degrizz.james.android_gkb.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeeklyTempAdapter extends RecyclerView.Adapter<WeeklyTempAdapter.ViewHolder> {
    private String[] days;
    private String[] temperatures;

    public WeeklyTempAdapter(String[] days, String[] temperatures) {
        this.days = days;
        this.temperatures = temperatures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_temp_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String day = days[position];
        String temp = temperatures[position];
        holder.setTextToTextView(day, temp);
    }

    @Override
    public int getItemCount() {
        return days == null ? 0 : days.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView day;
        private TextView temperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.weeklyDay);
            temperature = itemView.findViewById(R.id.weeklyTemp);
        }

        public void setTextToTextView(String day, String temperature) {
            this.day.setText(day);
            this.temperature.setText(temperature);
        }
    }
}
