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
import com.anusha_peddina.WeatherApp.services.model.week_weather.DailyForecast;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeekWeatherAdapter extends RecyclerView.Adapter<WeekWeatherAdapter.ViewHolder> {

    private List<DailyForecast> dailyForecastList = new ArrayList<>();
    private LayoutInflater inflater;
    boolean isF;
    private Context context;

    public WeekWeatherAdapter(@NonNull Context context, List<DailyForecast> dayWeatherModelList, boolean isF) {
        this.context = context;
        inflater =LayoutInflater.from(context);
        this.dailyForecastList = dayWeatherModelList;
        this.isF = isF;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.week_forecast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyForecast dailyForecast = dailyForecastList.get(position);
        holder.dateTextView.setText(Utils.getFormattedDate(dailyForecast.date));
        holder.weatherIcon.setImageResource(Utils.getWeatherIcon(dailyForecast.day.icon));
        holder.weatherDescription.setText(dailyForecast.day.iconPhrase);
        if(isF) {
            if(dailyForecast.temperature.maximum.unit.equalsIgnoreCase("f") ) {
                holder.maximumTemperature.setText(String.valueOf((int) Math.round(dailyForecast.temperature.maximum.value))+ ApiConstants.FAHRENHEIT);
                holder.minimumTemperature.setText(String.valueOf((int) Math.round(dailyForecast.temperature.minimum.value))+ ApiConstants.FAHRENHEIT);
            } else {
                holder.maximumTemperature.setText(String.valueOf((int) Math.round(Utils.centigradeToFahrenheit(dailyForecast.temperature.maximum.value))+ ApiConstants.FAHRENHEIT));
                holder.minimumTemperature.setText(String.valueOf((int) Math.round(Utils.centigradeToFahrenheit(dailyForecast.temperature.minimum.value))+ ApiConstants.FAHRENHEIT));
            }
        } else {
            if(dailyForecast.temperature.maximum.unit.equalsIgnoreCase("f") ) {
                holder.maximumTemperature.setText(String.valueOf((int) Math.round(Utils.fahrenheitToCentigrade(dailyForecast.temperature.maximum.value)))+ ApiConstants.CENTIGRADE);
                holder.minimumTemperature.setText(String.valueOf((int) Math.round(Utils.fahrenheitToCentigrade(dailyForecast.temperature.minimum.value)))+ ApiConstants.CENTIGRADE);
            } else {
                holder.maximumTemperature.setText(String.valueOf((int) Math.round(dailyForecast.temperature.maximum.value))+ ApiConstants.CENTIGRADE);
                holder.minimumTemperature.setText(String.valueOf((int) Math.round(dailyForecast.temperature.maximum.value))+ ApiConstants.CENTIGRADE);
            }
        }

        Glide.with(context)
                .asGif()
                .load(Utils.getWeatherGif(dailyForecast.day.icon))
                .centerCrop()
                .into(holder.imageView);
        holder.dateTextView.setTextColor(context.getResources().getColor(Utils.getFontColor(dailyForecast.day.icon)));
        holder.weatherDescription.setTextColor(context.getResources().getColor(Utils.getFontColor(dailyForecast.day.icon)));
        holder.maximumTemperature.setTextColor(context.getResources().getColor(Utils.getFontColor(dailyForecast.day.icon)));
        holder.minimumTemperature.setTextColor(context.getResources().getColor(Utils.getFontColor(dailyForecast.day.icon)));
    }

    @Override
    public int getItemCount() {
        return dailyForecastList.size();
    }

    // convenience method for getting data at click position
    public DailyForecast getItem(int id) {
        return dailyForecastList.get(id);
    }

    public void addAll(List<DailyForecast> dailyForecastList) {
        this.dailyForecastList.clear();
        this.dailyForecastList.addAll(dailyForecastList);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date_text_view) TextView dateTextView;
        @BindView(R.id.weather_icon) ImageView weatherIcon;
        @BindView(R.id.week_gif_place) ImageView imageView;
        @BindView(R.id.weather_description) TextView weatherDescription;
        @BindView(R.id.max_temp) TextView maximumTemperature;
        @BindView(R.id.min_temp) TextView minimumTemperature;
        @BindView(R.id.week_layout) LinearLayout weekLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}