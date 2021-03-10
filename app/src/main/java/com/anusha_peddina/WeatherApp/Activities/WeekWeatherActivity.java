package com.anusha_peddina.WeatherApp.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anusha_peddina.WeatherApp.Adapters.WeekWeatherAdapter;
import com.anusha_peddina.WeatherApp.R;
import com.anusha_peddina.WeatherApp.services.ApiDataSource;
import com.anusha_peddina.WeatherApp.services.model.HomeScreenModel;
import com.anusha_peddina.WeatherApp.services.model.week_weather.DailyForecast;
import com.anusha_peddina.WeatherApp.services.model.week_weather.WeekWeatherModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeekWeatherActivity extends AppCompatActivity {
    WeekWeatherAdapter weekWeatherAdapter;
    List<DailyForecast> dailyForecastList = new ArrayList<>();
    HomeScreenModel homeScreenModel;

    @BindView(R.id.week_forecast_recycler_view) RecyclerView weekForecastRecyclerView;
    @BindView(R.id.week_weather_city_name) TextView weekWeatherCityName;
    @BindView(R.id.week_weather_country_name) TextView weekWeatherCountryName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_forecast_view);
        ButterKnife.bind(this);
        //get home Model
        homeScreenModel = (HomeScreenModel) getIntent().getSerializableExtra("home_model");;
        //city details
        weekWeatherCityName.setText(homeScreenModel.cityName);
        weekWeatherCountryName.setText(homeScreenModel.countryName);
        // set up the RecyclerView
        weekForecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        weekForecastRecyclerView.addItemDecoration(new DividerItemDecoration(weekForecastRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        weekWeatherAdapter = new WeekWeatherAdapter(this, dailyForecastList, homeScreenModel.isF);
        weekForecastRecyclerView.setAdapter(weekWeatherAdapter);
        fetchWeeklyWeatherForecast(homeScreenModel.locationKey);
    }

    @OnClick(R.id.week_back_button)
    public void onBackButtonClick() {
        finish();
    }

    public void fetchWeeklyWeatherForecast(int locationKey) {
        Observable<WeekWeatherModel> twelveHourWeatherModelObservable = ApiDataSource.getInstance().fetchWeekWeatherForecast(locationKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        twelveHourWeatherModelObservable.subscribeWith(new Observer<WeekWeatherModel>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(WeekWeatherModel weekWeatherModel) {
                weekWeatherAdapter.addAll(weekWeatherModel.dailyForecasts);
                weekWeatherAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
