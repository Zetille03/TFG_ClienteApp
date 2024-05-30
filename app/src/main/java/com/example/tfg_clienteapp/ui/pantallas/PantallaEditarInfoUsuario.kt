package com.example.tfg_clienteapp.ui.pantallas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.BotonInhabilitado
import com.example.tfg_clienteapp.ui.componentes.CabeceraTextoNormal
import com.example.tfg_clienteapp.ui.componentes.CampoContraseñaUnico
import com.example.tfg_clienteapp.ui.componentes.CampoNumeroPlazas
import com.example.tfg_clienteapp.ui.componentes.CampoTextoDescripcion
import com.example.tfg_clienteapp.ui.componentes.CampoTextoEmail
import com.example.tfg_clienteapp.ui.componentes.CampoTextoTitulo
import com.example.tfg_clienteapp.ui.componentes.CampoTextoUser
import com.example.tfg_clienteapp.ui.componentes.DatePickerWithDialog
import com.example.tfg_clienteapp.ui.componentes.DropDownList
import com.example.tfg_clienteapp.ui.componentes.EleccionUsuario
import com.example.tfg_clienteapp.ui.componentes.TextoCambiarTipoRegistro
import com.example.tfg_clienteapp.ui.componentes.TextoNormal
import com.example.tfg_clienteapp.ui.theme.Intenso2
import com.example.tfg_clienteapp.ui.theme.Suave3

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarInfoUsuario(navController: NavController, appViewModel: AppViewModel){
    val logeoUiState by appViewModel.logeoUiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar datos usuarios") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                    {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                },
                scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Intenso2)
            )

        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Suave3),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                CabeceraTextoNormal(value = "Modifica tus datos")
                Spacer(modifier = Modifier.height(20.dp))
                CampoTextoUser(
                    appViewModel,
                    logeoUiState.nombreUsuario,
                    textoLabel = "Usuario",
                    R.drawable.profile_icon,
                    logeoUiState.userValido
                )
                CampoTextoEmail(appViewModel,
                    logeoUiState.email, textoLabel = "Email",
                    R.drawable.email_icon,
                    logeoUiState.emailValido)
                CampoContraseñaUnico(
                    appViewModel,
                    logeoUiState.password, textoLabel = "Password",
                    R.drawable.password_icon,
                    logeoUiState.passwordValido
                )
                BotonInhabilitado(
                    textoBoton = "Editar",
                    accion = {
                        if(logeoUiState.tipoUsuario==("Consumidor")){
                            appViewModel.cambiarDatosConsumidor()
                        }else{
                            appViewModel.cambiarDatosOfertante()
                        }
                    },
                    botonActivo = appViewModel.loginUsuarioValido(),
                    modifier = Modifier.padding(start = 40.dp, end = 40.dp)
                )
            }


        }
    )
}
