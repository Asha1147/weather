package com.anusha_peddina.WeatherApp.services;

import com.anusha_peddina.WeatherApp.services.model.city_search.CityModel;
import com.anusha_peddina.WeatherApp.services.model.current_conditions.CurrentConditionModel;
import com.anusha_peddina.WeatherApp.services.model.location_auto_complete.LocationModel;
import com.anusha_peddina.WeatherApp.services.model.day_weather.DayWeatherModel;
import com.anusha_peddina.WeatherApp.services.model.week_weather.WeekWeatherModel;

import java.util.List;

import io.reactivex.Observable;

public interface IDataSource {
    Observable<List<LocationModel>> fetchLocationAutoComplete(String userInput);
    Observable<List<CityModel>> fetchCities(String userInput);
    Observable<List<CurrentConditionModel>> fetchCurrentConditions(int locationKey);
    Observable<List<DayWeatherModel>> fetchDayWeatherForecast(int locationKey);
    Observable<WeekWeatherModel> fetchWeekWeatherForecast(int locationKey);
}
