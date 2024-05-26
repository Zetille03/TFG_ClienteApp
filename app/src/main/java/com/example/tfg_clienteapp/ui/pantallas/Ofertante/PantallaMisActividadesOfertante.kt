package com.example.tfg_clienteapp.ui.pantallas.Ofertante

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg_clienteapp.model.ActividadOfertante
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.DialogoMisActividadesOfertante
import com.example.tfg_clienteapp.ui.componentes.MisActividadesOfertanteCard
import com.example.tfg_clienteapp.ui.data.Pantallas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMisActividadesOfertante(navController: NavController, appViewModel: AppViewModel){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Mis Actividades") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "ArrowBack"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { appViewModel.actualizarMisActividadesOfertante() }) {
                            Icon(Icons.Outlined.CloudSync, contentDescription = null)
                        }
                    },

                    scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

                )

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
                        onDismiss = { actividadSeleccionada = null }
                    )
                }


                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    items(appViewModel.getListaMisActividadesOfertante()) { actividad ->
                        MisActividadesOfertanteCard(
                            actividadOfertante = actividad,
                            accionClicar = { actividadSeleccionada = actividad },
                            cardSize = 250.dp,
                            imageSize = 150.dp
                        )
                    }
                }

            }
        )
    }
}