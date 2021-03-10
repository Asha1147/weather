package com.anusha_peddina.WeatherApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anusha_peddina.WeatherApp.R;
import com.anusha_peddina.WeatherApp.Utils;
import com.anusha_peddina.WeatherApp.listeners.HomeItemClickListener;
import com.anusha_peddina.WeatherApp.services.ApiConstants;
import com.anusha_peddina.WeatherApp.services.model.HomeScreenModel;
import com.anusha_peddina.WeatherApp.services.model.current_conditions.CurrentConditionModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.ViewHolder> {

    private List<HomeScreenModel> homeScreenModelList = new ArrayList<>();
    private LayoutInflater inflater;
    private HomeItemClickListener homeItemClickListener;
    private Context context;

    public HomeScreenAdapter(@NonNull Context context, List<HomeScreenModel> homeScreenModelList) {
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.homeScreenModelList = homeScreenModelList;
        homeItemClickListener = (HomeItemClickListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_city_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeScreenModel homeScreenModel = homeScreenModelList.get(position);
        holder.homeCityName.setText(homeScreenModel.cityName);
        holder.homeScreenCountryName.setText(homeScreenModel.countryName);
        holder.weatherIcon.setImageResource(Utils.getWeatherIcon(homeScreenModel.weatherIcon));
        holder.weatherDescription.setText(homeScreenModel.weatherText);
        if(homeScreenModel.isF) {
            if(homeScreenModel.temperature.imperial.unit.equalsIgnoreCase("f") ) {
                holder.currentTemperature.setText(String.valueOf((int) Math.round(homeScreenModel.temperature.imperial.value))+ ApiConstants.FAHRENHEIT);
            } else {
                holder.currentTemperature.setText(String.valueOf((int) Math.round(Utils.centigradeToFahrenheit(homeScreenModel.temperature.imperial.value)))+ ApiConstants.FAHRENHEIT);
            }
        } else {
            if(homeScreenModel.temperature.imperial.unit.equalsIgnoreCase("f") ) {
                holder.currentTemperature.setText(String.valueOf((int) Math.round(Utils.fahrenheitToCentigrade(homeScreenModel.temperature.imperial.value)))+ ApiConstants.CENTIGRADE);
            } else {
                holder.currentTemperature.setText(String.valueOf((int) Math.round(homeScreenModel.temperature.imperial.value))+ ApiConstants.CENTIGRADE);
            }
        }
        holder.homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemClickListener.onItemClick(homeScreenModel, position);
            }
        });
        holder.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemClickListener.showLoading("refreshing "+homeScreenModel.cityName+" Information");
                homeItemClickListener.onRefreshClickListener(homeScreenModel, position);
            }
        });
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemClickListener.showLoading("Removing "+homeScreenModel.cityName+" Information");
                homeItemClickListener.onDeleteClickListener(homeScreenModel, position);
            }
        });
        Glide.with(context)
                .asGif()
                .load(Utils.getWeatherGif(homeScreenModel.weatherIcon))
                .centerCrop()
                .into(holder.imageView);
        holder.homeCityName.setTextColor(context.getResources().getColor(Utils.getFontColor(homeScreenModel.weatherIcon)));
        holder.homeScreenCountryName.setTextColor(context.getResources().getColor(Utils.getFontColor(homeScreenModel.weatherIcon)));
        holder.weatherDescription.setTextColor(context.getResources().getColor(Utils.getFontColor(homeScreenModel.weatherIcon)));
        holder.currentTemperature.setTextColor(context.getResources().getColor(Utils.getFontColor(homeScreenModel.weatherIcon)));
    }

    @Override
    public int getItemCount() {
        return homeScreenModelList.size();
    }

    // convenience method for getting data at click position
    public CurrentConditionModel getItem(int id) {
        return homeScreenModelList.get(id);
    }

    public void addAll(List<HomeScreenModel> homeScreenModelList) {
        this.homeScreenModelList.clear();
        this.homeScreenModelList.addAll(homeScreenModelList);
        this.notifyDataSetChanged();
        homeItemClickListener.hideLoading();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_city_name) TextView homeCityName;
        @BindView(R.id.country_name) TextView homeScreenCountryName;
        @BindView(R.id.weather_icon) ImageView weatherIcon;
        @BindView(R.id.remove_button) ImageView removeButton;
        @BindView(R.id.refresh_button) ImageView refreshButton;
        @BindView(R.id.weather_description) TextView weatherDescription;
        @BindView(R.id.current_temperature) TextView currentTemperature;
        @BindView(R.id.home_item_layout) LinearLayout homeLayout;
        @BindView(R.id.gif_place) ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}