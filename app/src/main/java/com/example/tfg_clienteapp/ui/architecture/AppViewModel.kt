package com.example.tfg_clienteapp.ui.architecture

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante
import com.example.tfg_clienteapp.model.Consumidor
import com.example.tfg_clienteapp.model.ConsumidorActividadFavorita
import com.example.tfg_clienteapp.model.ConsumidorActividadOfertante
import com.example.tfg_clienteapp.model.Ofertante
import com.example.tfg_clienteapp.model.OfertanteActividadFavorita
import com.example.tfg_clienteapp.retrofit.ActividadConsumidorAPI
import com.example.tfg_clienteapp.retrofit.ActividadOfertanteAPI
import com.example.tfg_clienteapp.retrofit.ConsumidorAPI
import com.example.tfg_clienteapp.retrofit.ConsumidorActividadFavoritaAPI
import com.example.tfg_clienteapp.retrofit.ConsumidorActividadOfertanteAPI
import com.example.tfg_clienteapp.retrofit.OfertanteAPI
import com.example.tfg_clienteapp.retrofit.OfertanteActividadFavoritaAPI
import com.example.tfg_clienteapp.retrofit.RetrofitService
import com.example.tfg_clienteapp.ui.data.Pantallas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AppViewModel(public val context: Context, public val navigator: NavHostController): ViewModel()  {

    //region VARIABLES VIEWMODEL

    private val _consumidorUiState: MutableStateFlow<ConsumidorUiState> = MutableStateFlow(ConsumidorUiState())
    val consumidorUiState: StateFlow<ConsumidorUiState> = _consumidorUiState.asStateFlow()

    private val _ofertanteUiSate: MutableStateFlow<OfertanteUiState> = MutableStateFlow(OfertanteUiState())
    val ofertanteUiState: StateFlow<OfertanteUiState> = _ofertanteUiSate.asStateFlow()

    private val _logeoUiState: MutableStateFlow<LogeoUiState> = MutableStateFlow(LogeoUiState())
    val logeoUiState: StateFlow<LogeoUiState> = _logeoUiState.asStateFlow()

    private val _actividadUiState: MutableStateFlow<ActividadUiState> = MutableStateFlow(ActividadUiState())
    val actividadUiState: StateFlow<ActividadUiState> = _actividadUiState.asStateFlow()

    //endregion

    //region RETROFIT SERVICE VARIABLES
    private val retrofitService:RetrofitService = RetrofitService()

    private val consumidorAPI: ConsumidorAPI = retrofitService.retrofit.create(ConsumidorAPI::class.java)

    private val ofertanteAPI: OfertanteAPI = retrofitService.retrofit.create(OfertanteAPI::class.java)

    private val actividadConsumidorAPI: ActividadConsumidorAPI = retrofitService.retrofit.create(ActividadConsumidorAPI::class.java)

    private val actividadOfertanteAPI: ActividadOfertanteAPI = retrofitService.retrofit.create(ActividadOfertanteAPI::class.java)

    private val consumidorActividadOfertanteAPI: ConsumidorActividadOfertanteAPI = retrofitService.retrofit.create(ConsumidorActividadOfertanteAPI::class.java)

    private val consumidorActividadFavoritaAPI: ConsumidorActividadFavoritaAPI = retrofitService.retrofit.create(ConsumidorActividadFavoritaAPI::class.java)

    private val ofertanteActividadFavoritaAPI: OfertanteActividadFavoritaAPI = retrofitService.retrofit.create(OfertanteActividadFavoritaAPI::class.java)

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
        return _consumidorUiState.value.listaActividadesApuntado;
    }

    fun getListaActividadesOfertanteDeConsumidor(): List<ActividadOfertante> {
        return _consumidorUiState.value.listaActividadesOfertantes;
    }

    fun getListaActividadesRecientesConsumidor():List<ActividadOfertante>{
        return _consumidorUiState.value.listaActividadesRecientes;
    }

    fun getListaActividadesFavoritasConsumidor():List<ActividadOfertante>{
        return  _consumidorUiState.value.listaActividadesFavoritasConsumidor;
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

    fun setListaActividadesRecientesConsumidor(lista: List<ActividadOfertante>){
        _consumidorUiState.update {
            it.copy(
                listaActividadesRecientes = lista
            )
        }
    }

    fun setListaActividadesFavoritasConsumidor(lista: List<ActividadOfertante>){
        _consumidorUiState.update {
            it.copy(
                listaActividadesFavoritasConsumidor = lista
            )
        }
    }



    fun addElementToListaActividadesRecientesConsumidor(actividad: ActividadOfertante){
        var lista = getListaActividadesRecientesConsumidor().toMutableList()

        if(!lista.contains(actividad)){
            lista.add(actividad)
            setListaActividadesRecientesConsumidor(lista)
        }
    }

    fun getBadgeListaMisActividadesConsumidor(): Int {
        return _consumidorUiState.value.badgeListaMisActividades;
    }

    fun getBadgeListaApuntadoConsumidor(): Int {
        return _consumidorUiState.value.badgeListaApuntado;
    }

    fun getBadgeListaTablonConsumidor(): Int {
        return _consumidorUiState.value.badgeListaTablon;
    }

    fun setBadgeListaMisActividadesConsumidor(valor: Int){
        _consumidorUiState.update {
            it.copy(
                badgeListaMisActividades = valor
            )
        }
    }

    fun setBadgeListaApuntadoConsumidor(valor: Int){
        _consumidorUiState.update {
            it.copy(
                badgeListaApuntado = valor
            )
        }
    }



    fun setBadgeListaTablonConsumidor(valor: Int){
        _consumidorUiState.update {
            it.copy(
                badgeListaTablon = valor
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

    fun getListaActividadesRecientesOfertante():List<ActividadConsumidor>{
        return _ofertanteUiSate.value.listaActividadesRecientes
    }

    fun setListaActividadesRecientesofertante(lista: List<ActividadConsumidor>){
        _ofertanteUiSate.update {
            it.copy(
                listaActividadesRecientes = lista
            )
        }
    }

    fun getListaActividadesFavoritasOfertante():List<ActividadConsumidor>{
        return  _ofertanteUiSate.value.listaActividadesFavoritasOfertante;
    }

    fun setListaActividadesFavoritasOfertante(lista: List<ActividadConsumidor>){
        _ofertanteUiSate.update {
            it.copy(
                listaActividadesFavoritasOfertante = lista
            )
        }
    }

    fun addElementToListaActividadesRecientesOfertante(actividad: ActividadConsumidor){
        var lista = getListaActividadesRecientesOfertante().toMutableList()
        lista.add(actividad)
        setListaActividadesRecientesofertante(lista)
    }

    fun getBadgeListaMisActividadesOfertante(): Int {
        return _ofertanteUiSate.value.badgeListaMisActividades;
    }

    fun getBadgeListaApuntadoOfertante(): Int {
        return _ofertanteUiSate.value.badgeListaApuntado;
    }

    fun getBadgeListaTablonOfertante(): Int {
        return _ofertanteUiSate.value.badgeListaTablon;
    }

    fun setBadgeListaMisActividadesOfertante(valor: Int){
        _ofertanteUiSate.update {
            it.copy(
                badgeListaMisActividades = valor
            )
        }
    }

    fun setBadgeListaApuntadoOfertante(valor: Int){
        _ofertanteUiSate.update {
            it.copy(
                badgeListaApuntado = valor
            )
        }
    }



    fun setBadgeListaTablonOfertante(valor: Int){
        _ofertanteUiSate.update {
            it.copy(
                badgeListaTablon = valor
            )
        }
    }

    //endregion

    //region Actividad

    fun getTitulo(): String {
        return _actividadUiState.value.titulo
    }

    fun setTitulo(valor: String){
        _actividadUiState.update {
            it.copy(
                titulo = valor
            )
        }
    }

    fun getDescripcion(): String {
        return _actividadUiState.value.descripcion
    }

    fun setDescripcion(valor: String){
        _actividadUiState.update {
            it.copy(
                descripcion = valor
            )
        }
    }

    fun getNumeroPlazas(): Int {
        return _actividadUiState.value.nPlazas
    }

    fun setNumeroPlazas(valor: Int){
        _actividadUiState.update {
            it.copy(
                nPlazas = valor
            )
        }
    }

    fun getCategoria(): String {
        return _actividadUiState.value.categoria
    }

    fun setCategoria(valor: String){
        _actividadUiState.update {
            it.copy(
                categoria = valor
            )
        }
    }

    fun getFecha(): String {
        return _actividadUiState.value.fecha
    }

    fun setFecha(valor: String){
        _actividadUiState.update {
            it.copy(
                fecha = valor
            )
        }
    }



    fun getTituloValido(): Boolean{
        return _actividadUiState.value.tituloValido
    }

    fun setTituloValido(valor: Boolean){
        _actividadUiState.update {
            it.copy(
                tituloValido = valor
            )
        }
    }

    fun getDescripcionValido(): Boolean{
        return _actividadUiState.value.descripcionValido
    }

    fun setDescripcionValido(valor: Boolean){
        _actividadUiState.update {
            it.copy(
                descripcionValido = valor
            )
        }
    }

    fun getNPlazasValido(): Boolean{
        return _actividadUiState.value.nPlazasValido
    }

    fun setNPlazasValido(valor: Boolean){
        _actividadUiState.update {
            it.copy(
                nPlazasValido = valor
            )
        }
    }

    fun getCategoriaValido(): Boolean{
        return _actividadUiState.value.categoriaValido
    }

    fun setCategoriaValido(valor: Boolean){
        _actividadUiState.update {
            it.copy(
                categoriaValido = valor
            )
        }
    }

    fun getFechaValido(): Boolean{
        return _actividadUiState.value.fechaValido
    }

    fun setFechaValido(valor: Boolean){
        _actividadUiState.update {
            it.copy(
                fechaValido = valor
            )
        }
    }

    fun isTituloValido(titulo:String):Boolean{
        return titulo!=""
    }

    fun isDescripcionValido(descripcion:String):Boolean{
        return descripcion!=""
    }

    fun isNPlazasValido(nPlazasPrevias: Int = -1,nPlazas: Int):Boolean{
        var tieneDecimales: Boolean = false
        if(nPlazasPrevias!=-1){

        }
        var numberString = nPlazas.toString()


        for(c in numberString.toCharArray()){
            if(c=='.' || c==',')tieneDecimales=true
        }
        return numberString.length<=2 || !tieneDecimales
    }

    fun isCategoriaValido(categoria: String):Boolean{
        return categoria!=""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isFechaValido(fecha: String): Boolean {
        if (fecha.isEmpty()) {
            return false
        }
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        try {
            val fechaIngresada = LocalDate.parse(fecha, formatter)
            val fechaActual = LocalDate.now()
            return !fechaIngresada.isBefore(fechaActual)
        } catch (e: Exception) {
            return false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadPUTActividadOfertante(actividad: ActividadOfertante){
        _actividadUiState.update {
            it.copy(
                titulo = actividad.titulo,
                descripcion = actividad.descripcion,
                nPlazas = actividad.numeroPlazas,
                categoria = actividad.categoria,
                fecha = convertDateToString(actividad.dueDate),
                tituloValido = true,
                descripcionValido = true,
                nPlazasValido = true,
                categoriaValido = true,
                tipoRegistro = "PUT",
                idActividad = actividad.idActividadOfertante
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadPUTActividadConsumidor(actividad: ActividadConsumidor){
        _actividadUiState.update {
            it.copy(
                titulo = actividad.titulo,
                descripcion = actividad.descripcion,
                categoria = actividad.categoria,
                fecha = convertDateToString(actividad.dueDate),
                tituloValido = true,
                descripcionValido = true,
                categoriaValido = true,
                tipoRegistro = "PUT",
                idActividad = actividad.idActividadConsumidor
            )
        }
    }


    fun registrarActividadValido():Boolean{
        return if(_logeoUiState.value.tipoUsuario=="Consumidor"){
            (getTituloValido()
                    && getDescripcionValido()
                    && getCategoriaValido()
                    &&getFechaValido())
        }else{
            (getTituloValido()
                    && getDescripcionValido()
                    && getNPlazasValido()
                    && getCategoriaValido()
                    && getFechaValido())
        }

    }

    fun setActividadUiStateEmpty(){
        _actividadUiState.update {
            it.copy(
                titulo = "",
                descripcion = "",
                nPlazas = 0,
                categoria = "",
                fecha = "",
                tituloValido = false,
                descripcionValido = false,
                nPlazasValido = false,
                categoriaValido = false,
                fechaValido = false
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
                    setIdUser(p1.body()!!.idConsumidor)
                    navigator.navigate(Pantallas.PantallaMenuPrincipalConsumidor.name)
                    actualizarListasConsumidor()
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
                    setIdUser(p1.body()!!.idOfertante)
                    navigator.navigate(Pantallas.PantallaMenuPrincipalOfertante.name)
                    actualizarListasOfertante()
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
                    actualizarListasConsumidor()
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
                    navigator.navigate(Pantallas.PantallaMenuPrincipalOfertante.name)
                    actualizarListasOfertante()
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
                    setBadgeListaTablonConsumidor(p1.body()!!.size)
                    Toast.makeText(context,"Lista actualizada", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<List<ActividadOfertante>>, p1: Throwable) {
                Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun actualizarMisActividadesConsumidor(){
        actividadConsumidorAPI.getActividadesConsumidoresByConsumidor(_logeoUiState.value.idUsuario).enqueue(object: Callback<List<ActividadConsumidor>>{
            override fun onResponse(
                p0: Call<List<ActividadConsumidor>>,
                p1: Response<List<ActividadConsumidor>>
            ) {
                if(p1.body()!=null){
                    setListaMisActividadesConsumidor(p1.body()!!)
                    setBadgeListaMisActividadesConsumidor(p1.body()!!.size)
                    Toast.makeText(context,"Lista actualizada", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<List<ActividadConsumidor>>, p1: Throwable) {
                Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun actualizarActividadesApuntadoConsumidor(){
        actividadOfertanteAPI.getActividadesOfertantesByConsumidor(_logeoUiState.value.idUsuario)
            .enqueue(object: Callback<List<ActividadOfertante>>{
                override fun onResponse(
                    p0: Call<List<ActividadOfertante>>,
                    p1: Response<List<ActividadOfertante>>
                ) {
                    if(p1.body()!=null){
                        setListaActividadesApuntadoConsumidor(p1.body()!!)
                        setBadgeListaApuntadoConsumidor(p1.body()!!.size)
                        Toast.makeText(context,"Lista actualizada", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(p0: Call<List<ActividadOfertante>>, p1: Throwable) {
                    Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun actualizarActividadesFavoritasConsumidor(){
        actividadOfertanteAPI.getActividadesFavoritasByConsumidor(_logeoUiState.value.idUsuario)
            .enqueue(object: Callback<List<ActividadOfertante>>{
                override fun onResponse(
                    p0: Call<List<ActividadOfertante>>,
                    p1: Response<List<ActividadOfertante>>
                ) {
                    if(p1.body()!=null){
                        setListaActividadesFavoritasConsumidor(p1.body()!!)
                        Toast.makeText(context,"Lista actualizada", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(p0: Call<List<ActividadOfertante>>, p1: Throwable) {
                    Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                }

            })
    }


    fun solapamientoActividadesConsumidorPorFecha(idActividad: Int = 0,fecha: Date): Boolean{
        for(actividad in getListaActividadesApuntadoConsumidor()){
            if(actividad.dueDate.equals(fecha))return true
        }
        for(actividad in getListaMisActividadesConsumidor()){
            if(actividad.idActividadConsumidor==idActividad){
                continue
            }
            if(actividad.dueDate.equals(fecha))return true
        }
        return false
    }

    //endregion


    //region OFERTANTE

    fun actualizarActividadesConsumidoresDeOfertantes(){
        actividadConsumidorAPI.allActividadesConsumidores.enqueue(object: Callback<List<ActividadConsumidor>>{
            override fun onResponse(
                p0: Call<List<ActividadConsumidor>>,
                p1: Response<List<ActividadConsumidor>>
            ) {
                if(p1.body()!=null){
                    setListaActividadesConsumidoresDeOfertante(p1.body()!!)
                    setBadgeListaTablonOfertante(p1.body()!!.size)
                    Toast.makeText(context,"Lista actualizada", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                }            }

            override fun onFailure(p0: Call<List<ActividadConsumidor>>, p1: Throwable) {
                Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun actualizarMisActividadesOfertante(){
        actividadOfertanteAPI.getActividadesOfertanteByOfertante(_logeoUiState.value.idUsuario)
            .enqueue(object: Callback<List<ActividadOfertante>>{
                override fun onResponse(
                    p0: Call<List<ActividadOfertante>>,
                    p1: Response<List<ActividadOfertante>>
                ) {
                    if(p1.body()!=null){
                        setListaMisActividadesOfertante(p1.body()!!)
                        setBadgeListaMisActividadesOfertante(p1.body()!!.size)
                        Toast.makeText(context,"Lista actualizada", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(p0: Call<List<ActividadOfertante>>, p1: Throwable) {
                    Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun actualizarActividadesApuntadoOfertante(){
        actividadConsumidorAPI.getActividadesConsumidoresByOfertante(_logeoUiState.value.idUsuario)
            .enqueue(object: Callback<List<ActividadConsumidor>>{
                override fun onResponse(
                    p0: Call<List<ActividadConsumidor>>,
                    p1: Response<List<ActividadConsumidor>>
                ) {
                    if(p1.body()!=null){
                        setListaActividadesApuntadoOfertante(p1.body()!!)
                        setBadgeListaApuntadoOfertante(p1.body()!!.size)
                        Toast.makeText(context,"Lista actualizada", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(p0: Call<List<ActividadConsumidor>>, p1: Throwable) {
                    Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                }

            })

    }

    fun actualizarActividadesFavoritasOfertante(){
        actividadConsumidorAPI.getActividadesFavoritasByOfertante(_logeoUiState.value.idUsuario)
            .enqueue(object: Callback<List<ActividadConsumidor>>{
                override fun onResponse(
                    p0: Call<List<ActividadConsumidor>>,
                    p1: Response<List<ActividadConsumidor>>
                ) {
                    if(p1.body()!=null){
                        setListaActividadesFavoritasOfertante(p1.body()!!)
                        Toast.makeText(context,"Lista actualizada", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(p0: Call<List<ActividadConsumidor>>, p1: Throwable) {
                    Toast.makeText(context,"Error al actualizar", Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun solapamientoActividadesOfertantePorFecha(idActividad: Int = 0,fecha: Date):Boolean{
        for(actividad in getListaActividadesApuntadoOfertante()){

            if(actividad.dueDate.equals(fecha))return true
        }
        for(actividad in getListaMisActividadesOfertante()){
            if(actividad.idActividadOfertante==idActividad){
                continue
            }
            if(actividad.dueDate.equals(fecha))return true
        }
        return false
    }

    //endregion



    //region ACTIVIDADES
    @RequiresApi(Build.VERSION_CODES.O)
    fun postActividadConsumidor(){
        val actividad = ActividadConsumidor(actividadUiState.value.titulo,
            actividadUiState.value.descripcion,
            convertStringToDate(actividadUiState.value.fecha),
            actividadUiState.value.categoria,
            Consumidor(logeoUiState.value.idUsuario)
        )
        if(solapamientoActividadesConsumidorPorFecha(fecha = actividad.dueDate)){
            Toast.makeText(context,"Tienes una actividad ese dia", Toast.LENGTH_LONG).show()
        }else{
            actividadConsumidorAPI.save(actividad).enqueue(object: Callback<ActividadConsumidor>{
                override fun onResponse(
                    p0: Call<ActividadConsumidor>,
                    p1: Response<ActividadConsumidor>
                ) {
                    if(p1.body()!=null){
                        actividadConsumidorAPI.getActividadesConsumidoresByConsumidor(logeoUiState.value.idUsuario).enqueue(object: Callback<List<ActividadConsumidor>>{
                            override fun onResponse(
                                p0: Call<List<ActividadConsumidor>>,
                                p1: Response<List<ActividadConsumidor>>
                            ) {
                                if(p1.body()!=null){
                                    setListaMisActividadesConsumidor(p1.body()!!)
                                }
                            }

                            override fun onFailure(p0: Call<List<ActividadConsumidor>>, p1: Throwable) {
                                Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                            }

                        })
                        Toast.makeText(context,"Actividad añadida", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(p0: Call<ActividadConsumidor>, p1: Throwable) {
                    Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                }

            })
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateActividadConsumidor(){
        val actividad = ActividadConsumidor(actividadUiState.value.titulo,
            actividadUiState.value.descripcion,
            convertStringToDate(actividadUiState.value.fecha),
            actividadUiState.value.categoria,
            Consumidor(logeoUiState.value.idUsuario)
        )
        if(solapamientoActividadesConsumidorPorFecha(_actividadUiState.value.idActividad,fecha = actividad.dueDate)){
            Toast.makeText(context,"Tienes una actividad ese dia", Toast.LENGTH_LONG).show()
        }else{
            actividadConsumidorAPI.update(_actividadUiState.value.idActividad, actividad).enqueue(object: Callback<ActividadConsumidor>{
                override fun onResponse(
                    p0: Call<ActividadConsumidor>,
                    p1: Response<ActividadConsumidor>
                ) {
                    if(p1.body()!=null){
                        actividadConsumidorAPI.getActividadesConsumidoresByConsumidor(logeoUiState.value.idUsuario).enqueue(object: Callback<List<ActividadConsumidor>>{
                            override fun onResponse(
                                p0: Call<List<ActividadConsumidor>>,
                                p1: Response<List<ActividadConsumidor>>
                            ) {
                                if(p1.body()!=null){
                                    setListaMisActividadesConsumidor(p1.body()!!)
                                }
                            }

                            override fun onFailure(p0: Call<List<ActividadConsumidor>>, p1: Throwable) {
                                Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                            }

                        })
                        Toast.makeText(context,"Actividad modificada", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(p0: Call<ActividadConsumidor>, p1: Throwable) {
                    Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                }

            })
        }

    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun postActividadOfertante(){

        val actividad = ActividadOfertante()
        val ofertante = Ofertante()
        ofertante.idOfertante = logeoUiState.value.idUsuario
        actividad.ofertante = ofertante
        actividad.titulo = actividadUiState.value.titulo
        actividad.descripcion = actividadUiState.value.descripcion
        actividad.numeroPlazas = actividadUiState.value.nPlazas
        actividad.dueDate = convertStringToDate(actividadUiState.value.fecha)
        actividad.categoria = actividadUiState.value.categoria
        if(solapamientoActividadesOfertantePorFecha(fecha = actividad.dueDate)){
            Toast.makeText(context,"Tienes una actividad ese dia", Toast.LENGTH_LONG).show()
        }else{
            actividadOfertanteAPI.save(actividad).enqueue(object: Callback<ActividadOfertante>{
                override fun onResponse(
                    p0: Call<ActividadOfertante>,
                    p1: Response<ActividadOfertante>
                ) {
                    if(p1.body()!=null){
                        actividadOfertanteAPI.getActividadesOfertanteByOfertante(logeoUiState.value.idUsuario).enqueue(object: Callback<List<ActividadOfertante>>{
                            override fun onResponse(
                                p0: Call<List<ActividadOfertante>>,
                                p1: Response<List<ActividadOfertante>>
                            ) {
                                if(p1.body()!=null){
                                    setListaMisActividadesOfertante(p1.body()!!)
                                }
                            }

                            override fun onFailure(p0: Call<List<ActividadOfertante>>, p1: Throwable) {
                                Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                            }

                        })
                        Toast.makeText(context,"Actividad añadida", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(p0: Call<ActividadOfertante>, p1: Throwable) {
                    Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                }

            })
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateActividadOfertante(){
        val actividad = ActividadOfertante()
        val ofertante = Ofertante()
        ofertante.idOfertante = logeoUiState.value.idUsuario
        actividad.ofertante = ofertante
        actividad.titulo = actividadUiState.value.titulo
        actividad.descripcion = actividadUiState.value.descripcion
        actividad.numeroPlazas = actividadUiState.value.nPlazas
        actividad.dueDate = convertStringToDate(actividadUiState.value.fecha)
        actividad.categoria = actividadUiState.value.categoria
        if(solapamientoActividadesOfertantePorFecha(_actividadUiState.value.idActividad,fecha = actividad.dueDate)){
            Toast.makeText(context,"Tienes una actividad ese dia", Toast.LENGTH_LONG).show()
        }else{
            actividadOfertanteAPI.update(_actividadUiState.value.idActividad,actividad).enqueue(object: Callback<ActividadOfertante>{
                override fun onResponse(
                    p0: Call<ActividadOfertante>,
                    p1: Response<ActividadOfertante>
                ) {
                    if(p1.body()!=null){
                        actividadOfertanteAPI.getActividadesOfertanteByOfertante(logeoUiState.value.idUsuario).enqueue(object: Callback<List<ActividadOfertante>>{
                            override fun onResponse(
                                p0: Call<List<ActividadOfertante>>,
                                p1: Response<List<ActividadOfertante>>
                            ) {
                                if(p1.body()!=null){
                                    setListaMisActividadesOfertante(p1.body()!!)
                                }
                            }

                            override fun onFailure(p0: Call<List<ActividadOfertante>>, p1: Throwable) {
                                Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                            }

                        })
                        Toast.makeText(context,"Actividad modificada", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(p0: Call<ActividadOfertante>, p1: Throwable) {
                    Toast.makeText(context,"Error registro", Toast.LENGTH_LONG).show()
                }

            })
        }

    }

    fun apuntarConsumidorAActividadOfertante(actividad: ActividadOfertante){
        if(estaApuntadoConsumidor(actividad.idActividadOfertante)){
            Toast.makeText(context,"Ya estas apuntado", Toast.LENGTH_LONG).show()
        }else{
            if(actividad.numeroPlazas<=actividad.listaConsumidoresActividadOfertantes.size){
                Toast.makeText(context,"No quedan plazas", Toast.LENGTH_LONG).show()
            }else{
                var consumidorActividadOfertante = ConsumidorActividadOfertante()
                var actividadOfertante = ActividadOfertante()
                actividadOfertante.idActividadOfertante = actividad.idActividadOfertante
                consumidorActividadOfertante.actividadOfertante = actividadOfertante
                var consumidor = Consumidor()
                consumidor.idConsumidor = _logeoUiState.value.idUsuario
                consumidorActividadOfertante.consumidor = consumidor
                consumidorActividadOfertanteAPI.save(consumidorActividadOfertante).enqueue(object: Callback<ConsumidorActividadOfertante>{
                    override fun onResponse(
                        p0: Call<ConsumidorActividadOfertante>,
                        p1: Response<ConsumidorActividadOfertante>
                    ) {
                        if(p1.body()!=null){
                            actualizarActividadesApuntadoConsumidor()
                            actualizarActividadesOfertantesDeConsumidores()
                            actualizarActividadesFavoritasConsumidor()
                        }else{
                            Toast.makeText(context,"Error al apuntarse", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(p0: Call<ConsumidorActividadOfertante>, p1: Throwable) {
                        Toast.makeText(context,"Error al apuntarse", Toast.LENGTH_LONG).show()
                    }

                })
            }

        }

    }

    fun añadirFavoritaConsumidor(idActividad: Int){
        if(esFavoritaOfertante(idActividad)){
            Toast.makeText(context, "Ya se añadio", Toast.LENGTH_LONG).show()
        }else{
            var c = ConsumidorActividadFavorita()
            var consumidor = Consumidor()
            consumidor.idConsumidor = _logeoUiState.value.idUsuario
            var actividadOfertante = ActividadOfertante()
            actividadOfertante.idActividadOfertante = idActividad
            c.consumidor = consumidor
            c.actividadOfertante = actividadOfertante
            consumidorActividadFavoritaAPI.save(c).enqueue(object: Callback<ConsumidorActividadFavorita>{
                override fun onResponse(
                    p0: Call<ConsumidorActividadFavorita>,
                    p1: Response<ConsumidorActividadFavorita>
                ) {
                    if(p1.body()!=null){
                        actualizarActividadesApuntadoConsumidor()
                        actualizarActividadesOfertantesDeConsumidores()
                        actualizarActividadesFavoritasConsumidor()
                    }else{
                        Toast.makeText(context,"Error al añadirla", Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(p0: Call<ConsumidorActividadFavorita>, p1: Throwable) {
                    Toast.makeText(context,"Error al añadirla", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    fun borrarFavoritaConsumidor(idActividad: Int){
        if(!esFavoritaConsumidor(idActividad)){
            Toast.makeText(context, "Ya se borro de favoritos", Toast.LENGTH_LONG).show()
        }else{
            consumidorActividadFavoritaAPI.deleteByIds(idActividad,_logeoUiState.value.idUsuario)
                .enqueue(object:Callback<Void>{
                    override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
                        if(p1.isSuccessful){
                            actualizarActividadesApuntadoConsumidor()
                            actualizarActividadesOfertantesDeConsumidores()
                            actualizarActividadesFavoritasConsumidor()
                        }else{
                            Toast.makeText(context, "Error al borrarla", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(p0: Call<Void>, p1: Throwable) {
                        Toast.makeText(context, "Error al borrarla", Toast.LENGTH_LONG).show()

                    }

                })
        }
    }
    fun esFavoritaConsumidor(idActividad: Int):Boolean{
        for(actividad in getListaActividadesFavoritasConsumidor()){
            if(actividad.idActividadOfertante==idActividad)return true;
        }
        return false;
    }


    fun estaApuntadoConsumidor(idActividad: Int):Boolean{

        for(actividad in getListaActividadesApuntadoConsumidor()){
            if(actividad.idActividadOfertante == idActividad)return true
        }
        return false
    }

    fun desapuntarseActividadApuntadoConsumidor(idActividad: Int){
        if(!estaApuntadoConsumidor(idActividad)){
            Toast.makeText(context, "Ya te borraste", Toast.LENGTH_LONG).show()
        }else {
            consumidorActividadOfertanteAPI.deleteByIds(idActividad, _logeoUiState.value.idUsuario)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
                        if (p1.isSuccessful) {
                            actualizarActividadesApuntadoConsumidor()
                            actualizarActividadesOfertantesDeConsumidores()
                            actualizarActividadesFavoritasConsumidor()
                        } else {
                            Toast.makeText(context, "Error al desapuntarse", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(p0: Call<Void>, p1: Throwable) {
                        Toast.makeText(context, "Error al desapuntarse", Toast.LENGTH_LONG).show()
                    }

            })
        }
    }

    fun borrarActividadConsumidor(idActividad: Int){
        actividadConsumidorAPI.deleteById(idActividad).enqueue(object: Callback<Void>{
            override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
                if(p1.isSuccessful){
                    actualizarMisActividadesConsumidor()
                }else{
                    Toast.makeText(context, "Error al borrar", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<Void>, p1: Throwable) {
                Toast.makeText(context, "Error al borrar", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun apuntarOfertanteAActividadConsumidor(actividad: ActividadConsumidor){
        if(estaApuntadoOfertante(actividad.idActividadConsumidor)){
            Toast.makeText(context, "Ya esta apuntado", Toast.LENGTH_LONG).show()
        }else{
            if(actividad.ofertanteActividadConsumidor != null){
                Toast.makeText(context, "Ya tiene un ofertante", Toast.LENGTH_LONG).show()
            }else{
                actividadConsumidorAPI.updateOfertante(actividad.idActividadConsumidor,_logeoUiState.value.idUsuario)
                    .enqueue(object: Callback<ActividadConsumidor>{
                        override fun onResponse(
                            p0: Call<ActividadConsumidor>,
                            p1: Response<ActividadConsumidor>
                        ) {
                            if(p1.body()!=null){
                                actualizarActividadesApuntadoOfertante()
                                actualizarActividadesConsumidoresDeOfertantes()
                                actualizarActividadesFavoritasOfertante()
                            }else{
                                Toast.makeText(context, "Error al apuntarse", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(p0: Call<ActividadConsumidor>, p1: Throwable) {
                            Toast.makeText(context, "Error al apuntarse", Toast.LENGTH_LONG).show()
                        }

                    })
            }
        }
    }

    fun añadirFavoritaOfertante(idActividad: Int){
        if(esFavoritaOfertante(idActividad)){
            Toast.makeText(context, "Ya se añadio", Toast.LENGTH_LONG).show()
        }else{
            var o = OfertanteActividadFavorita()
            var ofertante = Ofertante()
            var actividadConsumidor = ActividadConsumidor()
            ofertante.idOfertante = _logeoUiState.value.idUsuario
            actividadConsumidor.idActividadConsumidor = idActividad
            o.ofertante = ofertante
            o.actividadConsumidor = actividadConsumidor
            ofertanteActividadFavoritaAPI.save(o).enqueue(object: Callback<OfertanteActividadFavorita>{
                override fun onResponse(
                    p0: Call<OfertanteActividadFavorita>,
                    p1: Response<OfertanteActividadFavorita>
                ) {
                    if(p1.body()!=null){
                        actualizarActividadesApuntadoOfertante()
                        actualizarActividadesConsumidoresDeOfertantes()
                        actualizarActividadesFavoritasOfertante()
                    }else{
                        Toast.makeText(context,"Error al añadirla", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(p0: Call<OfertanteActividadFavorita>, p1: Throwable) {
                    Toast.makeText(context,"Error al añadirla", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    fun desapuntarseActividadApuntadoOfertante(idActividad: Int){
        if(!estaApuntadoOfertante(idActividad)){
            Toast.makeText(context, "Ya se ha borrado", Toast.LENGTH_LONG).show()
        }else{
            actividadConsumidorAPI.deleteOfertante(idActividad).enqueue(object: Callback<ActividadConsumidor>{
                override fun onResponse(
                    p0: Call<ActividadConsumidor>,
                    p1: Response<ActividadConsumidor>
                ) {
                    if(p1.body()!=null){
                        actualizarActividadesApuntadoOfertante()
                        actualizarActividadesConsumidoresDeOfertantes()
                        actualizarActividadesFavoritasOfertante()
                    }else{
                        Toast.makeText(context, "Error al desapuntarse", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(p0: Call<ActividadConsumidor>, p1: Throwable) {
                    Toast.makeText(context, "Error al desapuntarse", Toast.LENGTH_LONG).show()
                }

            })
        }
    }




    fun esFavoritaOfertante(idActividad: Int):Boolean{
        for(actividad in getListaActividadesFavoritasOfertante()){
            if(actividad.idActividadConsumidor==idActividad)return true;
        }
        return false;
    }

    fun estaApuntadoOfertante(idActividad: Int):Boolean{

        for(actividad in getListaActividadesApuntadoOfertante()){
            if(actividad.idActividadConsumidor == idActividad)return true
        }
        return false
    }

    fun borrarActividadOfertante(idActividad: Int){
        actividadOfertanteAPI.deleteById(idActividad).enqueue(object: Callback<Void>{
            override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
                if(p1.isSuccessful){
                    actualizarMisActividadesOfertante()
                }else{
                    Toast.makeText(context, "Error al borrar", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<Void>, p1: Throwable) {
                Toast.makeText(context, "Error al borrar", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun borrarFavoritaOfertante(idActividad: Int){
        if(!esFavoritaOfertante(idActividad)){
            Toast.makeText(context, "Ya se borro de favoritos", Toast.LENGTH_LONG).show()
        }else {
            ofertanteActividadFavoritaAPI.deleteByIds(idActividad, _logeoUiState.value.idUsuario)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
                        if (p1.isSuccessful) {
                            actualizarActividadesApuntadoOfertante()
                            actualizarActividadesConsumidoresDeOfertantes()
                            actualizarActividadesFavoritasOfertante()
                        } else {
                            Toast.makeText(context, "Error al borrarla", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(p0: Call<Void>, p1: Throwable) {
                        Toast.makeText(context, "Error al borrarla", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }




    //endregion

    //region OTROS


    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDateToString(date: Date): String {
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return localDate.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertStringToDate(dateString: String): Date {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val localDate = LocalDate.parse(dateString, formatter)
        return Date.valueOf(localDate.toString())
    }

    public fun resetLogeoData(){
        _logeoUiState.update {
            it.copy(
                idUsuario = 0,
                tipoUsuario = "",
                nombreUsuario = "",
                email = "",
                password = "",
                emailValido = true,
                passwordValido = true,
                userValido = true
            )
        }

    }

    public fun resetActividadData(){
        _actividadUiState.update {
            it.copy(
                titulo = "",
                descripcion = "",
                nPlazas = 0,
                categoria  = "",
                fecha = "",
                tituloValido = false,
                descripcionValido  = false,
                nPlazasValido  = false,
                categoriaValido = false,
                fechaValido = false,
                tipoRegistro = "POST",
                idActividad = 0
            )
        }
    }

    public fun resetConsumidorData(){
        _consumidorUiState.update {
            it.copy(
                listaMisActividades = arrayListOf(),
                listaActividadesApuntado = arrayListOf(),
                listaActividadesOfertantes = arrayListOf(),
                listaActividadesRecientes = arrayListOf(),
                listaActividadesFavoritasConsumidor = arrayListOf(),
                badgeListaMisActividades = _consumidorUiState.value.listaMisActividades.size,
                badgeListaApuntado = _consumidorUiState.value.listaActividadesApuntado.size,
                badgeListaTablon = _consumidorUiState.value.listaActividadesOfertantes.size
            )
        }
    }

    public fun resetOfertanteData(){
        _ofertanteUiSate.update {
            it.copy(
                listaMisActividades = arrayListOf(),
                listaActividadesApuntado = arrayListOf(),
                listaActividadesAConsumidores = arrayListOf(),
                listaActividadesRecientes = arrayListOf(),
                listaActividadesFavoritasOfertante = arrayListOf(),
                badgeListaMisActividades = _ofertanteUiSate.value.listaMisActividades.size,
                badgeListaApuntado = _ofertanteUiSate.value.listaActividadesApuntado.size,
                badgeListaTablon = _ofertanteUiSate.value.listaActividadesAConsumidores.size
            )
        }
    }

    public fun resetAllUiStates(){
        resetLogeoData()
        resetActividadData()
        resetConsumidorData()
        resetOfertanteData()
    }

    fun actualizarListasConsumidor(){
        actualizarActividadesApuntadoConsumidor()
        actualizarActividadesOfertantesDeConsumidores()
        actualizarMisActividadesConsumidor()
        actualizarActividadesFavoritasConsumidor()
    }

    fun actualizarListasOfertante(){
        actualizarMisActividadesOfertante()
        actualizarActividadesApuntadoOfertante()
        actualizarActividadesConsumidoresDeOfertantes()
        actualizarActividadesFavoritasOfertante()
    }


    //endregion

    //endregion




}