package com.anusha_peddina.WeatherApp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anusha_peddina.WeatherApp.Adapters.DayWeatherAdapter;
import com.anusha_peddina.WeatherApp.R;
import com.anusha_peddina.WeatherApp.services.ApiDataSource;
import com.anusha_peddina.WeatherApp.services.model.HomeScreenModel;
import com.anusha_peddina.WeatherApp.services.model.day_weather.DayWeatherModel;

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

public class DayWeatherActivity extends AppCompatActivity {

    DayWeatherAdapter dayWeatherAdapter;
    List<DayWeatherModel> dayWeatherModelList = new ArrayList<>();
    HomeScreenModel homeScreenModel;

    @BindView(R.id.day_forecast_recycler_view) RecyclerView dayForecastRecyclerView;
    @BindView(R.id.day_weather_city_name) TextView dayWeatherCityName;
    @BindView(R.id.day_weather_country_name) TextView dayWeatherCountryName;
    @BindView(R.id.forecast_button) Button forecastButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_forecast_view);
        ButterKnife.bind(this);
        //get home Model
        homeScreenModel = (HomeScreenModel) getIntent().getSerializableExtra("home_model");;
        //city details
        dayWeatherCityName.setText(homeScreenModel.cityName);
        dayWeatherCountryName.setText(homeScreenModel.countryName);
        // set up the RecyclerView
        dayForecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dayForecastRecyclerView.addItemDecoration(new DividerItemDecoration(dayForecastRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        dayWeatherAdapter = new DayWeatherAdapter(this, dayWeatherModelList, homeScreenModel.isF);
        dayForecastRecyclerView.setAdapter(dayWeatherAdapter);
        fetch12HourWeatherForecast(homeScreenModel.locationKey);
    }

    @OnClick(R.id.forecast_button)
    public void onForecastButtonClick() {
        Intent intent = new Intent(this, WeekWeatherActivity.class);
        intent.putExtra("home_model", homeScreenModel);
        startActivity(intent);
    }

    @OnClick(R.id.day_back_button)
    public void onBackButtonClick() {
        finish();
    }

    public void fetch12HourWeatherForecast(int locationKey) {
        Observable<List<DayWeatherModel>> twelveHourWeatherModelObservable = ApiDataSource.getInstance().fetchDayWeatherForecast(locationKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        twelveHourWeatherModelObservable.subscribeWith(new Observer<List<DayWeatherModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<DayWeatherModel> dayWeatherModelList) {
                dayWeatherAdapter.addAll(dayWeatherModelList);
                dayWeatherAdapter.notifyDataSetChanged();
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
