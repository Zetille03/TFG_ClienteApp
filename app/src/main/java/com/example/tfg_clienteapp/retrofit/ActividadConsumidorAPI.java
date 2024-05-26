package com.example.tfg_clienteapp.retrofit;

import com.example.tfg_clienteapp.model.ActividadConsumidor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ActividadConsumidorAPI {

    @GET("/actividad-consumidor/get-all")
    Call<List<ActividadConsumidor>> getAllActividadesConsumidores();

    @POST("/actividad-consumidor/save")
    Call<ActividadConsumidor> save(@Body ActividadConsumidor actividadConsumidor);

    @GET("/actividad-consumidor/get-by-consumidor")
    Call<List<ActividadConsumidor>> getActividadesConsumidoresByConsumidor(@Query("consumidorId") int id);

    @DELETE("/actividad-consumidor/delete")
    Call<Void> deleteById(@Query("id") int id);

    @PUT("/actividad-consumidor/update")
    Call<ActividadConsumidor> update(@Query("id")int id,@Body ActividadConsumidor actividadOfertante);
}
