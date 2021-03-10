package com.anusha_peddina.WeatherApp.services.model.location_auto_complete;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationModel implements Serializable {
    @SerializedName("Version")
    public int version;

    @SerializedName("Key")
    public String key;

    @SerializedName("Type")
    public String type;

    @SerializedName("Rank")
    public int rank;

    @SerializedName("LocalizedName")
    public String localizedName;

    @SerializedName("Country")
    public AreaObject country;

    @SerializedName("AdministrativeArea")
    public AreaObject adminArea;

    @Override
    public String toString() {
        return "LocationAutoCompleteModel{" +
                "version=" + version +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", rank=" + rank +
                ", localizedName='" + localizedName + '\'' +
                ", country=" + country +
                ", adminArea=" + adminArea +
                '}';
    }
}
