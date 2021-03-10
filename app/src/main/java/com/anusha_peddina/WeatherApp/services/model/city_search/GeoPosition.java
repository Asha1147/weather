package com.anusha_peddina.WeatherApp.services.model.city_search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeoPosition implements Serializable {

    @SerializedName("Latitude")
    public Double latitude;
    @SerializedName("Longitude")
    public Double longitude;
    @SerializedName("Elevation")
    public Elevation elevation;
}