package com.example.tfg_clienteapp.retrofit;

import com.example.tfg_clienteapp.model.Consumidor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ConsumidorAPI {

    @GET("/consumidor/get-all")
    Call<List<Consumidor>> getAllConsumidores();

    @GET("/consumidor/comprobar-login")
    Call<Consumidor> comprobarLogIn(@Query("user") String usuario, @Query("password") String contraseña);

    @POST("/consumidor/save")
    Call<Consumidor> save(@Body Consumidor consumidor);

    @DELETE("/consumidor/delete")
    Call<Void> deleteById(@Query("id") int id);

    @PUT("/consumidor/update")
    Call<Consumidor> update(@Query("id") int id,@Body Consumidor consumidor);


}
