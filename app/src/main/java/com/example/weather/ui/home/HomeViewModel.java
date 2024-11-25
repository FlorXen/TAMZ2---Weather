package com.example.weather.ui.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.ui.PreferencesRepository;
import com.example.weather.ui.WeatherFetcher;
import com.example.weather.ui.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Objects;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Float> m_temperature;
    private MutableLiveData<String> m_description;
    private MutableLiveData<String> m_icon;
    private MutableLiveData<String> m_city;
    private Context cont;

    private PreferencesRepository preferencesRepository;

    public HomeViewModel() {
        m_temperature = new MutableLiveData<>();
        m_temperature.setValue(0.0f);

        m_description = new MutableLiveData<>();
        m_description.setValue("NULL");

        m_icon = new MutableLiveData<>();
        m_icon.setValue("01d");

        m_city = new MutableLiveData<>();
        m_city.setValue("");
    }

    public void setContext(Context context) {
        cont = context;
        preferencesRepository = new PreferencesRepository(context);

        if(!Objects.equals(preferencesRepository.getCity(), "")) {
            fetchWeather(preferencesRepository.getCity());
            m_city.postValue(preferencesRepository.getCity());
        }
    }

    public MutableLiveData<Float> getM_temperature() {
        return m_temperature;
    }

    public MutableLiveData<String> getM_description() {
        return m_description;
    }

    public MutableLiveData<String> getM_icon() {
        return m_icon;
    }

    public MutableLiveData<String> getM_city() {
        return m_city;
    }

    public void fetchWeather(String city) {
        Gson gson = new Gson();
        preferencesRepository.saveCity(city);
        new Thread(() -> {
            if (!isInternetWorking()) {
                m_description.postValue("No internet connection.");
                return;
            }
            WeatherFetcher fetcher = new WeatherFetcher();
            String json = fetcher.fetchWeather(city);

            if (json != null) {
                try {

                    WeatherResponse response = gson.fromJson(json, WeatherResponse.class);

                    m_temperature.postValue(response.main.temp);
                    m_description.postValue(response.weather.get(0).description);
                    m_icon.postValue(response.weather.get(0).icon);
                } catch (JsonSyntaxException e) {
                    m_description.postValue("City was not found.");
                }

            } else {
                m_description.postValue("Error fetching weather data.");
            }
        }).start();
    }

    private boolean isInternetWorking() {
        try {
            Process process = Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = process.waitFor();
            return (returnVal == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}