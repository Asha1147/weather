package com.anusha_peddina.WeatherApp.services.model.week_weather;

import com.anusha_peddina.WeatherApp.services.model.TemperatureUnitModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DayTemperature implements Serializable {

    @SerializedName("Minimum")
    public TemperatureUnitModel minimum;
    @SerializedName("Maximum")
    public TemperatureUnitModel maximum;
}