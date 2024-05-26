package com.example.tfg_clienteapp.ui.data

import androidx.annotation.StringRes
import com.example.tfg_clienteapp.R

enum class Pantallas(@StringRes val title: Int) {
    PantallaInicio(title = R.string.pantalla_inicio),
    PantallaSignUp(title = R.string.pantalla_signup),
    PantallaSignIn(title = R.string.pantalla_signin),
    PantallaMenuPrincipalConsumidor(title = R.string.pantalla_menu_consumidor),
    PantallaTablonConsumidor(title= R.string.pantalla_tablon_consumidor),
    PantallaMenuPrincipalOfertante(title = R.string.pantalla_menu_ofertante),
    PantallaMisActividadesConsumidor(title=R.string.pantalla_mis_actividades_consumidor),
    PantallaAñadirActividadConsumidor(title=R.string.pantalla_añadir_actividad_consumidor),
    PantallaAñadirActividadOfertante(title=R.string.pantalla_añadir_actividad_ofertante),
    PantallaMisActividadesOfertante(title=R.string.pantalla_mis_actividades_ofertante),
    PantallaTablonOfertante(title=R.string.pantalla_tablon_ofertante),
    PantallaActividadesApuntadoConsumidor(title=R.string.pantalla_actividades_apuntado_consumidor),
    PantallaActividadesApuntadoOfertante(title=R.string.pantalla_actividades_apuntado_ofertante)
}