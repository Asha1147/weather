package com.anusha_peddina.WeatherApp.listeners;

import com.anusha_peddina.WeatherApp.services.model.HomeScreenModel;

public interface HomeItemClickListener {
    void onItemClick(HomeScreenModel homeScreenModel, int position);
    void onRefreshClickListener(HomeScreenModel homeScreenModel, int position);
    void onDeleteClickListener(HomeScreenModel homeScreenModel, int position);
    void showLoading(String message);
    void hideLoading();
}