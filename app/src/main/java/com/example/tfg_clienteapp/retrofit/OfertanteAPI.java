package com.example.tfg_clienteapp.retrofit;

import com.example.tfg_clienteapp.model.Ofertante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface OfertanteAPI {
    @GET("/ofertante/get-all")
    Call<List<Ofertante>> getAllOfertantes();

    @GET("/ofertante/comprobar-login")
        Call<Ofertante> comprobarLogIn(@Query("user") String usuario, @Query("password") String contraseña);

    @POST("ofertante/save")
    Call<Ofertante> save(@Body Ofertante ofertante);

    @DELETE("/ofertante/delete")
    Call<Void> deleteById(@Query("id") int id);

    @PUT("/ofertante/update")
    Call<Ofertante> update(@Query("id") int id,@Body Ofertante ofertante);
}
