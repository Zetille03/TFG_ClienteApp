package com.example.tfg_clienteapp.retrofit;

import com.example.tfg_clienteapp.model.ConsumidorActividadOfertante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ConsumidorActividadOfertanteAPI {

    @GET("/consumidor-actividad-ofertante/get-all")
    Call<List<ConsumidorActividadOfertante>> getAllConsumidoresActividadesOfertantes();

    @POST("/consumidor-actividad-ofertante/save")
    Call<ConsumidorActividadOfertante> save(@Query("idActividad") int idActividad, @Query("idConsumidor") int consumidor);

    @DELETE("/consumidor-actividad-ofertante/delete")
    Call<Void> deleteById(@Query("id") int id);

    @DELETE("/consumidor-actividad-ofertante/delete-by-ids")
    Call<ConsumidorActividadOfertante> deleteByIds(@Query("idActividad") int idActividad,@Query("idConsumidor") int idConsumidor);

    @PUT("/consumidor-actividad-ofertante/update")
    Call<ConsumidorActividadOfertante> update(@Query("id") int id, @Body ConsumidorActividadOfertante consumidorActividadOfertante);
}
