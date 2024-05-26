package com.example.tfg_clienteapp.ui.componentes

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.model.ActividadConsumidor
import com.example.tfg_clienteapp.model.ActividadOfertante
import com.example.tfg_clienteapp.model.ConsumidorActividadOfertante
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.theme.*


//region Textos
@Composable
fun TextoNormal(value: String,modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text= value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            color = ColorTexto
        ),
        textAlign = textAlign
    )
}

@Composable
fun CabeceraTextoNormal(value: String, modifier: Modifier = Modifier) {
    Text(
        text= value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = ColorTexto
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextoClicableTerminosPolitica(
    politicaMostrado: MutableState<Boolean>,
    terminosMostrado: MutableState<Boolean>
) {
    val textoInicial = "Al continuar aceptas nuestra "
    val textoPoliticaPrivacidad = "Política de privacidad"
    val textoConsecuente = " y "
    val terminosYCondicionesTexto = "Términos de Uso"

    val annotatedString = buildAnnotatedString {
        append(textoInicial)
        append(TextoClicable(texto = textoPoliticaPrivacidad))
        append(textoConsecuente)
        append(TextoClicable(texto = terminosYCondicionesTexto))
    }


    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                if (span.item == "Términos de Uso") {
                    terminosMostrado.value = true
                }
                if (span.item == "Política de privacidad") {
                    politicaMostrado.value = true
                }
            }

    })
}

@Composable
fun TextoClicable(texto: String): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Intenso2)) {
            pushStringAnnotation(tag = texto, annotation = texto)
            append(texto)
        }
    }

    return annotatedString
}

@Composable
fun TextoCambiarTipoRegistro(
    textoNormal: String,
    textoEnlace: String,
    accionEnlace: () -> Unit
) {

    var annotatedString = buildAnnotatedString {
        append(textoEnlace)
        append(TextoClicable(texto = textoNormal))
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if (span.item == textoNormal) {
                        accionEnlace()
                    }

                }

        },

        )
}

@Composable
fun CuerpoPoliticaPrivacidad() {
    TextoNormal(
        value = "Esta Política de Privacidad describe cómo " +
                "Market Onuba recopila, utiliza y " +
                "comparte información personal de los usuarios de nuestra aplicación " +
                "móvil/sitio web/servicio (en adelante, \"Servicio\"). Al utilizar " +
                "nuestro Servicio, aceptas las prácticas descritas en esta Política de Privacidad.",
        textAlign = TextAlign.Justify
    )
    CabeceraTextoNormal(value = "Información Recopilada")
    TextoNormal(
        "Podemos recopilar información personal identificable, " +
                "como tu nombre, dirección de correo electrónico, dirección postal, " +
                "número de teléfono, información de pago, y cualquier otra información " +
                "que nos proporciones al utilizar nuestro Servicio. También podemos " +
                "recopilar información no identificable, como tu dirección IP, tipo de " +
                "navegador, proveedor de servicios de Internet, páginas de referencia/salida " +
                "y la fecha/hora de acceso",
        textAlign = TextAlign.Justify
    )
    CabeceraTextoNormal(value = "Uso de la Información")
    TextoNormal(
        value = "Podemos recopilar información personal identificable, " +
                "como tu nombre, dirección de correo electrónico, dirección postal, " +
                "número de teléfono, información de pago, y cualquier otra información " +
                "que nos proporciones al utilizar nuestro Servicio. También podemos " +
                "recopilar información no identificable, como tu dirección IP, " +
                "tipo de navegador, proveedor de servicios de Internet, páginas de " +
                "referencia/salida y la fecha/hora de acceso.",
        textAlign = TextAlign.Justify
    )
    CabeceraTextoNormal(value = "Seguridad de la Información")
    TextoNormal(
        "Nos esforzamos por proteger la seguridad de tu " +
                "información personal y tomamos medidas razonables para " +
                "evitar el acceso no autorizado, el uso, la divulgación o " +
                "la alteración de tu información. Sin embargo, ninguna transmisión de " +
                "datos por Internet o método de almacenamiento electrónico es 100% " +
                "seguro, por lo que no podemos garantizar su seguridad absoluta.",
        textAlign = TextAlign.Justify
    )
}

@Composable
fun CuerpoTerminosUso() {
    CabeceraTextoNormal(value = "INFORMACIÓN RELEVANTE")
    TextoNormal(
        "Es requisito necesario para la adquisición de " +
                "los productos que se ofrecen en este sitio, que lea y " +
                "acepte los siguientes Términos y Condiciones que a " +
                "continuación se redactan. El uso de nuestros servicios " +
                "así como la compra de nuestros productos implicará que " +
                "usted ha leído y aceptado los Términos y Condiciones " +
                "de Uso en el presente documento. Todas los productos  " +
                "que son ofrecidos por nuestro sitio web pudieran ser creadas, " +
                "cobradas, enviadas o presentadas por una página web tercera y " +
                "en tal caso estarían sujetas a sus propios Términos y Condiciones. " +
                "En algunos casos, para adquirir un producto, será necesario el " +
                "registro por parte del usuario, con ingreso de datos personales " +
                "fidedignos y definición de una contraseña.",
        textAlign = TextAlign.Justify
    )
    CabeceraTextoNormal(value = "USO NO AUTORIZADO")
    TextoNormal(
        "En caso de que aplique (para venta de software, templetes, " +
                "u otro producto de diseño y programación) usted no puede " +
                "colocar uno de nuestros productos, modificado o sin modificar, " +
                "en un CD, sitio web o ningún otro medio y ofrecerlos para la redistribución " +
                "o la reventa de ningún tipo.",
        textAlign = TextAlign.Justify
    )
    CabeceraTextoNormal(value = "PROPIEDAD")
    TextoNormal(
        "Usted no puede declarar propiedad intelectual o exclusiva a " +
                "ninguno de nuestros productos, modificado o sin modificar. Todos " +
                "los productos son propiedad  de los proveedores del contenido. " +
                "En caso de que no se especifique lo contrario, nuestros productos " +
                "se proporcionan  sin ningún tipo de garantía, expresa o implícita. " +
                "En ningún esta compañía será  responsables de ningún daño incluyendo, " +
                "pero no limitado a, daños directos, indirectos, especiales, fortuitos o " +
                "consecuentes u otras pérdidas resultantes del uso o de la imposibilidad de " +
                "utilizar nuestros productos.",
        textAlign = TextAlign.Justify
    )
}

//endregion

//region TextFields
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTextoUser(
    viewmodel: AppViewModel,
    variableRemember: String,
    textoLabel: String,
    @DrawableRes icono: Int,
    userValido: Boolean
    ){


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = {Text(text = textoLabel, style = TextStyle(Color.Black))},
        colors = TextFieldDefaults.outlinedTextFieldColors(
           focusedBorderColor = Intenso2,
            focusedLabelColor = Intenso2,
            cursorColor = Intenso2,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = variableRemember,
        onValueChange = {
            viewmodel.setUser(it)
            viewmodel.setUserValido(viewmodel.isValidUser(it))
        },
        leadingIcon = {
            Icon(painter = painterResource(id = icono),
                contentDescription = "",
                modifier = Modifier.sizeIn(maxHeight = 40.dp))
        },
        isError = !userValido
    )
    if(!userValido){
        Text("El usuario debe tener entre 5 y 30 caracteres")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTextoEmail(
    viewmodel: AppViewModel,
    variableRemember: String,
    textoLabel: String, @DrawableRes icono: Int,
    emailValido: Boolean

){


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = {Text(text = textoLabel, style = TextStyle(Color.Black))},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Intenso2,
            focusedLabelColor = Intenso2,
            cursorColor = Intenso2,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = variableRemember,
        onValueChange = { viewmodel.setEmail(it)
                        viewmodel.setEmailValido(viewmodel.isValidEmail(it))
                        },
        leadingIcon = {
            Icon(painter = painterResource(id = icono),
                contentDescription = "",
                modifier = Modifier.sizeIn(maxHeight = 40.dp))
        },
        isError = !emailValido
    )
    if(!emailValido){
        Text("El email no tiene una estructura valida")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTextoTitulo(appViewModel: AppViewModel, titulo: String,campoValido: Boolean){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = {Text(text = "Titulo", style = TextStyle(Color.Black))},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Intenso2,
            focusedLabelColor = Intenso2,
            cursorColor = Intenso2,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = titulo,
        onValueChange = {
            if(it.length <= 255){
                appViewModel.setTitulo(it)
                appViewModel.setTituloValido(appViewModel.isTituloValido(it))
            }

        },
        supportingText = {
            Text(
                text = "${appViewModel.getTitulo().length} / 255",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        isError = !campoValido
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTextoDescripcion(appViewModel: AppViewModel, descripcion: String,campoValido: Boolean){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = {Text(text = "Descripcion", style = TextStyle(Color.Black))},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Intenso2,
            focusedLabelColor = Intenso2,
            cursorColor = Intenso2,
        ),
        value = descripcion,
        onValueChange = {
            if(it.length <= 255) {
                appViewModel.setDescripcion(it)
                appViewModel.setDescripcionValido(appViewModel.isDescripcionValido(it))
            }
        },
        supportingText = {
            Text(
                text = "${appViewModel.getDescripcion().length} / 255",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        isError = !campoValido
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoNumeroPlazas(appViewModel: AppViewModel, nPlazas: Int,campoValido: Boolean){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = {Text(text = "Numero Plazas", style = TextStyle(Color.Black))},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Intenso2,
            focusedLabelColor = Intenso2,
            cursorColor = Intenso2,
        ),
        value = nPlazas.toString(),
        onValueChange = {
            appViewModel.setNumeroPlazas(it.toInt())
            appViewModel.setNPlazasValido(appViewModel.isNPlazasValido(it.toInt()))
        },
        supportingText = {
            Text(
                text = "${appViewModel.getDescripcion().length} / 255",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        isError = !campoValido,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    if(!campoValido){
        Text("El numero no puede ser mayor a 99 o tener decimales")
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoContraseñaUnico(
    viewmodel: AppViewModel,
    variableRemember: String,
    textoLabel: String,
    @DrawableRes icono: Int,
    passwordValido: Boolean
) {


    val contraseñaVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = { Text(text = textoLabel, style = TextStyle(Color.Black)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Intenso2,
            focusedLabelColor = Intenso2,
            cursorColor = Intenso2,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = variableRemember,
        onValueChange = { viewmodel.setPassword(it)
                        viewmodel.setPasswordValido(viewmodel.isValidPassword(it))
                        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icono),
                contentDescription = "",
                modifier = Modifier.sizeIn(maxHeight = 40.dp)
            )
        },
        trailingIcon = {
            val iconImage = if (contraseñaVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var description = if (contraseñaVisible.value) {
                "Hide Password"
            } else {
                "Show Password"
            }

            IconButton(onClick = { contraseñaVisible.value = !contraseñaVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = "")
            }
        },
        visualTransformation = if (contraseñaVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !passwordValido

    )

    if(!passwordValido){
        Text("La contraseña debe tener entre 6 y 16 caracteres")
    }

}

//endregion

//region Botones
@Composable
fun BotonInhabilitado(
    textoBoton: String,
    accion: () -> Unit,
    botonActivo: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { accion() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Intenso3
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        enabled = botonActivo
    ) {
        Text(textoBoton)
    }
}

@Composable
fun BotonHabilitado(textoBoton: String, accion: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = { accion() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Intenso3
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(textoBoton)
    }
}

//endregion

//region Combos, Radio, otros
@Composable
fun EleccionUsuario(viewmodel: AppViewModel) {
    val radioOptions = listOf("Consumidor", "Ofertante")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    .padding(horizontal = 16.dp)
                    .height(IntrinsicSize.Min)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text)
                                viewmodel.setTipeUser(text)
                              },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically)

                )
            }
        }
    }
}
@Composable
fun CajaChequeo(
    politicaMostrado: MutableState<Boolean>,
    terminosMostrado: MutableState<Boolean>,
    chequeoActivo: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = chequeoActivo.value,
            onCheckedChange = { chequeoActivo.value = !chequeoActivo.value },
            colors = CheckboxDefaults.colors(Intenso3)
        )

        TextoClicableTerminosPolitica(politicaMostrado, terminosMostrado)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoMuchoTexto(
    textoCabecera: String,
    cuerpo: @Composable () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(1.dp, color = Suave2)
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text(textoCabecera) })
                },
                bottomBar = {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text("Cerrar")
                    }
                }
            ) { contentPadding ->
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                        .padding(16.dp)
                ) {
                    item {
                        cuerpo()
                    }
                }
            }
        }

    }
}

//endregion

@Composable
public fun ExpandibleCabecera(appViewModel: AppViewModel,textoCabecera: String) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            item{
                CabeceraTextoNormal(value = textoCabecera)

                var actividadSeleccionada by remember { mutableStateOf<ActividadOfertante?>(null) }

                actividadSeleccionada?.let { actividad ->
                    DialogoTablonAnunciosConsumidor(
                        appViewModel = appViewModel,
                        actividad = actividad,
                        onDismiss = { actividadSeleccionada = null }
                    )
                }

                if (expanded) {
                    LazyRow {
                        items(appViewModel.getListaActividadesRecientesConsumidor()){actividad->
                            TablonActividadesOfertantesCard(
                                actividadOfertante = actividad,
                                accionClicar = { actividadSeleccionada = actividad },
                                cardSize = 200.dp,
                                imageSize = 100.dp
                            )
                        }
                    }
                }
            }

        }

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription =
                if (expanded) {
                    "show less"
                } else {
                    "show more"
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun DropDownList(appViewModel: AppViewModel,opciones: List<String>,selectedText: String,campoValido: Boolean){
    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {isExpanded = !isExpanded}
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .padding(8.dp),
            value = selectedText,
            onValueChange = {},
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Intenso2,
                focusedLabelColor = Intenso2,
                cursorColor = Intenso2
            ),
            readOnly = true,
            label = {Text("Categoria", style = TextStyle(Color.Black))},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            isError = !campoValido
        )
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            opciones.forEachIndexed{ index, text->
                DropdownMenuItem(
                    text = { Text(text) },
                    onClick = {
                        appViewModel.setCategoria(opciones[index])
                        appViewModel.setCategoriaValido(appViewModel.isCategoriaValido(opciones[index]))
                        isExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
        
    }
}

fun SeleccionImagenActividad(categoria: String): Int {
    when(categoria){
        "deportes"-> return R.drawable.deportesimage
        "naturaleza"-> return R.drawable.naturalezaimage
        "educacion"-> return R.drawable.educacionimage
        "otros"-> return R.drawable.otrosimage
        else->{
            return 0
        }
    }
}

//region CARDS
@Composable
fun TablonActividadesOfertantesCard(
    actividadOfertante: ActividadOfertante,
    accionClicar: () -> Unit,
    modifier: Modifier = Modifier,
    cardSize: Dp,
    imageSize: Dp
)
{

    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(cardSize)
            .clickable { accionClicar() }

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = SeleccionImagenActividad(actividadOfertante.categoria)),
                contentDescription = actividadOfertante.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageSize),
                contentScale = ContentScale.Crop
            )
            Text(
                text = actividadOfertante.titulo,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()

            )
        }
    }

}

@Composable
fun MisActividadesConsumidorCard(
    actividadConsumidor: ActividadConsumidor,
    accionClicar: () -> Unit,
    cardSize: Dp,
    imageSize: Dp
){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(cardSize)
            .clickable { accionClicar() }

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = SeleccionImagenActividad(actividadConsumidor.categoria)),
                contentDescription = actividadConsumidor.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageSize),
                contentScale = ContentScale.Crop
            )
            Text(
                text = actividadConsumidor.titulo,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()

            )
        }
    }
}

@Composable
fun MisActividadesOfertanteCard(
    actividadOfertante: ActividadOfertante,
    accionClicar: () -> Unit,
    cardSize: Dp,
    imageSize: Dp
){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(cardSize)
            .clickable { accionClicar() }

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = SeleccionImagenActividad(actividadOfertante.categoria)),
                contentDescription = actividadOfertante.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageSize),
                contentScale = ContentScale.Crop
            )
            Text(
                text = actividadOfertante.titulo,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()

            )
        }
    }
}



@Composable
fun TablonActividadesConsumidoresCard(
    actividadConsumidor: ActividadConsumidor,
    accionClicar: () -> Unit,
    modifier: Modifier,
    cardSize: Dp,
    imageSize: Dp
)
{

    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(cardSize)
            .clickable { accionClicar() }

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = SeleccionImagenActividad(actividadConsumidor.categoria)),
                contentDescription = actividadConsumidor.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageSize),
                contentScale = ContentScale.Crop
            )
            Text(
                text = actividadConsumidor.titulo,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()

            )
        }
    }

}

//endregion

//region DIALOGS
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoTablonAnunciosConsumidor(
    appViewModel: AppViewModel,
    actividad: ActividadOfertante,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .border(1.dp, color = Suave2)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                CabeceraTextoNormal(value = "Info Actividad")
                Image(
                    painter = painterResource(id = SeleccionImagenActividad(actividad.categoria)),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Titulo: ")
                        }
                        append(actividad.titulo)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Descripcion: ")
                        }
                        append(actividad.descripcion)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Numero de plazas: ")
                        }
                        append(actividad.numeroPlazas.toString())
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Fecha: ")
                        }
                        append(actividad.dueDate.toString())
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                var boton by remember { mutableStateOf(appViewModel.estaApuntadoConsumidor(actividad.idActividadOfertante)) }
                if (boton) {
                    Button(
                        onClick = {
                            appViewModel.desapuntarseActividadApuntadoConsumidor(actividad.idActividadOfertante)
                            boton = appViewModel.estaApuntadoConsumidor(actividad.idActividadOfertante)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Desapuntarse")
                    }
                } else {
                    Button(
                        onClick = {
                            appViewModel.apuntarConsumidorAActividadOfertante(actividad)
                            boton = appViewModel.estaApuntadoConsumidor(actividad.idActividadOfertante)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Apuntarse")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoTablonAnunciosOfertante(
    appViewModel: AppViewModel,
    actividad: ActividadConsumidor,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .border(1.dp, color = Suave2)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                CabeceraTextoNormal(value = "Info Actividad")
                Image(
                    painter = painterResource(id = SeleccionImagenActividad(actividad.categoria)),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Titulo: ")
                        }
                        append(actividad.titulo)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Descripcion: ")
                        }
                        append(actividad.descripcion)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Fecha: ")
                        }
                        append(actividad.dueDate.toString())
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                var boton by remember { mutableStateOf(appViewModel.estaApuntadoOfertante(actividad.idActividadConsumidor)) }
                if (boton) {
                    Button(
                        onClick = {
                            appViewModel.desapuntarseActividadApuntadoOfertante(actividad.idActividadConsumidor)
                            boton = appViewModel.estaApuntadoOfertante(actividad.idActividadConsumidor)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Desapuntarse")
                    }
                } else {
                    Button(
                        onClick = {
                            appViewModel.apuntarOfertanteAActividadConsumidor(actividad)
                            boton = appViewModel.estaApuntadoOfertante(actividad.idActividadConsumidor)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Apuntarse")
                    }
                }
            }
        }
    }
}


@Composable
fun DialogoMisActividadesConsumidor(
    appViewModel: AppViewModel,
    actividad: ActividadConsumidor,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .border(1.dp, color = Suave2)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                CabeceraTextoNormal(value = "Info Actividad")
                Image(
                    painter = painterResource(id = SeleccionImagenActividad(actividad.categoria)),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Titulo: ")
                        }
                        append(actividad.titulo)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Descripcion: ")
                        }
                        append(actividad.descripcion)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Fecha: ")
                        }
                        append(actividad.dueDate.toString())
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = {
                        appViewModel.borrarActividadConsumidor(actividad.idActividadConsumidor)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Borrar")
                }
            }
        }
    }
}

@Composable
fun DialogoMisActividadesOfertante(
    appViewModel: AppViewModel,
    actividad: ActividadOfertante,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .border(1.dp, color = Suave2)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                CabeceraTextoNormal(value = "Info Actividad")
                Image(
                    painter = painterResource(id = SeleccionImagenActividad(actividad.categoria)),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Titulo: ")
                        }
                        append(actividad.titulo)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Descripcion: ")
                        }
                        append(actividad.descripcion)
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Fecha: ")
                        }
                        append(actividad.dueDate.toString())
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = {
                        appViewModel.borrarActividadOfertante(actividad.idActividadOfertante)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Borrar")
                }
            }
        }
    }
}
//endregion




