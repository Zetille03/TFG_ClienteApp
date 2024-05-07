package com.example.tfg_clienteapp.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.componentes.BotonInhabilitado
import com.example.tfg_clienteapp.componentes.CabeceraTextoNormal
import com.example.tfg_clienteapp.componentes.CajaChequeo
import com.example.tfg_clienteapp.componentes.CampoContraseñaUnico
import com.example.tfg_clienteapp.componentes.CampoTextoUnico
import com.example.tfg_clienteapp.componentes.CuerpoPoliticaPrivacidad
import com.example.tfg_clienteapp.componentes.CuerpoTerminosUso
import com.example.tfg_clienteapp.componentes.DialogoMuchoTexto
import com.example.tfg_clienteapp.componentes.TextoCambiarTipoRegistro
import com.example.tfg_clienteapp.componentes.TextoNormal
import com.example.tfg_clienteapp.ui.theme.*

@Composable
fun PantallaSignUp(accionNavigator: ()-> Unit = {}){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Suave3)
            .padding(28.dp)
    ){
        var politicaMostrado = rememberSaveable {
            mutableStateOf(false)
        }

        var terminosMostrado = rememberSaveable {
            mutableStateOf(false)
        }

        var checkboxPoliticas = rememberSaveable {
            mutableStateOf(false)
        }

        var usuarioString = rememberSaveable {
            mutableStateOf("")
        }

        var emailString = rememberSaveable {
            mutableStateOf("")
        }

        var contraseñaString = rememberSaveable {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxSize().background(Suave3),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextoNormal(value = "Bienvenido,")
            CabeceraTextoNormal(value = "Create una cuenta")
            Spacer(modifier = Modifier.height(20.dp))
            CampoTextoUnico(textoLabel = "Usuario", R.drawable.profile_icon)
            CampoTextoUnico(textoLabel = "Email", R.drawable.email_icon)
            CampoContraseñaUnico(textoLabel = "Password", R.drawable.password_icon)
            CajaChequeo(politicaMostrado,terminosMostrado,checkboxPoliticas)
            TextoCambiarTipoRegistro(
                "¿Tienes ya una cuenta?",
                "Logeate aquí.",
                accionEnlace = accionNavigator)
            BotonInhabilitado(
                textoBoton = "Sign Up",
                accion = { /*TODO*/ },
                checkboxActivo = checkboxPoliticas,
                modifier = Modifier.padding(start = 40.dp, end = 40.dp)
            )

        }


        if(politicaMostrado.value){
            DialogoMuchoTexto(textoCabecera = stringResource(id = R.string.politica_privacidad_cabecera), cuerpo = {CuerpoPoliticaPrivacidad()}, onDismiss = { politicaMostrado.value = false })
        }

        if(terminosMostrado.value){
            DialogoMuchoTexto(textoCabecera = stringResource(id = R.string.terminos_uso_cabecera), cuerpo = { CuerpoTerminosUso() }, onDismiss = { terminosMostrado.value = false })
        }



    }
}

@Preview
@Composable
fun LogeoPreview(){
    PantallaSignUp()
}