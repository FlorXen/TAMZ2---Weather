package com.example.weather.ui;

import android.content.Context;

public class PreferencesRepository {
    private PreferencesHelper preferencesHelper;

    public PreferencesRepository(Context context) {
        preferencesHelper = new PreferencesHelper(context);
    }

    public void saveCity(String cityName) {
        preferencesHelper.saveCity(cityName);
    }

    public String getCity() {
        return preferencesHelper.getCity();
    }
}
