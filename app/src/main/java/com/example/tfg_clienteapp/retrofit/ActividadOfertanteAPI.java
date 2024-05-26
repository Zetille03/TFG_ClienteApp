package com.example.tfg_clienteapp.retrofit;

import com.example.tfg_clienteapp.model.ActividadOfertante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ActividadOfertanteAPI {

    @GET("/actividad-ofertante/get-all")
    Call<List<ActividadOfertante>> getAllActividadesOfertantes();

    @POST("/actividad-ofertante/save")
    Call<ActividadOfertante> save(@Body ActividadOfertante actividadOfertante);

    @GET("/actividad-ofertante/get-by-ofertante")
    Call<List<ActividadOfertante>> getActividadesOfertanteByOfertante(@Query("ofertanteId") int ofertanteId);

    @GET("/actividad-ofertante/get-apuntado")
    Call<List<ActividadOfertante>> getActividadesOfertantesByConsumidor(@Query("consumidorId") int consumidorId);

    @DELETE("/actividad-ofertante/delete")
    Call<Void> deleteById(@Query("id")int id);

    @PUT("/actividad-ofertante/update")
    Call<ActividadOfertante> update(@Query("id") int id,@Body ActividadOfertante actividadOfertante);

}
