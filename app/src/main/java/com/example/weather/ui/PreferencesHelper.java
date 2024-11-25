package com.example.weather.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "weather_preferences";
    private static final String KEY_CITY = "city_name";

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveCity(String cityName) {
        editor.putString(KEY_CITY, cityName);
        editor.apply();
    }

    public String getCity() {
        return sharedPreferences.getString(KEY_CITY, "");
    }
}
