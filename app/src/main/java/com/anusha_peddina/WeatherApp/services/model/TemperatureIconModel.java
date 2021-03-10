package com.anusha_peddina.WeatherApp.services.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TemperatureIconModel implements Serializable {

    @SerializedName("Icon")
    public int icon;
    @SerializedName("IconPhrase")
    public String iconPhrase;
    @SerializedName("HasPrecipitation")
    public Boolean hasPrecipitation;
    @SerializedName("PrecipitationType")
    public String precipitationType;
    @SerializedName("PrecipitationIntensity")
    public String precipitationIntensity;
}