package com.example.tfg_clienteapp.ui.architecture

data class LogeoUiState(
    val idUsuario: Int = 0,
    val tipoUsuario: String ="",
    val nombreUsuario: String ="",
    val email: String = "",
    val password: String = "",
    val emailValido: Boolean = true,
    val passwordValido: Boolean = true,
    val userValido: Boolean = true
)
