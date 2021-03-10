package com.anusha_peddina.WeatherApp.services.model.week_weather;

import com.anusha_peddina.WeatherApp.services.model.TemperatureIconModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DailyForecast implements Serializable {

    @SerializedName("Date")
    public String date;
    @SerializedName("EpochDate")
    public int epochDate;
    @SerializedName("Temperature")
    public DayTemperature temperature;
    @SerializedName("Day")
    public TemperatureIconModel day;
    @SerializedName("Night")
    public TemperatureIconModel night;
    @SerializedName("Sources")
    public List<String> sources = null;
    @SerializedName("MobileLink")
    public String mobileLink;
    @SerializedName("Link")
    public String link;
}