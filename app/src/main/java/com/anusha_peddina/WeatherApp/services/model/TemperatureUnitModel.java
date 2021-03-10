package com.anusha_peddina.WeatherApp.services.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TemperatureUnitModel implements Serializable {

    @SerializedName("Value")
    public int value;

    @SerializedName("Unit")
    public String unit;

    @SerializedName("UnitType")
    public int unitType;

    @Override
    public String toString() {
        return "TemperatureModel{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", unitType=" + unitType +
                '}';
    }
}