package com.example.tfg_clienteapp.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.BotonInhabilitado
import com.example.tfg_clienteapp.ui.componentes.CabeceraTextoNormal
import com.example.tfg_clienteapp.ui.componentes.CajaChequeo
import com.example.tfg_clienteapp.ui.componentes.CampoContraseñaUnico
import com.example.tfg_clienteapp.ui.componentes.CampoTextoEmail
import com.example.tfg_clienteapp.ui.componentes.CampoTextoUser
import com.example.tfg_clienteapp.ui.componentes.CuerpoPoliticaPrivacidad
import com.example.tfg_clienteapp.ui.componentes.CuerpoTerminosUso
import com.example.tfg_clienteapp.ui.componentes.DialogoMuchoTexto
import com.example.tfg_clienteapp.ui.componentes.EleccionUsuario
import com.example.tfg_clienteapp.ui.componentes.TextoCambiarTipoRegistro
import com.example.tfg_clienteapp.ui.componentes.TextoNormal
import com.example.tfg_clienteapp.ui.theme.*

@Composable
fun PantallaSignUp(accionNavigator: ()-> Unit = {},viewModel: AppViewModel){
    val politicaMostrado = rememberSaveable {
        mutableStateOf(false)
    }

    val terminosMostrado = rememberSaveable {
        mutableStateOf(false)
    }

    val checkboxPoliticas = rememberSaveable {
        mutableStateOf(false)
    }



    val logeoUiState by viewModel.logeoUiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Suave3)
            .padding(28.dp)
    ){



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Suave3),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextoNormal(value = "Bienvenido,")
            CabeceraTextoNormal(value = "Create una cuenta")
            Spacer(modifier = Modifier.height(20.dp))
            CampoTextoUser(
                viewModel,
                logeoUiState.nombreUsuario, textoLabel = "Usuario",
                R.drawable.profile_icon,
                logeoUiState.userValido
            )
            CampoTextoEmail(viewModel,
                logeoUiState.email, textoLabel = "Email",
                R.drawable.email_icon,
                logeoUiState.emailValido)
            CampoContraseñaUnico(
                viewModel,
                logeoUiState.password, textoLabel = "Password",
                R.drawable.password_icon,
                logeoUiState.passwordValido)
            EleccionUsuario(viewModel)
            CajaChequeo(politicaMostrado,terminosMostrado,checkboxPoliticas)
            TextoCambiarTipoRegistro(
                "¿Tienes ya una cuenta?",
                "Logeate aquí.",
                accionEnlace = accionNavigator)
            BotonInhabilitado(
                textoBoton = "Sign Up",
                accion = {
                         if(logeoUiState.tipoUsuario == "Consumidor"){
                            viewModel.postConsumidor()
                         }else{
                            viewModel.postOfertante()
                         }
                     },
                botonActivo = checkboxPoliticas.value && viewModel.registroUsuarioValido(),
                modifier = Modifier.padding(start = 40.dp, end = 40.dp)
            )

        }


        if(politicaMostrado.value){
            DialogoMuchoTexto(textoCabecera = stringResource(id = R.string.politica_privacidad_cabecera), cuerpo = { CuerpoPoliticaPrivacidad() }, onDismiss = { politicaMostrado.value = false })
        }

        if(terminosMostrado.value){
            DialogoMuchoTexto(textoCabecera = stringResource(id = R.string.terminos_uso_cabecera), cuerpo = { CuerpoTerminosUso() }, onDismiss = { terminosMostrado.value = false })
        }



    }
}
