package com.example.tfg_clienteapp.ui.pantallas.Consumidor

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
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.TablonActividadesOfertantesCard

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
                        IconButton(onClick = { appViewModel.actualizarActividadesOfertantesDeConsumidores() }) {
                            Icon(Icons.Outlined.CloudSync, contentDescription = null)
                        }
                    },
                    scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
                )

            },
            content = { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .height(2000.dp)
                        .padding(paddingValues)
                        .padding(horizontal = 20.dp)
                ) {
                    items(appViewModel.getListaMisActividadesConsumidor()) { actividad ->

                    }
                }

            }
        )
    }
}