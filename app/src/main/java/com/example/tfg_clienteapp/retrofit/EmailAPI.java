package com.example.tfg_clienteapp.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EmailAPI {

    @GET("/send-email")
    Call<Void> sendMail(@Query("to") String to, @Query("subject") String subject,@Query("body") String body);
}
