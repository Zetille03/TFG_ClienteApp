package com.example.tfg_clienteapp.ui.architecture

import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante

data class ConsumidorUiState(
    val nombreConsumidor: String = "",
    val listaMisActividades: ArrayList<ActividadConsumidor> = arrayListOf(),
    val listaActividadesApuntado: ArrayList<ActividadOfertante> = arrayListOf()
)
