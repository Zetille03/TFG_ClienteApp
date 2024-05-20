package com.example.tfg_clienteapp.ui.pantallas.Consumidor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.filled.SportsCricket
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.icons.outlined.SportsBaseball
import androidx.compose.material.icons.outlined.SportsCricket
import androidx.compose.material.icons.outlined.SportsTennis
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.componentes.ExpandibleCabecera
import com.example.tfg_clienteapp.ui.data.NavigationItem
import com.example.tfg_clienteapp.ui.data.Pantallas
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMenuPrincipal(navController: NavController, appViewModel: AppViewModel){
    val items = listOf(
        NavigationItem(
            title = "Mis actividades",
            selectedIcon = Icons.Filled.SportsTennis,
            unselectedIcon = Icons.Outlined.SportsTennis,
            route = Pantallas.PantallaInicio.name
        ),
        NavigationItem(
            title = "Actividades como participante",
            selectedIcon = Icons.Filled.SportsBaseball,
            unselectedIcon = Icons.Outlined.SportsBaseball,
            route = Pantallas.PantallaInicio.name
        ),
        NavigationItem(
            title="Tablon de anuncios",
            selectedIcon = Icons.Filled.SportsCricket,
            unselectedIcon = Icons.Outlined.SportsCricket,
            route = Pantallas.PantallaTablonConsumidor.name
        )

    )


    val icon = Icons.Filled.Info

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()

    val nombreUsuario = appViewModel.logeoUiState.value.nombreUsuario

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,

    ){
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(16.dp))
                    items.forEachIndexed { index, item ->

                        NavigationDrawerItem(
                            label = { Text (item.title) },
                            selected = index == selectedItemIndex,
                            onClick = {
                                    selectedItemIndex=index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(item.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = if(index == selectedItemIndex){
                                        item.selectedIcon
                                    }else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            badge = {
                                if(item.badgeCount != -1){
                                    Text(item.badgeCount.toString())
                                }
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)

                        )
                    }
                }
            }
            ,
            drawerState = drawerState
        ){
            Scaffold(
                topBar = {
                     TopAppBar(
                         title = { Text("Bienvenido, $nombreUsuario") },
                         navigationIcon = {
                             IconButton(onClick = { scope.launch {
                                        drawerState.open()
                                    }
                             }) {
                                 Icon(imageVector = Icons.Default.Menu, contentDescription = "Boton Menu")
                             }
                         },
                         scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
                     )
                    
                }

            ){paddingValues->
                Column(
                    modifier = Modifier.padding(paddingValues),
                    content = {
                        ExpandibleCabecera(textoCabecera = "Recientes")
                        ExpandibleCabecera(textoCabecera = "Favoritos")
                    }
                )

            }
        }


    }


}

