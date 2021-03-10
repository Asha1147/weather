package com.anusha_peddina.WeatherApp.services.model.week_weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Headline implements Serializable {
    @SerializedName("EffectiveDate")
    public String effectiveDate;
    @SerializedName("EffectiveEpochDate")
    public int effectiveEpochDate;
    @SerializedName("Severity")
    public int severity;
    @SerializedName("Text")
    public String text;
    @SerializedName("Category")
    public String category;
    @SerializedName("EndDate")
    public String endDate;
    @SerializedName("EndEpochDate")
    public int endEpochDate;
    @SerializedName("MobileLink")
    public String mobileLink;
    @SerializedName("Link")
    public String link;
}