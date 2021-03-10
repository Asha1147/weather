package com.anusha_peddina.WeatherApp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anusha_peddina.WeatherApp.Adapters.HomeScreenAdapter;
import com.anusha_peddina.WeatherApp.R;
import com.anusha_peddina.WeatherApp.Utils;
import com.anusha_peddina.WeatherApp.listeners.HomeItemClickListener;
import com.anusha_peddina.WeatherApp.services.ApiDataSource;
import com.anusha_peddina.WeatherApp.services.model.HomeScreenModel;
import com.anusha_peddina.WeatherApp.services.model.current_conditions.CurrentConditionModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity implements HomeItemClickListener {

    HomeScreenAdapter homeScreenAdapter;

    @BindView(R.id.home_screen_city_recycler_view) RecyclerView homeScreenCityRecyclerView;
    @BindView(R.id.search_button) Button searchButton;
    @BindView(R.id.c_button) Button cButton;
    @BindView(R.id.f_button) Button fButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        ButterKnife.bind(this);
        // set up the RecyclerView
        homeScreenCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeScreenCityRecyclerView.addItemDecoration(new DividerItemDecoration(homeScreenCityRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        homeScreenAdapter = new HomeScreenAdapter(this, Utils.getCurrentHomeScreenModelList(this));
        homeScreenCityRecyclerView.setAdapter(homeScreenAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList(Utils.getCurrentHomeScreenModelList(this));
    }

    private void updateList(List<HomeScreenModel> currentHomeScreenModelList) {
        homeScreenAdapter.addAll(currentHomeScreenModelList);
        homeScreenAdapter.notifyDataSetChanged();
        if(currentHomeScreenModelList != null && currentHomeScreenModelList.size() > 0) {
            if(currentHomeScreenModelList.get(0).isF) {
                enableFButton();
            } else {
                enableCButton();
            }
        }
        Utils.saveHomeScreenModelList(this, currentHomeScreenModelList);
    }

    @OnClick(R.id.search_button)
    public void onSearchButtonClick() {
        Intent intent = new Intent(this, CitySearchActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.c_button)
    public void onCButtonClick() {
        enableCButton();
        List<HomeScreenModel> homeScreenModelList = Utils.getCurrentHomeScreenModelList(this);
        for(HomeScreenModel homeScreenModel : homeScreenModelList) {
            homeScreenModel.isF = false;
        }
        updateList(homeScreenModelList);
    }

    @OnClick(R.id.f_button)
    public void onFButtonClick() {
        enableFButton();
        List<HomeScreenModel> homeScreenModelList = Utils.getCurrentHomeScreenModelList(this);
        for(HomeScreenModel homeScreenModel : homeScreenModelList) {
            homeScreenModel.isF = true;
        }
        updateList(homeScreenModelList);
    }

    void enableFButton() {
        fButton.setAlpha(1f);
        cButton.setAlpha(.2f);
    }

    void enableCButton() {
        cButton.setAlpha(1f);
        fButton.setAlpha(.2f);
    }

    @Override
    public void onItemClick(HomeScreenModel homeScreenModel, int position) {
        Intent intent = new Intent(this, DayWeatherActivity.class);
        intent.putExtra("home_model", homeScreenModel);
        startActivity(intent);
    }

    @Override
    public void onRefreshClickListener(HomeScreenModel homeScreenModel, int position) {
        fetchCurrentConditions(homeScreenModel.locationKey);
    }

    @Override
    public void onDeleteClickListener(HomeScreenModel homeScreenModel, int position) {

        List<HomeScreenModel> oldHomeScreenModelList = Utils.getCurrentHomeScreenModelList(HomeActivity.this);
        for(int i = 0;i<oldHomeScreenModelList.size();i++) {
            if(oldHomeScreenModelList.get(i).locationKey == homeScreenModel.locationKey) {
                oldHomeScreenModelList.remove(i);
            }
        }
        updateList(oldHomeScreenModelList);
    }

    public void fetchCurrentConditions(int locationKey) {
        Observable<List<CurrentConditionModel>> fetchCurrentConditionModel = ApiDataSource.getInstance().fetchCurrentConditions(locationKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        fetchCurrentConditionModel.subscribeWith(new Observer<List<CurrentConditionModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<CurrentConditionModel> currentConditionModelList) {
                List<HomeScreenModel> oldHomeScreenModelList = Utils.getCurrentHomeScreenModelList(HomeActivity.this);
                for(HomeScreenModel homeScreenModel: oldHomeScreenModelList) {
                    if(homeScreenModel.locationKey == locationKey) {
                        homeScreenModel.setCurrentConditions(currentConditionModelList.get(0));
                    }
                }
                updateList(oldHomeScreenModelList);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                hideLoading();
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void showLoading(String message) {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(message);
            progressDialog.show();
        } else {
            progressDialog.setMessage(message);
        }
    }

    @Override
    public void hideLoading() {
        if(progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }
}