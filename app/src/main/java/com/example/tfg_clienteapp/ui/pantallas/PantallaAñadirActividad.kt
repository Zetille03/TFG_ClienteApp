package com.example.tfg_clienteapp.ui.pantallas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.BotonInhabilitado
import com.example.tfg_clienteapp.ui.componentes.CampoNumeroPlazas
import com.example.tfg_clienteapp.ui.componentes.CampoTextoDescripcion
import com.example.tfg_clienteapp.ui.componentes.CampoTextoTitulo
import com.example.tfg_clienteapp.ui.componentes.DatePickerWithDialog
import com.example.tfg_clienteapp.ui.componentes.DropDownList
import com.example.tfg_clienteapp.ui.theme.Intenso2
import com.example.tfg_clienteapp.ui.theme.Suave3

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAñadirActividad(navController: NavController, appViewModel: AppViewModel){
    val actividadUiState by appViewModel.actividadUiState.collectAsState()
    val logeoUiState by appViewModel.logeoUiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Añadir nueva actividad") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
            var categorias = listOf("naturaleza","deportes","educacion","otros")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Suave3),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ){
                CampoTextoTitulo(
                    appViewModel,
                    actividadUiState.titulo,
                    appViewModel.getTituloValido()
                )
                CampoTextoDescripcion(appViewModel,
                    actividadUiState.descripcion,
                    appViewModel.getDescripcionValido()
                )
                CampoNumeroPlazas(
                    appViewModel,
                    actividadUiState.nPlazas,
                    appViewModel.getNPlazasValido()
                )
                DropDownList(
                    appViewModel,
                    categorias,actividadUiState.categoria,
                    appViewModel.getCategoriaValido()
                    )
                DatePickerWithDialog(
                    appViewModel,
                    campoValido = appViewModel.getFechaValido()
                )
                BotonInhabilitado(textoBoton = "Registrar",
                    accion = {
                        if(logeoUiState.tipoUsuario=="Consumidor"){
                            appViewModel.postActividadConsumidor()
                        }else{
                            appViewModel.postActividadOfertante()
                        }
                    },
                    botonActivo = appViewModel.registrarActividadValido())
            }


        }
    )

}