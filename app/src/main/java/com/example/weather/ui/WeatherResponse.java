package com.example.weather.ui;

import java.util.List;

public class WeatherResponse {
    public List<Weather> weather;
    public Main main;
    public static class Weather {
        public String description;
        public String icon;
    }
    public static class Main {
        public float temp;
    }
}
