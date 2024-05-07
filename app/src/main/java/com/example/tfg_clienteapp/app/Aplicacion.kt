package com.example.tfg_clienteapp.app

import androidx.annotation.StringRes
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
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.pantallas.PantallaInicio
import com.example.tfg_clienteapp.pantallas.PantallaMenuPrincipal
import com.example.tfg_clienteapp.pantallas.PantallaSignIn
import com.example.tfg_clienteapp.pantallas.PantallaSignUp
import com.example.tfg_clienteapp.ui.data.Pantallas

@Composable
fun Aplicacion(navController: NavHostController = rememberNavController()){
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
                PantallaInicio(accionSignIn = {navController.navigate(Pantallas.PantallaSignIn.name)},
                   accionSignUp =  {navController.navigate(Pantallas.PantallaSignUp.name)},
                    accionMenu = {navController.navigate(Pantallas.PantallaMenuPrincipal.name)}
                )
            }
            composable(route = Pantallas.PantallaSignUp.name){
                PantallaSignUp(accionNavigator = {navController.navigate(Pantallas.PantallaSignIn.name)})
            }
            composable(route = Pantallas.PantallaSignIn.name){
                PantallaSignIn(accionNavigator = {navController.navigate(Pantallas.PantallaSignUp.name)})
            }
            composable(route = Pantallas.PantallaMenuPrincipal.name){
                PantallaMenuPrincipal(navController)
            }
        }
    }
}

