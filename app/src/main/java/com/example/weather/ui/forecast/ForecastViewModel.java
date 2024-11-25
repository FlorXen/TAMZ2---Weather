package com.example.weather.ui.forecast;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.ui.ForecastFetcher;
import com.example.weather.ui.ForecastItem;
import com.example.weather.ui.ForecastResponse;
import com.example.weather.ui.PreferencesRepository;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class ForecastViewModel extends ViewModel {

    private final MutableLiveData<List<ForecastItem>> mList;
    private PreferencesRepository preferencesRepository;

    public ForecastViewModel() {
        mList = new MutableLiveData<>(new ArrayList<>());
        mList.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<ForecastItem>> getList() {
        return mList;
    }

    public void fetchForecast(Context context) {

        preferencesRepository = new PreferencesRepository(context);
        String city = preferencesRepository.getCity();


        if(city != null) {
            Gson gson = new Gson();

            new Thread(() -> {
                if (!isInternetWorking()) {
                    mList.postValue(new ArrayList<>());
                    return;
                }
                ForecastFetcher fetcher = new ForecastFetcher();
                String json = fetcher.fetchForecast(city);


                if (json != null) {
                    try {
                    ForecastResponse response = gson.fromJson(json, ForecastResponse.class);

                    List<ForecastResponse.ForecastData> data = response.getList();
                    List<ForecastItem> list = new ArrayList<>();
                    
                    for (int i = 0; i < data.size(); i++){
                        ForecastItem item = new ForecastItem(data.get(i).getMain().getTemp(),
                                data.get(i).getWeather().get(0).getDescription(),
                                data.get(i).getWeather().get(0).getIcon(),
                                data.get(i).getDt_txt());
                        
                        list.add(item);
                    }
                    mList.postValue(list);
                    } catch (JsonSyntaxException e) {
                        mList.postValue(new ArrayList<>());
                    }
                }
            }).start();
        }
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