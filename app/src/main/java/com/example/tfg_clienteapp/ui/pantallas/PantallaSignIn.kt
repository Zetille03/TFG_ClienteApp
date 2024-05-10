package com.example.tfg_clienteapp.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.ui.componentes.BotonHabilitado
import com.example.tfg_clienteapp.ui.componentes.BotonInhabilitado
import com.example.tfg_clienteapp.ui.componentes.CabeceraTextoNormal
import com.example.tfg_clienteapp.ui.componentes.CampoContraseñaUnico
import com.example.tfg_clienteapp.ui.componentes.CampoTextoUnico
import com.example.tfg_clienteapp.ui.componentes.TextoCambiarTipoRegistro
import com.example.tfg_clienteapp.ui.componentes.TextoNormal
import com.example.tfg_clienteapp.ui.theme.*

@Composable
fun PantallaSignIn(accionNavigator: ()-> Unit = {}){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Suave3)
            .padding(28.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize().background(Suave3),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextoNormal(value = "Bienvenido,")
            CabeceraTextoNormal(value = "Logeate en tu cuenta")
            Spacer(modifier = Modifier.height(20.dp))
            CampoTextoUnico(textoLabel = "Usuario", R.drawable.profile_icon)
            CampoContraseñaUnico(textoLabel = "Password", R.drawable.password_icon)
            TextoCambiarTipoRegistro(
                "¿No tienes cuenta?",
                "Registrate aquí.",
                accionEnlace = accionNavigator)
            BotonHabilitado(
                textoBoton = "Sign Up",
                accion = { /*TODO*/ },
                modifier = Modifier.padding(start = 40.dp, end = 40.dp)
            )
        }
    }
}