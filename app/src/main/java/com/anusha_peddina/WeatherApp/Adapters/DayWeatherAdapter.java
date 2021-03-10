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
import com.anusha_peddina.WeatherApp.services.ApiConstants;
import com.anusha_peddina.WeatherApp.services.model.day_weather.DayWeatherModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayWeatherAdapter extends RecyclerView.Adapter<DayWeatherAdapter.ViewHolder> {

    private List<DayWeatherModel> dayWeatherModelList = new ArrayList<>();
    private LayoutInflater inflater;
    private boolean isF;
    private Context context;

    public DayWeatherAdapter(@NonNull Context context, List<DayWeatherModel> locationModelList, boolean isF) {
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.dayWeatherModelList = locationModelList;
        this.isF = isF;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.day_forcast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DayWeatherModel dayWeatherModel = dayWeatherModelList.get(position);
        holder.dayTimeTextView.setText(Utils.getFormattedHours(dayWeatherModel.dateTime));
        holder.weatherIcon.setImageResource(Utils.getWeatherIcon(dayWeatherModel.weatherIcon));
        holder.weatherDescription.setText(dayWeatherModel.iconPhrase);
        if(isF) {
            if(dayWeatherModel.temperatureUnitModel.unit.equalsIgnoreCase("f") ) {
                holder.currentTemperature.setText(String.valueOf((int) Math.round(dayWeatherModel.temperatureUnitModel.value))+ ApiConstants.FAHRENHEIT);
            } else {
                holder.currentTemperature.setText(String.valueOf((int) Math.round(Utils.centigradeToFahrenheit(dayWeatherModel.temperatureUnitModel.value)))+ ApiConstants.FAHRENHEIT);
            }
        } else {
            if(dayWeatherModel.temperatureUnitModel.unit.equalsIgnoreCase("f") ) {
                holder.currentTemperature.setText(String.valueOf((int) Math.round(Utils.fahrenheitToCentigrade(dayWeatherModel.temperatureUnitModel.value)))+ ApiConstants.CENTIGRADE);
            } else {
                holder.currentTemperature.setText(String.valueOf((int) Math.round(dayWeatherModel.temperatureUnitModel.value))+ ApiConstants.CENTIGRADE);
            }
        }
        Glide.with(context)
                .asGif()
                .load(Utils.getWeatherGif(dayWeatherModel.weatherIcon))
                .centerCrop()
                .into(holder.imageView);
        holder.dayTimeTextView.setTextColor(context.getResources().getColor(Utils.getFontColor(dayWeatherModel.weatherIcon)));
        holder.weatherDescription.setTextColor(context.getResources().getColor(Utils.getFontColor(dayWeatherModel.weatherIcon)));
        holder.currentTemperature.setTextColor(context.getResources().getColor(Utils.getFontColor(dayWeatherModel.weatherIcon)));
    }

    @Override
    public int getItemCount() {
        return dayWeatherModelList.size();
    }

    // convenience method for getting data at click position
    public DayWeatherModel getItem(int id) {
        return dayWeatherModelList.get(id);
    }

    public void addAll(List<DayWeatherModel> dayWeatherModelList) {
        this.dayWeatherModelList.clear();
        this.dayWeatherModelList.addAll(dayWeatherModelList);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.day_layout) LinearLayout dayLayout;
        @BindView(R.id.day_time) TextView dayTimeTextView;
        @BindView(R.id.weather_icon) ImageView weatherIcon;
        @BindView(R.id.weather_description) TextView weatherDescription;
        @BindView(R.id.current_temperature) TextView currentTemperature;
        @BindView(R.id.day_gif_place) ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}