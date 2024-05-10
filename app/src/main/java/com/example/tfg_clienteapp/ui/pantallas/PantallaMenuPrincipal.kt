package com.example.tfg_clienteapp.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.icons.outlined.SportsBaseball
import androidx.compose.material.icons.outlined.SportsTennis
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
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
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.ui.data.NavigationItem
import com.example.tfg_clienteapp.ui.data.Pantallas
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMenuPrincipal(navController: NavController){
    val items = listOf(
        NavigationItem(
            title = "Mis actividades",
            selectedIcon = Icons.Filled.SportsTennis,
            unselectedIcon = Icons.Outlined.SportsTennis,
            route = Pantallas.PantallaInicio.name
        ),
        NavigationItem(
            title = "Mis ofertas",
            selectedIcon = Icons.Filled.SportsBaseball,
            unselectedIcon = Icons.Outlined.SportsBaseball,
            route = Pantallas.PantallaInicio.name
        )

    )


    val icon = Icons.Filled.Info

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()
    val itemsCard = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
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
                         title = { Text("TODO MENU") },
                         navigationIcon = {
                             IconButton(onClick = { scope.launch {
                                        drawerState.open()
                                    }
                             }) {
                                 Icon(imageVector = Icons.Default.Menu, contentDescription = "Boton Menu")
                             }
                         }
                     )
                    
                }
            ){paddingValues->
                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    itemsIndexed(itemsCard) { index, item ->
                        CardItem(item,Modifier.padding(paddingValues))
                    }
                }
            }
        }


    }


}

@Composable
fun CardItem(itemText: String,modifier: Modifier = Modifier,){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .width(200.dp)
            .padding(16.dp)
            .clickable {

            }

        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

//            Image(
//                painterResource(id = R.drawable)
//            )
            Text(
                text = itemText,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Descripción de la tarjeta. Esto es un ejemplo de texto de descripción que puede ser bastante largo y ocupar varias líneas.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )

        }
    }
}