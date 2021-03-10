package com.anusha_peddina.WeatherApp.listeners;

import com.anusha_peddina.WeatherApp.services.model.location_auto_complete.LocationModel;

public interface CitySearchClickListener {
    void onItemClick(LocationModel item, int position);
}