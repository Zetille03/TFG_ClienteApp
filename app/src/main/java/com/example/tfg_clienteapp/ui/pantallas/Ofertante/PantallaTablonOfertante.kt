package com.example.tfg_clienteapp.ui.pantallas.Ofertante

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.DialogoTablonAnunciosConsumidor
import com.example.tfg_clienteapp.ui.componentes.DialogoTablonAnunciosOfertante
import com.example.tfg_clienteapp.ui.componentes.TablonActividadesConsumidoresCard
import com.example.tfg_clienteapp.ui.componentes.TablonActividadesOfertantesCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaTablonOfertante(navController: NavController, appViewModel: AppViewModel){
    Scaffold(
        topBar = {
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

                    IconButton(onClick = { appViewModel.actualizarActividadesConsumidoresDeOfertantes() }) {
                        Icon(Icons.Outlined.CloudSync, contentDescription = null)
                    }
                }
            )

        },
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                var actividadSeleccionada by remember { mutableStateOf<ActividadConsumidor?>(null) }

                actividadSeleccionada?.let { actividad ->
                    DialogoTablonAnunciosOfertante(
                        appViewModel = appViewModel,
                        actividad = actividad,
                        onDismiss = { actividadSeleccionada = null }
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(appViewModel.getListaActividadesConsumidoresDeOfertante()) { actividad ->
                        TablonActividadesConsumidoresCard(
                            actividadConsumidor = actividad,
                            accionClicar = {
                                actividadSeleccionada = actividad
                                appViewModel.addElementToListaActividadesRecientesOfertante(actividad)

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
    )
}