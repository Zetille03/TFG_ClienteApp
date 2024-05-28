package com.example.tfg_clienteapp.ui.architecture

data class ActividadUiState(
    val titulo: String = "",
    val descripcion: String = "",
    val nPlazas: Int = 0,
    val categoria : String = "",
    val fecha: String = "",
    val tituloValido: Boolean = false,
    val descripcionValido: Boolean = false,
    val nPlazasValido: Boolean = false,
    val categoriaValido: Boolean = false,
    val fechaValido: Boolean = false,
    val tipoRegistro: String = "POST",
    val idActividad: Int = 0
)
