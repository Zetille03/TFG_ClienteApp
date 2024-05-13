package com.example.tfg_clienteapp.ui.pantallas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tfg_clienteapp.MainActivity
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.ui.theme.Suave3
import com.example.tfg_clienteapp.ui.theme.TFG_ClienteAppTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomPantallaCarga")
class PantallaCarga: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            TFG_ClienteAppTheme {
                PantallaCargaFun()
            }
        }
    }

    @Preview
    @Composable
    public fun PantallaCargaFun() {
        val alpha = remember {
            Animatable(0f)
        }

        LaunchedEffect(key1 = true, block = {
            alpha.animateTo(1f,
                animationSpec = tween(1500)
            )
            delay(2000)
            startActivity(Intent(this@PantallaCarga, MainActivity::class.java))
        })
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Suave3),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier.alpha(alpha.value),
                painter = painterResource(id = R.drawable.vertical_logo),
                contentDescription = null
            )
        }
    }
}