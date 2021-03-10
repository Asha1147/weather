package com.anusha_peddina.WeatherApp.services.model.city_search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {
    @SerializedName("ID")
    public String iD;
    @SerializedName("LocalizedName")
    public String localizedName;
    @SerializedName("EnglishName")
    public String englishName;
}