package com.example.tfg_clienteapp.ui.componentes

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tfg_clienteapp.model.Consumidor
import com.example.tfg_clienteapp.ui.theme.*

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTextoUnico(textoLabel: String,@DrawableRes icono: Int){

    val valorTexto = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = {Text(text = textoLabel)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
           focusedBorderColor = Intenso2,
            focusedLabelColor = Intenso2,
            cursorColor = Intenso2,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = valorTexto.value,
        onValueChange = {valorTexto.value = it},
        leadingIcon = {
            Icon(painter = painterResource(id = icono),
                contentDescription = "",
                modifier = Modifier.sizeIn(maxHeight = 40.dp))
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoContraseñaUnico(textoLabel: String,@DrawableRes icono: Int){

    val contraseña = remember {
        mutableStateOf("")
    }

    val contraseñaVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = {Text(text = textoLabel)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Intenso2,
            focusedLabelColor = Intenso2,
            cursorColor = Intenso2,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = contraseña.value,
        onValueChange = {contraseña.value = it},
        leadingIcon = {
            Icon(painter = painterResource(id = icono),
                contentDescription = "",
                modifier = Modifier.sizeIn(maxHeight = 40.dp))
        },
        trailingIcon = {
            val iconImage = if(contraseñaVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }

            var description = if(contraseñaVisible.value){
                "Hide Password"
            }else{
                "Show Password"
            }
            
            IconButton(onClick = { contraseñaVisible.value = !contraseñaVisible.value }) {
                Icon(imageVector = iconImage,contentDescription = "")
            }
        },
        visualTransformation = if(contraseñaVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun CajaChequeo(politicaMostrado: MutableState<Boolean>, terminosMostrado: MutableState<Boolean>, chequeoActivo: MutableState<Boolean>){
    Row(
      modifier = Modifier
          .fillMaxWidth()
          .heightIn(56.dp)
          .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ){
        
        Checkbox(
            checked = chequeoActivo.value,
            onCheckedChange = {chequeoActivo.value = !chequeoActivo.value},
            colors = CheckboxDefaults.colors(Intenso3)
        )

        TextoClicableTerminosPolitica(politicaMostrado,terminosMostrado)
    }
}

@Composable
fun TextoClicableTerminosPolitica(politicaMostrado: MutableState<Boolean>, terminosMostrado: MutableState<Boolean>) {
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


    ClickableText(text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{ span->
                if(span.item == "Términos de Uso"){
                    terminosMostrado.value = true
                }
                if(span.item == "Política de privacidad"){
                    politicaMostrado.value = true
                }
            }

    })
}

@Composable
fun TextoClicable(texto: String): AnnotatedString{
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Intenso2)){
            pushStringAnnotation(tag = texto, annotation = texto)
            append(texto)
        }
    }

    return annotatedString
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoMuchoTexto(
    textoCabecera: String,
    cuerpo: @Composable ()->Unit,
    onDismiss:()->Unit
){
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ){
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(1.dp, color = Suave2)
        ){
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text(textoCabecera) })
                },
                bottomBar = {
                    Button(
                        onClick = {onDismiss()},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ){
                        Text("Cerrar")
                    }
                }
            ){contentPadding->
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                        .padding(16.dp)
                ){
                    item{
                        cuerpo()
                    }
                }
            }
        }

    }
}

@Composable
fun TextoCambiarTipoRegistro(textoNormal: String, textoEnlace: String,accionEnlace: ()-> Unit){

    var annotatedString = buildAnnotatedString {
        append(textoEnlace)
        append(TextoClicable(texto = textoNormal))
    }

    ClickableText(
        text = annotatedString,
        onClick = {offset->
            annotatedString.getStringAnnotations(offset,offset)
                .firstOrNull()?.also{ span->
                    if(span.item == textoNormal){
                        accionEnlace()
                    }

                }

        },

    )
}

@Composable
fun BotonInhabilitado(textoBoton: String, accion: ()-> Unit, checkboxActivo: MutableState<Boolean>, modifier: Modifier = Modifier){
    Button(
        onClick = {accion()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Intenso3
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        enabled = checkboxActivo.value
    ){
        Text(textoBoton)
    }
}

@Composable
fun BotonHabilitado(textoBoton: String, accion: ()-> Unit, modifier: Modifier = Modifier ){
    Button(
        onClick = {accion()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Intenso3
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Text(textoBoton)
    }
}


@Composable
fun CuerpoPoliticaPrivacidad(){
    TextoNormal(value = "Esta Política de Privacidad describe cómo " +
            "Market Onuba recopila, utiliza y " +
            "comparte información personal de los usuarios de nuestra aplicación " +
            "móvil/sitio web/servicio (en adelante, \"Servicio\"). Al utilizar " +
            "nuestro Servicio, aceptas las prácticas descritas en esta Política de Privacidad.",
        textAlign = TextAlign.Justify
        )
    CabeceraTextoNormal(value = "Información Recopilada")
    TextoNormal("Podemos recopilar información personal identificable, " +
            "como tu nombre, dirección de correo electrónico, dirección postal, " +
            "número de teléfono, información de pago, y cualquier otra información " +
            "que nos proporciones al utilizar nuestro Servicio. También podemos " +
            "recopilar información no identificable, como tu dirección IP, tipo de " +
            "navegador, proveedor de servicios de Internet, páginas de referencia/salida " +
            "y la fecha/hora de acceso",
        textAlign = TextAlign.Justify
    )
    CabeceraTextoNormal(value ="Uso de la Información")
    TextoNormal(value="Podemos recopilar información personal identificable, " +
            "como tu nombre, dirección de correo electrónico, dirección postal, " +
            "número de teléfono, información de pago, y cualquier otra información " +
            "que nos proporciones al utilizar nuestro Servicio. También podemos " +
            "recopilar información no identificable, como tu dirección IP, " +
            "tipo de navegador, proveedor de servicios de Internet, páginas de " +
            "referencia/salida y la fecha/hora de acceso.",
        textAlign = TextAlign.Justify
    )
    CabeceraTextoNormal(value ="Seguridad de la Información")
    TextoNormal("Nos esforzamos por proteger la seguridad de tu " +
            "información personal y tomamos medidas razonables para " +
            "evitar el acceso no autorizado, el uso, la divulgación o " +
            "la alteración de tu información. Sin embargo, ninguna transmisión de " +
            "datos por Internet o método de almacenamiento electrónico es 100% " +
            "seguro, por lo que no podemos garantizar su seguridad absoluta.",
        textAlign = TextAlign.Justify
    )
}

@Composable
fun CuerpoTerminosUso(){
    CabeceraTextoNormal(value = "INFORMACIÓN RELEVANTE")
    TextoNormal("Es requisito necesario para la adquisición de " +
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
    TextoNormal("En caso de que aplique (para venta de software, templetes, " +
            "u otro producto de diseño y programación) usted no puede " +
            "colocar uno de nuestros productos, modificado o sin modificar, " +
            "en un CD, sitio web o ningún otro medio y ofrecerlos para la redistribución " +
            "o la reventa de ningún tipo.",
        textAlign = TextAlign.Justify
    )
    CabeceraTextoNormal(value = "PROPIEDAD")
    TextoNormal("Usted no puede declarar propiedad intelectual o exclusiva a " +
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

