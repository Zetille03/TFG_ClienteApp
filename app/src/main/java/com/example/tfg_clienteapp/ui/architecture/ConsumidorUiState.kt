package com.example.tfg_clienteapp.ui.architecture

import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante

data class ConsumidorUiState(
    val listaMisActividades: List<ActividadConsumidor> = arrayListOf(),
    val listaActividadesApuntado: List<ActividadOfertante> = arrayListOf(),
    val listaActividadesOfertantes: List<ActividadOfertante> = arrayListOf(),
    val listaActividadesRecientes: List<ActividadOfertante> = arrayListOf()
)
