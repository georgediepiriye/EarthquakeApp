package com.example.earthquakeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {
    public static final String LOCATION_SEPARATOR = " of ";
    private final OnItemClickListener listener;
    private ArrayList<Earthquake> mEarthquakeList;


    public EarthquakeAdapter(ArrayList<Earthquake> earthquakeList, OnItemClickListener listener) {
        mEarthquakeList = earthquakeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new EarthquakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EarthquakeViewHolder holder, int position) {
        holder.bind(mEarthquakeList.get(position), listener);


        Earthquake currentItem = mEarthquakeList.get(position);


        double result = currentItem.getMagnitude();
        String magnitude = formatMagnitude(result);

        holder.magnitudeTextView.setText(magnitude);


        String initialLocation = currentItem.getPlace();
        String locationOffset;
        String place;
        if (initialLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = initialLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            place = parts[1];
        } else {
            locationOffset = "Near the";
            place = initialLocation;
        }


        holder.placeTextView.setText(place);
        holder.locationOffsetTextView.setText(locationOffset);

        long timeInMilliseconds = currentItem.getTimeInMilliseconds();
        Date dateObject = new Date(timeInMilliseconds);
        String date = formatDate(dateObject);
        holder.dateTextView.setText(date);

        String time = formatTime(dateObject);
        holder.timeTextView.setText(time);


    }


    @Override
    public int getItemCount() {
        return mEarthquakeList.size();
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.getDefault());
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


    public interface OnItemClickListener {
        void onItemClick(Earthquake earthquake);
    }

    public static class EarthquakeViewHolder extends RecyclerView.ViewHolder {
        public TextView magnitudeTextView;
        public TextView placeTextView;
        public TextView dateTextView;
        public TextView timeTextView;
        private TextView locationOffsetTextView;

        public EarthquakeViewHolder(View itemView) {
            super(itemView);
            magnitudeTextView = itemView.findViewById(R.id.magnitude_text_view);
            placeTextView = itemView.findViewById(R.id.place_text_view);
            dateTextView = itemView.findViewById(R.id.date_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
            locationOffsetTextView = itemView.findViewById(R.id.location_off_text_view);


        }


        public void bind(final Earthquake earthquake, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(earthquake);
                }
            });
        }
    }


}