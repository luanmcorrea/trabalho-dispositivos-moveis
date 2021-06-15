package com.example.trabalho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.trabalho.adapters.ForecastAdapter;
import com.example.trabalho.databinding.ActivityTripDetailsBinding;
import com.example.trabalho.models.Forecast;
import com.example.trabalho.models.Trip;
import com.example.trabalho.presenter.contracts.ActivityContract;
import com.example.trabalho.presenter.contracts.RequestForecastContract;
import com.example.trabalho.presenter.TripDetailsPresenter;

import java.util.List;

public class TripDetailsActivity extends AppCompatActivity implements RequestForecastContract.RequestForecastView, ActivityContract.ActivityView {

    private RequestForecastContract.RequestForecastPresenter tripDetailsPresenter;
    private ActivityTripDetailsBinding tripDetailsBinding;
    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.tripDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_trip_details); //escolhe o layout

        Intent intent = getIntent(); // busca a intent
        this.trip = intent.getParcelableExtra("objTrip"); //traz a intent que veio da tela anterior
        this.tripDetailsBinding.setTrip(trip); // variavel trip da xml

        tripDetailsPresenter = new TripDetailsPresenter(this, this, this, trip); //aciona a presenter q vai fazer a merda acontecer
                                                                                                            //com os dados que vieram de antes
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
            Forecast forecastArrival = this.tripDetailsPresenter.findForecast(this.trip.getArrivalDate(), "Destiny");

            this.tripDetailsBinding.setForecastArrival(forecastArrival);
            this.tripDetailsBinding.setForecastDeparture(forecastDeparture); //faz o binding dos dados de partida e cria a variavel do xml
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

}