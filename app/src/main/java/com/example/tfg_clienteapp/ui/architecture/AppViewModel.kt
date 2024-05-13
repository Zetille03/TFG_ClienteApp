package com.example.tfg_clienteapp.ui.architecture

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.tfg_clienteapp.MainActivity
import com.example.tfg_clienteapp.model.Consumidor
import com.example.tfg_clienteapp.retrofit.ConsumidorAPI
import com.example.tfg_clienteapp.retrofit.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppViewModel(private val context: Context): ViewModel()  {
    private val _consumidorUiState: MutableStateFlow<ConsumidorUiState> = MutableStateFlow(ConsumidorUiState())
    val consumidorUiState: StateFlow<ConsumidorUiState> = _consumidorUiState.asStateFlow()

    private val _ofertanteUiSate: MutableStateFlow<OfertanteUiState> = MutableStateFlow(OfertanteUiState())
    val ofertanteUiState: StateFlow<OfertanteUiState> = _ofertanteUiSate.asStateFlow()

    private val _logeoUiState: MutableStateFlow<LogeoUiState> = MutableStateFlow(LogeoUiState())
    val logeoUiState: StateFlow<LogeoUiState> = _logeoUiState.asStateFlow()

    private val retrofitService:RetrofitService = RetrofitService()

    private val consumidorAPI: ConsumidorAPI = retrofitService.retrofit.create(ConsumidorAPI::class.java)


    public fun setUser(valor: String){
        _logeoUiState.update {
            it.copy(
                nombreUsuario = valor
            )
        }
    }

    public fun setEmail(valor: String){
        _logeoUiState.update {
            it.copy(
                email = valor
            )
        }
    }

    public fun setPassword(valor: String){
        _logeoUiState.update {
            it.copy(
                password = valor
            )
        }
    }

    public fun setTipeUser(valor: String){
        _logeoUiState.update {
            it.copy(
                tipoUsuario = valor
            )
        }
    }


    public fun postConsumidor(){
        val c =  Consumidor()
        c.username = _logeoUiState.value.nombreUsuario
        c.email = _logeoUiState.value.email
        c.password = _logeoUiState.value.password
        val hash = c.hashCode()
        c.password = hash.toString()
        consumidorAPI.save(c).enqueue(object: Callback<Consumidor>{
            override fun onFailure(p0: Call<Consumidor>, p1: Throwable) {
                Toast.makeText(context,"Error en el registro", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(p0: Call<Consumidor>, p1: Response<Consumidor>) {
                Toast.makeText(context,"Usuario registrado", Toast.LENGTH_LONG).show()
            }
        })
    }

    public fun postOfertante(usuario: String, email: String, contra: String){

    }




}