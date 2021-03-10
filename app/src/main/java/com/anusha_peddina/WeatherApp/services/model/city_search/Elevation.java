package com.anusha_peddina.WeatherApp.services.model.city_search;

import com.anusha_peddina.WeatherApp.services.model.Metric;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Elevation implements Serializable {

    @SerializedName("Metric")
    public Metric metric;
    @SerializedName("Imperial")
    public Metric imperial;

}
