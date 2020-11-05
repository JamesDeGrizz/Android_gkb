package com.degrizz.james.android_gkb.WeatherOracle.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.degrizz.james.android_gkb.WeatherOracle.Activities.MainActivity;
import com.degrizz.james.android_gkb.WeatherOracle.Adapters.HistoryAdapter;
import com.degrizz.james.android_gkb.WeatherOracle.Constants;
import com.degrizz.james.android_gkb.WeatherOracle.R;

import java.util.Set;

public class FragmentHistory extends Fragment implements Constants {
    private static final String TAG = "FragmentHistory";

    public FragmentHistory() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        Set<String> history = loadHistoryList();
        initHistoryView(view, history);
        return view;
    }

    private void initHistoryView(View view, Set<String> history) {
        RecyclerView historyView = view.findViewById(R.id.historyView);
        historyView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        historyView.setLayoutManager(layoutManager);

        HistoryAdapter adapter = new HistoryAdapter(history);
        historyView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button citiesCloseBtn = view.findViewById(R.id.buttonHistoryClose);
        citiesCloseBtn.setOnClickListener((View v) -> {
            MainActivity act = (MainActivity) getActivity();
            act.setMainFragment();
        });
    }

    private Set<String> loadHistoryList() {
        MainActivity act = (MainActivity) getActivity();
        return act.getHistory();
    }
}