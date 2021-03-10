package com.anusha_peddina.WeatherApp.services.model;

import com.anusha_peddina.WeatherApp.services.model.current_conditions.CurrentConditionModel;

public class HomeScreenModel extends CurrentConditionModel {
    public String cityName;
    public int locationKey;
    public boolean isF;
    public String countryName;

    public void setCurrentConditions(CurrentConditionModel currentConditionModel) {
        this.epochTime = currentConditionModel.epochTime;
        this.hasPrecipitation = currentConditionModel.hasPrecipitation;
        this.localObservationDateTime = currentConditionModel.localObservationDateTime;
        this.weatherText = currentConditionModel.weatherText;
        this.weatherIcon = currentConditionModel.weatherIcon;
        this.precipitationType = currentConditionModel.precipitationType;
        this.isDayTime = currentConditionModel.isDayTime;
        this.temperature = currentConditionModel.temperature;
        this.mobileLink = currentConditionModel.mobileLink;
        this.link = currentConditionModel.link;
    }
}
