package com.example.tfg_clienteapp.ui.architecture

import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante

data class OfertanteUiState(
    val listaMisActividades: List<ActividadOfertante> = arrayListOf(),
    val listaActividadesApuntado: List<ActividadConsumidor> = arrayListOf(),
    val listaActividadesAConsumidores: List<ActividadConsumidor> = arrayListOf(),
    val listaActividadesRecientes: List<ActividadConsumidor> = arrayListOf()

)
