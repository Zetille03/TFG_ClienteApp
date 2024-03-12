package com.example.tfg_clienteapp.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.componentes.CabezeraTextoNormal
import com.example.tfg_clienteapp.componentes.CampoTextoUnico
import com.example.tfg_clienteapp.componentes.TextoNormal

@Composable
fun PantallaSignUp(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextoNormal(value = "Bienvenido,")
            CabezeraTextoNormal(value = "Create una cuenta")
            Spacer(modifier = Modifier.height(20.dp))
            CampoTextoUnico(textoLabel = "Usuario", R.drawable.profile_icon)
            CampoTextoUnico(textoLabel = "Email", R.drawable.email_icon)
            CampoTextoUnico(textoLabel = "Email", R.drawable.password_icon)
        }
    }
}

@Preview
@Composable
fun LogeoPreview(){
    PantallaSignUp()
}