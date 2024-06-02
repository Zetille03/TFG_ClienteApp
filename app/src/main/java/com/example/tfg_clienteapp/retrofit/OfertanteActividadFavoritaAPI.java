package com.example.tfg_clienteapp.retrofit;

import com.example.tfg_clienteapp.model.OfertanteActividadFavorita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface OfertanteActividadFavoritaAPI {
    @GET("/ofertante-actividad-favorita/get-all")
    Call<List<OfertanteActividadFavorita>> getAllOfertanteActividadesFavoritas();

    @POST("/ofertante-actividad-favorita/save")
    Call<OfertanteActividadFavorita> save(@Query("idActividad") int idActividad,@Query("idOfertante") int idOfertante);

    @DELETE("/ofertante-actividad-favorita/delete")
    Call<Void> deleteById(@Query("id") int id);

    @DELETE("/ofertante-actividad-favorita/delete-by-ids")
    Call<Void> deleteByIds(@Query("idActividad") int idActividad, @Query("idOfertante") int idofertante);

    @PUT("/ofertante-actividad-favorita/update")
    Call<OfertanteActividadFavorita> update(@Query("id") int id, @Body OfertanteActividadFavorita ofertanteActividadFavorita);



}
