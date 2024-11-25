package com.example.weather.ui;

public class ForecastItem {
    public double temp;
    public String description;
    public String icon;
    public String dt_txt;

    public ForecastItem(double temp, String description, String icon, String dt_txt) {
        this.temp = temp;
        this.description = description;
        this.icon = icon;
        this.dt_txt = dt_txt;
    }
}
