package com.anusha_peddina.WeatherApp.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.anusha_peddina.WeatherApp.Adapters.CitySearchLookupAdapter;
import com.anusha_peddina.WeatherApp.R;
import com.anusha_peddina.WeatherApp.services.ApiDataSource;
import com.anusha_peddina.WeatherApp.services.model.location_auto_complete.LocationModel;
import com.anusha_peddina.WeatherApp.services.model.day_weather.DayWeatherModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    ListView locationLookupView;
    CitySearchLookupAdapter citySearchLookupAdapter;
    List<LocationModel> locationModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cirty_search_view);
        bindViews();
    }

    private void bindViews() {
        EditText userInput = findViewById(R.id.user_input);
//        locationLookupView = findViewById(R.id.location_lookup_list);
//        locationLookupAdapter = new LocationLookupAdapter(this, R.layout.city_search_item, locationModelList);
//        locationLookupView.setAdapter(locationLookupAdapter);

        userInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 3)
                    fetchLocationsAutoComplete(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void fetchLocationsAutoComplete(String userInput) {
        Observable<List<LocationModel>> fetchAddressSuggestionObservable = ApiDataSource.getInstance().fetchLocationAutoComplete(userInput)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        fetchAddressSuggestionObservable.subscribeWith(new Observer<List<LocationModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<LocationModel> locationModelList) {
                fetch12HourWeatherForecast(Integer.parseInt(locationModelList.get(0).key));
                citySearchLookupAdapter.addAll(locationModelList);
                citySearchLookupAdapter.notifyDataSetChanged();
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
                for(DayWeatherModel dayWeatherModel : dayWeatherModelList) {
                    Log.d("main activity", dayWeatherModel.toString());
                }
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


}