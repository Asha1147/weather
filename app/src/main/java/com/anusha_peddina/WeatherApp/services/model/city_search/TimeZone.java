package com.anusha_peddina.WeatherApp.services.model.city_search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TimeZone implements Serializable {

    @SerializedName("Code")
    public String code;
    @SerializedName("Name")
    public String name;
    @SerializedName("GmtOffset")
    public int gmtOffset;
    @SerializedName("IsDaylightSaving")
    public Boolean isDaylightSaving;
    @SerializedName("NextOffsetChange")
    public String nextOffsetChange;
}
