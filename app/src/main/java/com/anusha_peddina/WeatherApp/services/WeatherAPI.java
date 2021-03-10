package com.anusha_peddina.WeatherApp.services;
import com.anusha_peddina.WeatherApp.services.model.city_search.CityModel;
import com.anusha_peddina.WeatherApp.services.model.current_conditions.CurrentConditionModel;
import com.anusha_peddina.WeatherApp.services.model.location_auto_complete.LocationModel;
import com.anusha_peddina.WeatherApp.services.model.day_weather.DayWeatherModel;
import com.anusha_peddina.WeatherApp.services.model.week_weather.WeekWeatherModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("/locations/v1/cities/autocomplete")
    Observable<List<LocationModel>> fetchLocationAutoComplete(@Query("apikey") String apiKey,
                                                              @Query("q") String userInput);

    @GET("/locations/v1/cities/search")
    Observable<List<CityModel>> fetchCities(@Query("apikey") String apiKey,
                                            @Query("q") String userInput);

    @GET("/currentconditions/v1/{locationKey}")
    Observable<List<CurrentConditionModel>> fetchCurrentConditions(@Path("locationKey") int locationKey,
                                                                   @Query("apikey") String apiKey);


    @GET("/forecasts/v1/hourly/12hour/{locationKey}")
    Observable<List<DayWeatherModel>> fetchDayWeatherForecast(@Path("locationKey") int locationKey,
                                                              @Query("apikey") String apiKey);

    @GET("/forecasts/v1/daily/5day/{locationKey}")
    Observable<WeekWeatherModel> fetchWeekWeatherForecast(@Path("locationKey") int locationKey,
                                                          @Query("apikey") String apiKey);
}
