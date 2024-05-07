package com.example.tfg_clienteapp.ui.data

import androidx.annotation.StringRes
import com.example.tfg_clienteapp.R

enum class Pantallas(@StringRes val title: Int) {
    PantallaInicio(title = R.string.pantalla_inicio),
    PantallaSignUp(title = R.string.pantalla_signup),
    PantallaSignIn(title = R.string.pantalla_signin),
    PantallaMenuPrincipal(title = R.string.pantalla_menu)
}