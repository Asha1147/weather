package com.anusha_peddina.WeatherApp.services.model.current_conditions;

import com.anusha_peddina.WeatherApp.services.model.Metric;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrentTemperatureModel implements Serializable {

    @SerializedName("Metric")
    public Metric metric;
    @SerializedName("Imperial")
    public Metric imperial;
}