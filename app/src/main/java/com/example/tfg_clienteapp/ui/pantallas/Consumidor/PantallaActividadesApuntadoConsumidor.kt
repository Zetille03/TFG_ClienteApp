package com.example.tfg_clienteapp.ui.pantallas.Consumidor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg_clienteapp.model.ActividadOfertante
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.DialogoTablonAnunciosConsumidor
import com.example.tfg_clienteapp.ui.componentes.ExpandibleFiltros
import com.example.tfg_clienteapp.ui.componentes.TablonActividadesOfertantesCard
import com.example.tfg_clienteapp.ui.theme.Intenso2
import com.example.tfg_clienteapp.ui.theme.Suave3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaActividadesApuntadoConsumidor(navController: NavController, appViewModel: AppViewModel){
    val filtrosCategorias = listOf("Todos", "deportes", "naturaleza", "educacion", "otros")
    var filtroSeleccionado by remember { mutableStateOf("Todos") }
    var textoBusqueda by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Actividades como participante") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "ArrowBack"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Intenso2
                    ),
                    actions = {

                        IconButton(onClick = { appViewModel.actualizarActividadesApuntadoConsumidor() }) {
                            Icon(Icons.Outlined.CloudSync, contentDescription = null)
                        }
                    }
                )
                ExpandibleFiltros(
                    textoBusqueda = textoBusqueda,
                    accionTexto = {newValue->textoBusqueda = newValue},
                    filtrosCategorias = filtrosCategorias,
                    categoria = filtroSeleccionado,
                    accionCategoria = {newValue->filtroSeleccionado= newValue}
                )
            }


        },
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Suave3)
            ) {
                var actividadSeleccionada by remember { mutableStateOf<ActividadOfertante?>(null) }

                actividadSeleccionada?.let { actividad ->
                    DialogoTablonAnunciosConsumidor(
                        appViewModel = appViewModel,
                        actividad = actividad,
                        onDismiss = { actividadSeleccionada = null }
                    )
                }

                val actividadesFiltradas = appViewModel.getListaActividadesApuntadoConsumidor().filter { actividad ->

                    (filtroSeleccionado == "Todos" || actividad.categoria == filtroSeleccionado) &&
                            (textoBusqueda.isEmpty() ||
                                    actividad.titulo.contains(textoBusqueda, ignoreCase = true) ||
                                    actividad.titulo.startsWith(textoBusqueda, ignoreCase = true))
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(actividadesFiltradas) { actividad ->
                        TablonActividadesOfertantesCard(
                            actividadOfertante = actividad,
                            accionClicar = {
                                actividadSeleccionada = actividad
                                appViewModel.addElementToListaActividadesRecientesConsumidor(actividad)

                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            imageSize = 150.dp
                        )
                    }
                }
            }



        }
    )
}