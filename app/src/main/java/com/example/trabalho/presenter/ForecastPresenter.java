package com.example.trabalho.presenter;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.trabalho.models.Forecast;
import com.example.trabalho.models.LocationGeo;
import com.example.trabalho.models.Trip;
import com.example.trabalho.presenter.contracts.ActivityContract;
import com.example.trabalho.presenter.contracts.RequestForecastContract;
import com.example.trabalho.presenter.contracts.RequestLocationContract;
import com.example.trabalho.services.Location;
import com.example.trabalho.services.OpenWeather;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastPresenter implements RequestForecastContract.RequestForecastPresenter,
        ActivityContract.ActivityPresenter, RequestLocationContract.RequestLocationPresenter{

    private RequestForecastContract.RequestForecastView tripDetailsForecastView;
    private ActivityContract.ActivityView forecastDetailsView;
    private Trip trip;
    private List<Forecast> forecastArrayList = new ArrayList<>();
    private List<Forecast> forecastActual = new ArrayList<>();
    private List<Forecast> forecastDestiny = new ArrayList<>();
    private List<Forecast> forecastReturn = new ArrayList<>();
    private LocationGeo locationGeo;

    public ForecastPresenter(RequestForecastContract.RequestForecastView viewForecast,
                                ActivityContract.ActivityView view,
                                Activity activity,
                                Trip trip) {
        this.forecastDetailsView = view;
        this.tripDetailsForecastView = viewForecast;
        this.trip = trip;

        Location location = new Location(activity, this); // aqui é onde pega a última localização do dispositivo
        location.getLastLocation(); //retorno dos dados de última localização
    }


    @Override
    public void start() {
        RequestForecastContract.RequestForecastPresenter forecastPresenter = this;
        OpenWeather openWeatherActual = new OpenWeather(forecastPresenter, this.forecastDetailsView.getContext(), "Actual");
        openWeatherActual.forecastActualPromise(locationGeo.getLatitude(), locationGeo.getLongitude(), new RequestForecastContract.VolleyCallBack() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess() {

            }
        });
    }

    @Override
    public void sendErrorForecast(String errorMessage) {

    }

    @Override
    public void getForecast(List<Forecast> forecastArrayList, String type) {

    }

    @Override
    public Forecast findForecast(Date date, String type) throws Exception {
        return null;
    }

    @Override
    public void getLocation(LocationGeo locationGeo) {

    }
}
