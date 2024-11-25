package com.example.weather.ui;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForecastFetcher {
    private static final String API_KEY = "d3fcee56e786693aa721df54ef917288";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    public String fetchForecast(String cityName) {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "?q=" + cityName + "&appid=" + API_KEY + "&units=metric";

        try {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                Log.e("ForecastFetcher", "HTTP Error: " + response.code() + " " + response.message());
                return "Error: " + response.code() + " " + response.message();
            }
        } catch (Exception e) {
            Log.e("ForecastFetcher", "Network Error", e);
            return "Error: " + e.getMessage();
        }
    }
}
