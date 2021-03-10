package com.anusha_peddina.WeatherApp.services.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Metric implements Serializable {

    @SerializedName("Value")
    public double value;
    @SerializedName("Unit")
    public String unit;
    @SerializedName("UnitType")
    public int unitType;
}
