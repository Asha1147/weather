package com.anusha_peddina.WeatherApp.services.model.city_search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CityModel implements Serializable {

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
    @SerializedName("EnglishName")
    public String englishName;
    @SerializedName("PrimaryPostalCode")
    public String primaryPostalCode;
    @SerializedName("Region")
    public Country region;
    @SerializedName("Country")
    public Country country;
    @SerializedName("AdministrativeArea")
    public AdministrativeArea administrativeArea;
    @SerializedName("TimeZone")
    public TimeZone timeZone;
    @SerializedName("GeoPosition")
    public GeoPosition geoPosition;
}