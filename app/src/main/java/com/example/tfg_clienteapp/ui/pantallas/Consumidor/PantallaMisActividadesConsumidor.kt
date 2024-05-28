package com.example.tfg_clienteapp.ui.pantallas.Consumidor

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
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
import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.DialogoMisActividadesConsumidor
import com.example.tfg_clienteapp.ui.componentes.DialogoTablonAnunciosConsumidor
import com.example.tfg_clienteapp.ui.componentes.MisActividadesConsumidorCard
import com.example.tfg_clienteapp.ui.componentes.TablonActividadesOfertantesCard
import com.example.tfg_clienteapp.ui.data.Pantallas

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMisActividadesConsumidor(navController: NavController, appViewModel: AppViewModel){
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
                        IconButton(onClick = { appViewModel.actualizarMisActividadesConsumidor() }) {
                            Icon(Icons.Outlined.CloudSync, contentDescription = null)
                        }
                    },
                    
                    scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
                    
                )

            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate(Pantallas.PantallaAñadirActividadConsumidor.name) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            content = { paddingValues ->
                var actividadSeleccionada by remember { mutableStateOf<ActividadConsumidor?>(null) }

                actividadSeleccionada?.let { actividad ->
                    DialogoMisActividadesConsumidor(
                        appViewModel = appViewModel,
                        actividad = actividad,
                        navController,
                        onDismiss = { actividadSeleccionada = null }
                    )
                }


                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    items(appViewModel.getListaMisActividadesConsumidor()) { actividad ->
                        MisActividadesConsumidorCard(
                            actividadConsumidor = actividad,
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