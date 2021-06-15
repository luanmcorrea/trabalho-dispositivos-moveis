package com.example.trabalho;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho.adapters.ForecastAdapter;
import com.example.trabalho.adapters.MyRecyclerViewAdapter;
import com.example.trabalho.databinding.ActivityTripDetailsBinding;
import com.example.trabalho.models.Forecast;
import com.example.trabalho.models.Trip;
import com.example.trabalho.presenter.TripDetailsPresenter;
import com.example.trabalho.presenter.contracts.ActivityContract;
import com.example.trabalho.presenter.contracts.RequestForecastContract;

import java.util.ArrayList;
import java.util.List;

public class ForecastActivity extends AppCompatActivity implements RequestForecastContract.RequestForecastView, ActivityContract.ActivityView {
    private RequestForecastContract.RequestForecastPresenter tripDetailsPresenter;
    private ActivityTripDetailsBinding tripDetailsBinding;
    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        this.tripDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_forecast);

        tripDetailsPresenter = new TripDetailsPresenter(this, this, this, trip);

//        // data to populate the RecyclerView with
//        ArrayList<Integer> viewColors = new ArrayList<>();
//        viewColors.add(Color.BLUE);
//        viewColors.add(Color.YELLOW);
//        viewColors.add(Color.MAGENTA);
//        viewColors.add(Color.RED);
//        viewColors.add(Color.BLACK);
//
//        ArrayList<String> animalNames = new ArrayList<>();
//        animalNames.add("Horse");
//        animalNames.add("Cow");
//        animalNames.add("Camel");
//        animalNames.add("Sheep");
//        animalNames.add("Goat");

//        // set up the RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.forecast_rv_today);
//        LinearLayoutManager horizontalLayoutManager
//                = new LinearLayoutManager(ForecastActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(horizontalLayoutManager);
//        adapter = new MyRecyclerViewAdapter(this, animalNames);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void bindList(List<Forecast> forecastArrayList) {
        try {
            RecyclerView recyclerView = findViewById(R.id.recyclerViewForecast); //aponta onde vai salvar os dados
            LinearLayoutManager linearLayoutManagerHorinzontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
            recyclerView.setLayoutManager(linearLayoutManagerHorinzontal);

            ForecastAdapter forecastAdapter = new ForecastAdapter(forecastArrayList);
            recyclerView.setAdapter(forecastAdapter); // seta o adapter do recyclerView

            Forecast forecastDeparture = this.tripDetailsPresenter.findForecast(this.trip.getDepartureDate(), "Actual");

            this.tripDetailsBinding.setForecastDeparture(forecastDeparture); //faz o binding dos dados de partida e cria a variavel do xml
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void navigate(Intent intent) {

    }

}