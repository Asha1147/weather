package com.anusha_peddina.WeatherApp.services.model.week_weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeekWeatherModel implements Serializable {
    @SerializedName("Headline")
    public Headline headline;
    @SerializedName("DailyForecasts")
    public List<DailyForecast> dailyForecasts = null;
}
