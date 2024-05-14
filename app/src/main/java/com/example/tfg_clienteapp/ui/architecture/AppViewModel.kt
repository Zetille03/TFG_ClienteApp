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

class AppViewModel(public val context: Context): ViewModel()  {
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

    public fun setUserValido(valor: Boolean){
        _logeoUiState.update {
            it.copy(
                userValido = valor
            )
        }
    }

    public fun setEmailValido(valor: Boolean){
        _logeoUiState.update {
            it.copy(
                emailValido = valor
            )
        }
    }
    public fun setPasswordValido(valor: Boolean){
        _logeoUiState.update {
            it.copy(
                passwordValido = valor
            )
        }
    }


    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return emailRegex.matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        return password.length in 6..16
    }

    fun isValidUser(password: String): Boolean {
        return password.length in 5..30
    }

    fun registroUsuarioValido(): Boolean{
        return isValidPassword(logeoUiState.value.password)
                && isValidEmail(logeoUiState.value.email)
                && isValidUser(logeoUiState.value.nombreUsuario)
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
                Toast.makeText(context,"Error de conexion", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(p0: Call<Consumidor>, p1: Response<Consumidor>) {
                if(p1.body()==null){
                    Toast.makeText(context,"Registro equívoco", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(context,"Usuario registrado", Toast.LENGTH_LONG).show()
                }
            }


        })
    }

    public fun postOfertante(usuario: String, email: String, contra: String){

    }

    fun comprobarSigninConsumidor() {
        val c =  Consumidor()
        c.username = _logeoUiState.value.nombreUsuario
        c.password = _logeoUiState.value.password
        val hash = c.hashCode()
        c.password = hash.toString()
        consumidorAPI.getConsumidorLogIn(c.username,c.password).enqueue(object: Callback<Consumidor>{
            override fun onResponse(p0: Call<Consumidor>, p1: Response<Consumidor>) {
                if(p1.body()==null){
                    Toast.makeText(context,"Registro equívoco", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(context,"Usuario registrado", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<Consumidor>, p1: Throwable) {
                Toast.makeText(context,"Error de conexion", Toast.LENGTH_LONG).show()
            }

        })
    }


}