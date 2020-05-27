package com.example.earthquakeapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Earthquake> earthquake = QueryUtils.extractEarthquakes();


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);


        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new EarthquakeAdapter(earthquake, new EarthquakeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Earthquake earthquake) {
                Uri earthquakeUri = Uri.parse(earthquake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);


            }

        }));
    }
}
