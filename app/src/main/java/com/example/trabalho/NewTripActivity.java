package com.example.trabalho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.trabalho.databinding.ActivityNewTripBinding;
import com.example.trabalho.models.Trip;
import com.example.trabalho.presenter.NewTripPresenter;
import com.example.trabalho.presenter.contracts.ActivityContract;

public class NewTripActivity extends AppCompatActivity implements ActivityContract.ActivityView {

    private ActivityContract.ActivityFormPresenter newTripPresenter;
    private ActivityNewTripBinding newTripBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip); //chama activity

        newTripPresenter = new NewTripPresenter(this); //cria uma presenter

        newTripBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_trip); //seta a view
        newTripBinding.setPresenter((NewTripPresenter) newTripPresenter); //seta a presenter
        newTripBinding.setTrip(new Trip()); //seta as variaveis da xml

        ((NewTripPresenter) newTripPresenter).newTripBinding = newTripBinding; //presenter.activity ????
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigate(Intent intent) {
        startActivity(intent);
    }
}