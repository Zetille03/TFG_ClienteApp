package com.example.tfg_clienteapp.ui.pantallas.Consumidor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import com.example.tfg_clienteapp.ui.componentes.TablonActividadesOfertantesCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaTablonConsumidor(navController: NavController, appViewModel: AppViewModel){
    val filtrosCategorias = listOf("Todos", "deportes", "naturaleza", "educacion", "otros")
    var filtroSeleccionado by remember { mutableStateOf("Todos") }
    var textoBusqueda by remember { mutableStateOf("") }
    var soloApuntadas by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Tablon de anuncios") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "ArrowBack"
                            )
                        }
                    }
                    ,
                    actions = {

                        IconButton(onClick = { appViewModel.actualizarActividadesOfertantesDeConsumidores() }) {
                            Icon(Icons.Outlined.CloudSync, contentDescription = null)
                        }
                    }
                )
                TextField(
                    value = textoBusqueda,
                    onValueChange = { textoBusqueda = it },
                    label = { Text("Buscar actividades") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = soloApuntadas,
                        onCheckedChange = { soloApuntadas = it }
                    )
                    Text(text = "Mostrar solo actividades a las que estoy apuntado")
                }
            }


        },
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                var actividadSeleccionada by remember { mutableStateOf<ActividadOfertante?>(null) }

                actividadSeleccionada?.let { actividad ->
                    DialogoTablonAnunciosConsumidor(
                        appViewModel = appViewModel,
                        actividad = actividad,
                        onDismiss = { actividadSeleccionada = null }
                    )
                }

                Column {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(filtrosCategorias) { filtro ->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        filtroSeleccionado = filtro
                                    }
                            ) {
                                Text(
                                    text = filtro,
                                    modifier = Modifier.padding(16.dp),
                                    color = if (filtro == filtroSeleccionado) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    val actividadesFiltradas = appViewModel.getListaActividadesOfertanteDeConsumidor().filter {
                        (filtroSeleccionado == "Todos" || it.categoria == filtroSeleccionado) &&
                                (textoBusqueda.isEmpty() || it.titulo.contains(textoBusqueda, ignoreCase = true) || it.titulo.startsWith(textoBusqueda, ignoreCase = true)) &&
                                (!soloApuntadas || appViewModel.estaApuntadoConsumidor(it.idActividadOfertante))
                    }



                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
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
                                cardSize = 250.dp,
                                imageSize = 150.dp
                            )
                        }
                    }
                }

            }



        }
    )

}
