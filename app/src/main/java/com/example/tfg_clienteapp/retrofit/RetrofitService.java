package com.example.tfg_clienteapp.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;
    private String baseURL = "http://192.168.1.129:8080";

    public RetrofitService(){
        initializeRetrofit();
    }

    public void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
        return this.retrofit;
    }
}
