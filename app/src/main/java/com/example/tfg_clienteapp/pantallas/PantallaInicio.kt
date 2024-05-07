package com.example.tfg_clienteapp.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.tfg_clienteapp.componentes.BotonHabilitado

@Composable
fun PantallaInicio(accionSignIn: ()-> Unit, accionSignUp: ()-> Unit,accionMenu: ()->Unit){
    Column {
        BotonHabilitado(textoBoton = "Sign In", accion = accionSignIn)
        BotonHabilitado(textoBoton = "Sign Up", accion = accionSignUp)
        BotonHabilitado(textoBoton = "Menu", accion = accionMenu)
    }
}