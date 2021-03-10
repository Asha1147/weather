package com.anusha_peddina.WeatherApp.services.model.location_auto_complete;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AreaObject implements Serializable {

    @SerializedName("ID")
    public String id;

    @SerializedName("LocalizedName")
    public String localizedName;

    @Override
    public String toString() {
        return "AreaObject{" +
                "id='" + id + '\'' +
                ", localizedName='" + localizedName + '\'' +
                '}';
    }
}
