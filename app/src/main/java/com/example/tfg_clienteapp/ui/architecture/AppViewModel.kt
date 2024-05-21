package com.example.tfg_clienteapp.ui.architecture

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante
import com.example.tfg_clienteapp.model.Consumidor
import com.example.tfg_clienteapp.model.ConsumidorActividadOfertante
import com.example.tfg_clienteapp.model.Ofertante
import com.example.tfg_clienteapp.retrofit.ActividadConsumidorAPI
import com.example.tfg_clienteapp.retrofit.ActividadOfertanteAPI
import com.example.tfg_clienteapp.retrofit.ConsumidorAPI
import com.example.tfg_clienteapp.retrofit.ConsumidorActividadOfertanteAPI
import com.example.tfg_clienteapp.retrofit.OfertanteAPI
import com.example.tfg_clienteapp.retrofit.RetrofitService
import com.example.tfg_clienteapp.ui.data.Pantallas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class AppViewModel(public val context: Context, public val navigator: NavHostController): ViewModel()  {

    //region VARIABLES VIEWMODEL

    private val _consumidorUiState: MutableStateFlow<ConsumidorUiState> = MutableStateFlow(ConsumidorUiState())
    val consumidorUiState: StateFlow<ConsumidorUiState> = _consumidorUiState.asStateFlow()

    private val _ofertanteUiSate: MutableStateFlow<OfertanteUiState> = MutableStateFlow(OfertanteUiState())
    val ofertanteUiState: StateFlow<OfertanteUiState> = _ofertanteUiSate.asStateFlow()

    private val _logeoUiState: MutableStateFlow<LogeoUiState> = MutableStateFlow(LogeoUiState())
    val logeoUiState: StateFlow<LogeoUiState> = _logeoUiState.asStateFlow()

    //endregion

    //region RETROFIT SERVICE VARIABLES
    private val retrofitService:RetrofitService = RetrofitService()

    private val consumidorAPI: ConsumidorAPI = retrofitService.retrofit.create(ConsumidorAPI::class.java)

    private val ofertanteAPI: OfertanteAPI = retrofitService.retrofit.create(OfertanteAPI::class.java)

    private val actividadConsumidorAPI: ActividadConsumidorAPI = retrofitService.retrofit.create(ActividadConsumidorAPI::class.java)

    private val actividadOfertanteAPI: ActividadOfertanteAPI = retrofitService.retrofit.create(ActividadOfertanteAPI::class.java)

    private val consumidorActividadOfertanteAPI: ConsumidorActividadOfertanteAPI = retrofitService.retrofit.create(ConsumidorActividadOfertanteAPI::class.java)

    //endregion

    //region UISTATE

    //region LOGEO

    fun getIdUser(): Int {
        return _logeoUiState.value.idUsuario;
    }

    public fun setIdUser(valor: Int){
        _logeoUiState.update {
            it.copy(
                idUsuario = valor
            )
        }
    }

    fun getUser(): String {
        return _logeoUiState.value.nombreUsuario;
    }

    public fun setUser(valor: String){
        _logeoUiState.update {
            it.copy(
                nombreUsuario = valor
            )
        }
    }

    fun getEmail(): String {
        return _logeoUiState.value.email;
    }

    public fun setEmail(valor: String){
        _logeoUiState.update {
            it.copy(
                email = valor
            )
        }
    }

    fun getPassword(): String {
        return _logeoUiState.value.password;
    }

    public fun setPassword(valor: String){
        _logeoUiState.update {
            it.copy(
                password = valor
            )
        }
    }

    fun getTypeUser(): String {
        return _logeoUiState.value.tipoUsuario;
    }

    public fun setTipeUser(valor: String){
        _logeoUiState.update {
            it.copy(
                tipoUsuario = valor
            )
        }
    }

    fun getUserValido(): Boolean {
        return _logeoUiState.value.userValido;
    }

    public fun setUserValido(valor: Boolean){
        _logeoUiState.update {
            it.copy(
                userValido = valor
            )
        }
    }

    fun getEmailValido(): Boolean {
        return _logeoUiState.value.emailValido;
    }

    public fun setEmailValido(valor: Boolean){
        _logeoUiState.update {
            it.copy(
                emailValido = valor
            )
        }
    }

    fun getEPasswordValido(): Boolean {
        return _logeoUiState.value.passwordValido;
    }

    public fun setPasswordValido(valor: Boolean){
        _logeoUiState.update {
            it.copy(
                passwordValido = valor
            )
        }
    }

    //endregion

    //region Consumidor

    fun getListaMisActividadesConsumidor(): List<ActividadConsumidor> {
        return _consumidorUiState.value.listaMisActividades;
    }

    fun getListaActividadesApuntadoConsumidor(): List<ActividadOfertante> {
        return _consumidorUiState.value.listaActividadesOfertantes;
    }

    fun getListaActividadesOfertanteDeConsumidor(): List<ActividadOfertante> {
        return _consumidorUiState.value.listaActividadesOfertantes;
    }



    fun setListaMisActividadesConsumidor(lista: List<ActividadConsumidor>){
        _consumidorUiState.update {
            it.copy(
                listaMisActividades = lista
            )
        }
    }

    fun setListaActividadesApuntadoConsumidor(lista: List<ActividadOfertante>){
        _consumidorUiState.update {
            it.copy(
                listaActividadesApuntado = lista
            )
        }
    }

    fun setListaActividadesOfertantesDeConsumidor(lista: List<ActividadOfertante>){
        _consumidorUiState.update {
            it.copy(
                listaActividadesOfertantes = lista
            )
        }
    }

    //endregion

    //region Ofertante

    fun getListaMisActividadesOfertante(): List<ActividadOfertante> {
        return _ofertanteUiSate.value.listaMisActividades;
    }

    fun setListaMisActividadesOfertante(lista: List<ActividadOfertante>){
        _ofertanteUiSate.update {
            it.copy(
                listaMisActividades = lista
            )
        }
    }

    fun getListaActividadesApuntadoOfertante(): List<ActividadConsumidor> {
        return _ofertanteUiSate.value.listaActividadesApuntado;
    }

    fun setListaActividadesApuntadoOfertante(lista: List<ActividadConsumidor>){
        _ofertanteUiSate.update {
            it.copy(
                listaActividadesApuntado = lista
            )
        }
    }

    fun getListaActividadesConsumidoresDeOfertante(): List<ActividadConsumidor> {
        return _ofertanteUiSate.value.listaActividadesAConsumidores;
    }

    fun setListaActividadesConsumidoresDeOfertante(lista: List<ActividadConsumidor>){
        _ofertanteUiSate.update {
            it.copy(
                listaActividadesAConsumidores = lista
            )
        }
    }


    //endregion

    //endregion

    //region

    //region FUNCIONES

    //region SIGN IN/SIGN UP

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

    fun loginUsuarioValido():Boolean{
        return isValidPassword(logeoUiState.value.password)
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
                    Toast.makeText(context,"Consumidor registrado", Toast.LENGTH_LONG).show()
                    navigator.navigate(Pantallas.PantallaMenuPrincipalConsumidor.name)
                }
            }


        })
    }

    public fun postOfertante(){
        val o = Ofertante()
        o.username = logeoUiState.value.nombreUsuario;
        o.email = logeoUiState.value.email;
        o.password = logeoUiState.value.password;

        val hash = o.hashCode();
        o.password = hash.toString()

        ofertanteAPI.save(o).enqueue(object: Callback<Ofertante>{
            override fun onFailure(p0: Call<Ofertante>, p1: Throwable) {
                Toast.makeText(context,"Error de conexion", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(p0: Call<Ofertante>, p1: Response<Ofertante>) {
                if(p1.body()==null){
                    Toast.makeText(context,"Registro equívoco", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(context,"Ofertante registrado", Toast.LENGTH_LONG).show()
                    navigator.navigate(Pantallas.PantallaMenuPrincipalConsumidor.name)

                }
            }


        })
    }

    fun comprobarSigninConsumidor() {
        val c =  Consumidor()
        c.username = _logeoUiState.value.nombreUsuario
        c.password = _logeoUiState.value.password
        val hash = c.hashCode()
        c.password = hash.toString()
        consumidorAPI.comprobarLogIn(c.username,c.password).enqueue(object: Callback<Consumidor>{
            override fun onResponse(p0: Call<Consumidor>, p1: Response<Consumidor>) {
                if(p1.body()==null){
                    Toast.makeText(context,"Registro equívoco", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(context,"Consumidor logeado", Toast.LENGTH_LONG).show()
                    setIdUser(p1.body()!!.idConsumidor)
                    navigator.navigate(Pantallas.PantallaMenuPrincipalConsumidor.name)
                    actividadOfertanteAPI.allActividadesOfertantes.enqueue(object: Callback<List<ActividadOfertante>>{
                        override fun onResponse(
                            p0: Call<List<ActividadOfertante>>,
                            p1: Response<List<ActividadOfertante>>
                        ) {
                            if(p1.body()!=null){
                                setListaActividadesOfertantesDeConsumidor(p1.body()!!)
                            }
                        }

                        override fun onFailure(p0: Call<List<ActividadOfertante>>, p1: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
                }
            }

            override fun onFailure(p0: Call<Consumidor>, p1: Throwable) {
                Toast.makeText(context,"Error de conexion", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun comprobarSigninOfertante() {
        val o =  Ofertante()
        o.username = _logeoUiState.value.nombreUsuario
        o.password = _logeoUiState.value.password
        val hash = o.hashCode()
        o.password = hash.toString()
        ofertanteAPI.comprobarLogIn(o.username,o.password).enqueue(object: Callback<Ofertante>{
            override fun onResponse(p0: Call<Ofertante>, p1: Response<Ofertante>) {
                if(p1.body()==null){
                    Toast.makeText(context,"Registro equívoco", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"Ofertante logeado", Toast.LENGTH_LONG).show()
                    setIdUser(p1.body()!!.idOfertante)
                    navigator.navigate(Pantallas.PantallaMenuPrincipalConsumidor.name)

                }
            }

            override fun onFailure(p0: Call<Ofertante>, p1: Throwable) {
                Toast.makeText(context,"Error de conexion", Toast.LENGTH_LONG).show()
            }

        })
    }

    //endregion

    //region CONSUMIDOR

    fun actualizarActividadesOfertantesDeConsumidores(){
        actividadOfertanteAPI.allActividadesOfertantes.enqueue(object: Callback<List<ActividadOfertante>>{
            override fun onResponse(
                p0: Call<List<ActividadOfertante>>,
                p1: Response<List<ActividadOfertante>>
            ) {
                if(p1.body()!=null){
                    setListaActividadesOfertantesDeConsumidor(p1.body()!!)
                }
            }

            override fun onFailure(p0: Call<List<ActividadOfertante>>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    //endregion


    //endregion




}