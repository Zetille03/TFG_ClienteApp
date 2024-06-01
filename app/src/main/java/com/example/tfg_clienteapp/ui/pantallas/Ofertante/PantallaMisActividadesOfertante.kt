package com.example.tfg_clienteapp.ui.pantallas.Ofertante

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.example.tfg_clienteapp.ui.componentes.DialogoMisActividadesOfertante
import com.example.tfg_clienteapp.ui.componentes.ExpandibleFiltros
import com.example.tfg_clienteapp.ui.componentes.MisActividadesOfertanteCard
import com.example.tfg_clienteapp.ui.data.Pantallas
import com.example.tfg_clienteapp.ui.theme.Intenso2
import com.example.tfg_clienteapp.ui.theme.Suave3

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMisActividadesOfertante(navController: NavController, appViewModel: AppViewModel){
    val filtrosCategorias = listOf("Todos", "deportes", "naturaleza", "educacion", "otros")
    var filtroSeleccionado by remember { mutableStateOf("Todos") }
    var textoBusqueda by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Mis Actividades") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "ArrowBack"
                            )
                        }
                    },colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Intenso2
                    ),
                    actions = {
                        IconButton(onClick = { appViewModel.actualizarMisActividadesOfertante() }) {
                            Icon(Icons.Outlined.CloudSync, contentDescription = null)
                        }
                    },

                    scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

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
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Pantallas.PantallaAñadirActividadOfertante.name) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            var actividadSeleccionada by remember { mutableStateOf<ActividadOfertante?>(null) }

            actividadSeleccionada?.let { actividad ->
                DialogoMisActividadesOfertante(
                    appViewModel = appViewModel,
                    actividad = actividad,
                    navController,
                    onDismiss = { actividadSeleccionada = null }
                )
            }

            val actividadesFiltradas = appViewModel.getListaMisActividadesOfertante().filter { actividad ->

                (filtroSeleccionado == "Todos" || actividad.categoria == filtroSeleccionado) &&
                        (textoBusqueda.isEmpty() ||
                                actividad.titulo.contains(textoBusqueda, ignoreCase = true) ||
                                actividad.titulo.startsWith(textoBusqueda, ignoreCase = true))
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Suave3)
            ){
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(actividadesFiltradas) { actividad ->
                        MisActividadesOfertanteCard(
                            actividadOfertante = actividad,
                            accionClicar = { actividadSeleccionada = actividad },
                            imageSize = 150.dp
                        )
                    }
                }
            }




        }
    )

}