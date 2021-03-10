package com.anusha_peddina.WeatherApp.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anusha_peddina.WeatherApp.Adapters.CitySearchLookupAdapter;
import com.anusha_peddina.WeatherApp.R;
import com.anusha_peddina.WeatherApp.Utils;
import com.anusha_peddina.WeatherApp.listeners.CitySearchClickListener;
import com.anusha_peddina.WeatherApp.services.ApiDataSource;
import com.anusha_peddina.WeatherApp.services.model.HomeScreenModel;
import com.anusha_peddina.WeatherApp.services.model.current_conditions.CurrentConditionModel;
import com.anusha_peddina.WeatherApp.services.model.location_auto_complete.LocationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CitySearchActivity extends AppCompatActivity implements CitySearchClickListener {
    CitySearchLookupAdapter cityLookUpAdapter;
    List<LocationModel> locationModelList = new ArrayList<>();


    @BindView(R.id.city_search_list_view) RecyclerView citySearchRecyclerView;
    @BindView(R.id.user_input) EditText userInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cirty_search_view);
        ButterKnife.bind(this);
        viewListeners();
    }

    private void viewListeners() {
        // set up the RecyclerView
        citySearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        citySearchRecyclerView.addItemDecoration(new DividerItemDecoration(citySearchRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        cityLookUpAdapter = new CitySearchLookupAdapter(this, locationModelList, this);
        citySearchRecyclerView.setAdapter(cityLookUpAdapter);
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
                cityLookUpAdapter.addAll(locationModelList);
                cityLookUpAdapter.notifyDataSetChanged();
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
    public void onItemClick(LocationModel locationModel, int position) {
        fetchCurrentConditions(locationModel);
    }

    public void fetchCurrentConditions(LocationModel locationModel) {
        Observable<List<CurrentConditionModel>> fetchCurrentConditionModel = ApiDataSource.getInstance().fetchCurrentConditions(Integer.parseInt(locationModel.key))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        fetchCurrentConditionModel.subscribeWith(new Observer<List<CurrentConditionModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<CurrentConditionModel> currentConditionModelList) {
                List<HomeScreenModel> oldHomeScreenModelList = Utils.getCurrentHomeScreenModelList(CitySearchActivity.this);
                List<HomeScreenModel> homeScreenModelList = new ArrayList<>();

                for(CurrentConditionModel currentConditionModel: currentConditionModelList) {
                    HomeScreenModel homeScreenModel = new HomeScreenModel();
                    homeScreenModel.locationKey = Integer.parseInt(locationModel.key);
                    homeScreenModel.cityName = locationModel.localizedName;
                    homeScreenModel.countryName = locationModel.country.localizedName;
                    homeScreenModel.setCurrentConditions(currentConditionModel);
                    //avoid duplicates
                    for(int i = 0;i<oldHomeScreenModelList.size();i++) {
                        if(oldHomeScreenModelList.get(i).locationKey == homeScreenModel.locationKey) {
                            oldHomeScreenModelList.remove(i);
                        }
                    }
                    //sync units
                    if(oldHomeScreenModelList != null && oldHomeScreenModelList.size() > 0) {
                        homeScreenModel.isF = oldHomeScreenModelList.get(0).isF;
                    }
                    homeScreenModelList.add(homeScreenModel);
                }

                for(HomeScreenModel homeScreenModel: oldHomeScreenModelList) {
                    homeScreenModelList.add(homeScreenModel);
                }

                Utils.saveHomeScreenModelList(CitySearchActivity.this, homeScreenModelList);
                finish();
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
