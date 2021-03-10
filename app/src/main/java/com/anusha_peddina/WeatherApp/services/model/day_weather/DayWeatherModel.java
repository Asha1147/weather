package com.anusha_peddina.WeatherApp.services.model.day_weather;


import com.anusha_peddina.WeatherApp.services.model.TemperatureUnitModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class DayWeatherModel implements Serializable {

    @SerializedName("DateTime")
    public String dateTime;

    @SerializedName("EpochDateTime")
    public long epochDateTime;

    @SerializedName("WeatherIcon")
    public int weatherIcon;

    @SerializedName("IconPhrase")
    public String iconPhrase;

    @SerializedName("HasPrecipitation")
    public Boolean hasPrecipitation;

    @SerializedName("IsDaylight")
    public Boolean isDaylight;

    @SerializedName("Temperature")
    public TemperatureUnitModel temperatureUnitModel;

    @SerializedName("PrecipitationProbability")
    public int precipitationProbability;

    @SerializedName("MobileLink")
    public String mobileLink;

    @SerializedName("Link")
    public String link;

    @Override
    public String toString() {
        return "TwelveHourWeatherModel{" +
                "dateTime='" + dateTime + '\'' +
                ", epochDateTime=" + epochDateTime +
                ", weatherIcon=" + weatherIcon +
                ", iconPhrase='" + iconPhrase + '\'' +
                ", hasPrecipitation=" + hasPrecipitation +
                ", isDaylight=" + isDaylight +
                ", temperature=" + temperatureUnitModel +
                ", precipitationProbability=" + precipitationProbability +
                ", mobileLink='" + mobileLink + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
