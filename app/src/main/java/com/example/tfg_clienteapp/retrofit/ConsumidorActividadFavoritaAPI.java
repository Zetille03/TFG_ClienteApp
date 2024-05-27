package com.example.tfg_clienteapp.retrofit;

import com.example.tfg_clienteapp.model.ConsumidorActividadFavorita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ConsumidorActividadFavoritaAPI {

    @GET("/consumidor-actividad-favorita/get-all")
    Call<List<ConsumidorActividadFavorita>> getAllConsumidorActividadesFavoritas();

    @POST("/consumidor-actividad-favorita/save")
    Call<ConsumidorActividadFavorita> save(@Body ConsumidorActividadFavorita consumidorActividadFavorita);

    @PUT("/consumidor-actividad-favorita/update")
    Call<ConsumidorActividadFavorita> update(@Query("id") int id,@Body ConsumidorActividadFavorita consumidorActividadFavorita);

    @DELETE("/consumidor-actividad-favorita/delete")
    Call<Void> deleteById(@Query("id") int id);

    @DELETE("/consumidor-actividad-favorita/delete-by-ids")
    Call<Void> deleteByIds(@Query("idActividad") int idActividad,@Query("idConsumidor") int idConsumidor);
}
