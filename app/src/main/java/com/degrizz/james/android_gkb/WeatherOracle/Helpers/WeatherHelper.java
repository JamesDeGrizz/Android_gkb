package com.degrizz.james.android_gkb.WeatherOracle.Helpers;

import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.degrizz.james.android_gkb.WeatherOracle.Activities.MainActivity;
import com.degrizz.james.android_gkb.WeatherOracle.BuildConfig;
import com.degrizz.james.android_gkb.WeatherOracle.Models.WeatherRequest;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class WeatherHelper {
    private static final String TAG = "WeatherHelper";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?id=%d&appid=";
    private final static float KELVINS = 273.15f;
    
    public static void getTemperatureInfo(int id, AppCompatActivity activity) {
        try {
            String url = String.format(WEATHER_URL, id);
            final URL uri = new URL(url + BuildConfig.WEATHER_API_KEY);
            final Handler handler = new Handler();
            new Thread(() -> {
                HttpsURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpsURLConnection) uri.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String result = getLines(in);
                    Gson gson = new Gson();
                    final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                    handler.post(() -> {
                        float temp = weatherRequest.getMain().getTemp() - KELVINS;
                        TextView temperatureView = activity.findViewById(R.id.textViewTemperature);
                        temperatureView.setText(String.format("%.2f", temp));

                        MainActivity mActivity = (MainActivity)activity;
                        TextView cityTextView = activity.findViewById(R.id.textViewChosenCity);
                        mActivity.updateHistory(cityTextView.getText().toString(), temp);
                    });
                } catch (Exception e) {
                    Log.e(TAG, "Fail connection", e);
                    e.printStackTrace();
                } finally {
                    if (null != urlConnection) {
                        urlConnection.disconnect();
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            Log.e(TAG, "Fail URI", e);
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(R.string.alert_dialog_message)
                    .setTitle(R.string.alert_dialog_title)
                    .setPositiveButton(R.string.alert_dialog_positive_button_text, (dialog, id1) -> {
                        dialog.cancel();
                    }) ;
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private static String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }
}
