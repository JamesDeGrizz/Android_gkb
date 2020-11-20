package com.degrizz.james.android_gkb.WeatherOracle.Helpers;

import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.degrizz.james.android_gkb.WeatherOracle.Activities.MainActivity;
import com.degrizz.james.android_gkb.WeatherOracle.BuildConfig;
import com.degrizz.james.android_gkb.WeatherOracle.Models.WeatherRequest;
import com.degrizz.james.android_gkb.WeatherOracle.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface OpenWeather {
    @GET("data/2.5/weather")
    Call<WeatherRequest> loadWeather(@Query("id") int id, @Query("appid") String keyApi);
}

public class WeatherHelper {
    private static final String TAG = "WeatherHelper";
    private final static float KELVINS = 273.15f;
    
    public static void getTemperatureInfo(String city, String country, int id, BottomSheetDialogFragment parent) {
            OpenWeather openWeather = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OpenWeather.class);

            openWeather.loadWeather(id, BuildConfig.WEATHER_API_KEY).enqueue(new Callback<WeatherRequest>() {
                @Override
                public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                    if (response.body() != null) {
                        MainActivity mActivity = (MainActivity) parent.getActivity();
                        mActivity.runOnUiThread(() -> {
                            float temp = response.body().getMain().getTemp() - KELVINS;
                            mActivity.update(city, country, temp);
                            parent.dismiss();
                        });
                    }
                }

                @Override
                public void onFailure(Call<WeatherRequest> call, Throwable t) {
                    Log.e(TAG, "Fail URI");
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    builder.setMessage(R.string.alert_dialog_message)
                            .setTitle(R.string.alert_dialog_title)
                            .setPositiveButton(R.string.alert_dialog_positive_button_text, (dialog, id1) -> {
                                dialog.cancel();
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    parent.dismiss();
                }
            });

    }
}
