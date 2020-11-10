package com.degrizz.james.android_gkb.WeatherOracle.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.R;

import java.util.Set;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> implements Constants {
    private Set<String> history;

    public HistoryAdapter(Set<String> history) {
        this.history = history;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = 0;
        String historyItem = null;
        for (String s : history) {
            if (i == position) {
                historyItem = s;
                break;
            }
            ++i;
        }
        String[] items = historyItem.split("__");
        holder.setTextToTextView(items[0], items[1]);
    }

    @Override
    public int getItemCount() {
        return history == null ? 0 : history.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView location;
        TextView temperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.historyLocation);
            temperature = itemView.findViewById(R.id.historyTemperature);
        }

        public void setTextToTextView(String location, String temperature) {
            this.location.setText(location);
            this.temperature.setText(temperature);
        }
    }
}
