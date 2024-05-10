package com.example.tfg_clienteapp.retrofit;

import com.example.tfg_clienteapp.model.Consumidor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ConsumidorAPI {

    @GET("/consumidor/get-all")
    Call<List<Consumidor>> getAllConsumidores();

    @POST("/consumidor/save")
    Call<Consumidor> save(@Body Consumidor consumidor);
}
