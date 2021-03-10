package com.anusha_peddina.WeatherApp.services.model.current_conditions;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrentConditionModel implements Serializable {

    @SerializedName("LocalObservationDateTime")
    public String localObservationDateTime;
    @SerializedName("EpochTime")
    public int epochTime;
    @SerializedName("WeatherText")
    public String weatherText;
    @SerializedName("WeatherIcon")
    public int weatherIcon;
    @SerializedName("HasPrecipitation")
    public Boolean hasPrecipitation;
    @SerializedName("PrecipitationType")
    public Object precipitationType;
    @SerializedName("IsDayTime")
    public Boolean isDayTime;
    @SerializedName("Temperature")
    public CurrentTemperatureModel temperature;
    @SerializedName("MobileLink")
    public String mobileLink;
    @SerializedName("Link")
    public String link;
}