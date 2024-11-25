package com.example.weather.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Boolean> trigger = new MutableLiveData<>();

    public LiveData<Boolean> getTrigger() {
        return trigger;
    }

    public void setTrigger(Boolean value) {
        trigger.setValue(value);
    }
}