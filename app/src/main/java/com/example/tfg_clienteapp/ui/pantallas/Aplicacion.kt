package com.example.tfg_clienteapp.ui.pantallas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.tfg_clienteapp.ui.data.Pantallas
import com.example.tfg_clienteapp.ui.pantallas.Consumidor.PantallaActividadesApuntadoConsumidor
import com.example.tfg_clienteapp.ui.pantallas.Consumidor.PantallaAñadirActividadConsumidor
import com.example.tfg_clienteapp.ui.pantallas.Consumidor.PantallaMenuPrincipalConsumidor
import com.example.tfg_clienteapp.ui.pantallas.Consumidor.PantallaMisActividadesConsumidor
import com.example.tfg_clienteapp.ui.pantallas.Consumidor.PantallaTablonConsumidor
import com.example.tfg_clienteapp.ui.pantallas.Ofertante.PantallaActividadesApuntadoOfertante
import com.example.tfg_clienteapp.ui.pantallas.Ofertante.PantallaAñadirActividadOfertante
import com.example.tfg_clienteapp.ui.pantallas.Ofertante.PantallaMenuPrincipalOfertante
import com.example.tfg_clienteapp.ui.pantallas.Ofertante.PantallaMisActividadesOfertante
import com.example.tfg_clienteapp.ui.pantallas.Ofertante.PantallaTablonOfertante

@RequiresApi(Build.VERSION_CODES.O)
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
        ){
            composable(route = Pantallas.PantallaInicio.name){
                appViewModel.resetAllUiStates()
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
                appViewModel.setTipeUser("Consumidor")
                PantallaMenuPrincipalConsumidor(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaTablonConsumidor.name ){
                appViewModel.setTipeUser("Consumidor")
                PantallaTablonConsumidor(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaMisActividadesConsumidor.name){
                appViewModel.setTipeUser("Consumidor")
                PantallaMisActividadesConsumidor(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaAñadirActividadConsumidor.name){
                appViewModel.setTipeUser("Consumidor")
                PantallaAñadirActividadConsumidor(navController,appViewModel)
            }
            composable(route=Pantallas.PantallaAñadirActividadOfertante.name){
                appViewModel.setTipeUser("Ofertante")
                PantallaAñadirActividadOfertante(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaMenuPrincipalOfertante.name){
                appViewModel.setTipeUser("Ofertante")
                PantallaMenuPrincipalOfertante(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaMisActividadesOfertante.name){
                appViewModel.setTipeUser("Ofertante")
                PantallaMisActividadesOfertante(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaTablonOfertante.name){
                appViewModel.setTipeUser("Ofertante")
                PantallaTablonOfertante(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaActividadesApuntadoConsumidor.name){
                appViewModel.setTipeUser("Consumidor")
                PantallaActividadesApuntadoConsumidor(navController,appViewModel)
            }
            composable(route=Pantallas.PantallaActividadesApuntadoOfertante.name){
                appViewModel.setTipeUser("Ofertante")
                PantallaActividadesApuntadoOfertante(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaConfiguracion.name){
                PantallaConfiguracion(navController,appViewModel)
            }
            composable(route = Pantallas.PantallaEditarInfoUsuario.name){
                PantallaEditarInfoUsuario(navController,appViewModel)
            }
        }
    }
}

