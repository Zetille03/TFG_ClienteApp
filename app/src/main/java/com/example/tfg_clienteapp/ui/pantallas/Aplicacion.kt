package com.example.tfg_clienteapp.ui.pantallas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.tfg_clienteapp.MyApp
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.pantallas.Consumidor.PantallaMenuPrincipal
import com.example.tfg_clienteapp.ui.data.Pantallas
import com.example.tfg_clienteapp.ui.pantallas.Consumidor.PantallaMisActividadesConsumidor
import com.example.tfg_clienteapp.ui.pantallas.Consumidor.PantallaTablonConsumidor

@Composable
fun Aplicacion(navController: NavHostController = rememberNavController()){
    val appViewModel = AppViewModel(MyApp.instance,navController)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ){
        NavHost(
            navController = navController,
            startDestination = Pantallas.PantallaInicio.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            composable(route = Pantallas.PantallaInicio.name){
                PantallaInicio(
                    accionSignIn = {navController.navigate(Pantallas.PantallaSignIn.name)},
                   accionSignUp =  {navController.navigate(Pantallas.PantallaSignUp.name)},
                    accionMenu = {navController.navigate(Pantallas.PantallaMenuPrincipalConsumidor.name)}
                )
            }
            composable(route = Pantallas.PantallaSignUp.name){
                PantallaSignUp(accionNavigator = {navController.navigate(Pantallas.PantallaSignIn.name)},appViewModel)
            }
            composable(route = Pantallas.PantallaSignIn.name){
                PantallaSignIn(accionNavigator = {navController.navigate(Pantallas.PantallaSignUp.name)},appViewModel)
            }
            composable(route = Pantallas.PantallaMenuPrincipalConsumidor.name){
                PantallaMenuPrincipal(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaTablonConsumidor.name ){
                PantallaTablonConsumidor(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaMisActividadesConsumidor.name){
                PantallaMisActividadesConsumidor(navController,appViewModel)
            }

        }
    }
}

