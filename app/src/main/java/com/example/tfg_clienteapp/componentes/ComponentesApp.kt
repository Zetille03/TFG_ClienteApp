package com.example.tfg_clienteapp.componentes

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tfg_clienteapp.R
import com.example.tfg_clienteapp.ui.theme.ColorGris
import com.example.tfg_clienteapp.ui.theme.Primario

@Composable
fun TextoNormal(value: String,modifier: Modifier = Modifier) {
    Text(
        text= value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun CabezeraTextoNormal(value: String,modifier: Modifier = Modifier) {
    Text(
        text= value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        ),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTextoUnico(textoLabel: String,@DrawableRes icono: Int){

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
        label = {Text(text = textoLabel)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
           focusedBorderColor = Primario,
            focusedLabelColor = Primario,
            cursorColor = Primario,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {textValue.value = it},
        leadingIcon = {
            Icon(painter = painterResource(id = icono),
                contentDescription = "",
                modifier = Modifier.sizeIn(maxHeight = 40.dp))
        }
    )
}