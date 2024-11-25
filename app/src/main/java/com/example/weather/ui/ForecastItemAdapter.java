package com.example.weather.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;

import java.util.List;

public class ForecastItemAdapter extends RecyclerView.Adapter<ForecastItemAdapter.ForecastViewHolder> {

    private List<ForecastItem> forecastList;
    private Context context;

    public ForecastItemAdapter(Context context, List<ForecastItem> forecastList) {
        this.context = context;
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        ForecastItem forecastItem = forecastList.get(position);

        // Set forecast data to views

        String iconName = "icon_" + forecastItem.icon;
        int resourceId = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
        if (resourceId != 0) {
            holder.img_view.setImageResource(resourceId);
        } else {
            holder.img_view.setImageResource(R.drawable.icon_01d);
        }
        holder.txt_dt_txt.setText(String.format("%s", forecastItem.dt_txt));
        holder.txt_temp.setText(String.format("Temp: %.1f°C", forecastItem.temp));
        holder.txt_description.setText(String.format("Weather: %s", forecastItem.description));


    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        ImageView img_view;
        TextView txt_dt_txt;
        TextView txt_temp;
        TextView txt_description;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            img_view = itemView.findViewById(R.id.img_view);
            txt_dt_txt = itemView.findViewById(R.id.txt_dt_txt);
            txt_temp = itemView.findViewById(R.id.txt_temp);
            txt_description = itemView.findViewById(R.id.txt_description);
        }
    }
}

