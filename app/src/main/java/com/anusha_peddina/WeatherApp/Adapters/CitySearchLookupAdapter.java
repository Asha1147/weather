package com.anusha_peddina.WeatherApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anusha_peddina.WeatherApp.listeners.CitySearchClickListener;
import com.anusha_peddina.WeatherApp.R;
import com.anusha_peddina.WeatherApp.services.model.location_auto_complete.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class CitySearchLookupAdapter extends RecyclerView.Adapter<CitySearchLookupAdapter.ViewHolder> {

    private List<LocationModel> locationModelList = new ArrayList<>();
    private LayoutInflater inflater;
    private CitySearchClickListener citySearchClickListener;

    public CitySearchLookupAdapter(@NonNull Context context, List<LocationModel> locationModelList, CitySearchClickListener citySearchClickListener) {
        inflater =LayoutInflater.from(context);
        this.locationModelList = locationModelList;
        this.citySearchClickListener = citySearchClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.city_search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocationModel locationModel = locationModelList.get(position);
        holder.cityName.setText(locationModel.localizedName+", "+locationModel.adminArea.id+", "+locationModel.country.localizedName);
        holder.viewHolderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                citySearchClickListener.onItemClick(locationModel,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationModelList.size();
    }

    // convenience method for getting data at click position
    public LocationModel getItem(int id) {
        return locationModelList.get(id);
    }

    public void addAll(List<LocationModel> locationModelList) {
        this.locationModelList.clear();
        this.locationModelList.addAll(locationModelList);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        LinearLayout viewHolderLayout;
        public ViewHolder(View view) {
            super(view);
            cityName = view.findViewById(R.id.city_name_search);
            viewHolderLayout = view.findViewById(R.id.city_search_item_layout);
        }
    }
}