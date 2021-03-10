package com.anusha_peddina.WeatherApp.services;

import com.anusha_peddina.WeatherApp.services.model.city_search.CityModel;
import com.anusha_peddina.WeatherApp.services.model.current_conditions.CurrentConditionModel;
import com.anusha_peddina.WeatherApp.services.model.location_auto_complete.LocationModel;
import com.anusha_peddina.WeatherApp.services.model.day_weather.DayWeatherModel;
import com.anusha_peddina.WeatherApp.services.model.week_weather.WeekWeatherModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.anusha_peddina.WeatherApp.services.ApiConstants.BASE_URL;

public class ApiDataSource implements IDataSource {

    private static ApiDataSource instance = null;

    private static Retrofit retrofit;
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static OkHttpClient client;

    protected ApiDataSource() {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addNetworkInterceptor(new HeaderInterceptor());

        client = builder
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static ApiDataSource getInstance() {
        if (instance == null) {
            instance = new ApiDataSource();
        }

        return instance;
    }

    private static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .build();
            return chain.proceed(request);
        }
    }

    @Override
    public Observable<List<LocationModel>> fetchLocationAutoComplete(String userInput) {
        WeatherAPI service = retrofit.create(WeatherAPI.class);
        return service.fetchLocationAutoComplete(ApiConstants.WEATHER_API_KEY, userInput);
    }

    @Override
    public Observable<List<CityModel>> fetchCities(String userInput) {
        WeatherAPI service = retrofit.create(WeatherAPI.class);
        return service.fetchCities(ApiConstants.WEATHER_API_KEY, userInput);
    }

    @Override
    public Observable<List<CurrentConditionModel>> fetchCurrentConditions(int locationKey) {
        WeatherAPI service = retrofit.create(WeatherAPI.class);
        return service.fetchCurrentConditions(locationKey, ApiConstants.WEATHER_API_KEY);
    }

    @Override
    public Observable<List<DayWeatherModel>> fetchDayWeatherForecast(int locationKey) {
        WeatherAPI service = retrofit.create(WeatherAPI.class);
        return service.fetchDayWeatherForecast(locationKey, ApiConstants.WEATHER_API_KEY);
    }

    @Override
    public Observable<WeekWeatherModel> fetchWeekWeatherForecast(int locationKey) {
        WeatherAPI service = retrofit.create(WeatherAPI.class);
        return service.fetchWeekWeatherForecast(locationKey, ApiConstants.WEATHER_API_KEY);
    }
}
