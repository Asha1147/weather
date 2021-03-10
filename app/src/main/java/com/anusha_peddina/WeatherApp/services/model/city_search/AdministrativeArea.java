package com.anusha_peddina.WeatherApp.services.model.city_search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AdministrativeArea implements Serializable {
    @SerializedName("ID")
    public String iD;
    @SerializedName("LocalizedName")
    public String localizedName;
    @SerializedName("EnglishName")
    public String englishName;
    @SerializedName("Level")
    public Integer level;
    @SerializedName("LocalizedType")
    public String localizedType;
    @SerializedName("EnglishType")
    public String englishType;
    @SerializedName("CountryID")
    public String countryID;
}
