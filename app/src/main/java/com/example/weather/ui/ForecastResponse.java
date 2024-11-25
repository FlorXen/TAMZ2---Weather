package com.example.weather.ui;

import java.util.List;

public class ForecastResponse {
    private List<ForecastData> list;

    public List<ForecastData> getList() {
        return list;
    }

    public void setList(List<ForecastData> list) {
        this.list = list;
    }

    public static class ForecastData {
        private Main main;
        private List<Weather> weather;
        private String dt_txt;

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        public static class Main {
            private float temp;

            public float getTemp() {
                return temp;
            }

            public void setTemp(float temp) {
                this.temp = temp;
            }
        }

        public static class Weather {
            private String description;
            private String icon;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
