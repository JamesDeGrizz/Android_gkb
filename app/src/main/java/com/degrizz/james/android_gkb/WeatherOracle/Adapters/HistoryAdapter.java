package com.degrizz.james.android_gkb.WeatherOracle.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.Database.HistorySource;
import com.degrizz.james.android_gkb.WeatherOracle.Database.Model.HistoryRecord;
import com.degrizz.james.android_gkb.WeatherOracle.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> implements Constants {
    private HistorySource historySource;

    public HistoryAdapter(HistorySource historySource) {
        this.historySource = historySource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<HistoryRecord> history = historySource.getHistory();
        HistoryRecord record = history.get(position);

        String temperature =  record.temperature.startsWith("-") ? record.temperature : '+' + record.temperature;
        holder.setTextToTextView(record.location, temperature, record.date);
    }

    @Override
    public int getItemCount() {
        return (int)historySource.getHistoryRecordsCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView location;
        TextView temperature;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.historyLocation);
            temperature = itemView.findViewById(R.id.historyTemperature);
            date = itemView.findViewById(R.id.historyDate);
        }

        public void setTextToTextView(String location, String temperature, String date) {
            this.location.setText(location);
            this.temperature.setText(temperature);
            this.date.setText(date);
        }
    }
}
