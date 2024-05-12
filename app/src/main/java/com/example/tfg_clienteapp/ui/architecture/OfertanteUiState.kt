package com.example.tfg_clienteapp.ui.architecture

import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante

data class OfertanteUiState(
    val nombreOfertante: String = "",
    val listaMisActividades: ArrayList<ActividadOfertante> = arrayListOf(),
    val listaActividadesApuntado: ArrayList<ActividadConsumidor> = arrayListOf()
)
