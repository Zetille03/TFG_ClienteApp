package com.example.tfg_clienteapp.ui.componentes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tfg_clienteapp.ui.architecture.AppViewModel
import com.example.tfg_clienteapp.ui.theme.Intenso2
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    appViewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val dateState = rememberDatePickerState()
    val millisToLocalDate = dateState.selectedDateMillis?.let {
        convertMillisToLocalDate(it)
    }
    appViewModel.setFecha(millisToLocalDate?.let {
        dateToTimestampString(millisToLocalDate)
    } ?: "")

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = appViewModel.getFecha(),
            onValueChange = {appViewModel.setFecha(it)},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Calendar Icon",
                    modifier = Modifier.clickable { showDialog = true }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .padding(16.dp)
                .clickable { showDialog = true },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Intenso2,
                focusedLabelColor = Intenso2,
                cursorColor = Intenso2,
            )
        )

        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = dateState,
                    showModeToggle = true
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToLocalDate(millis: Long) : LocalDate {
    return Instant
        .ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToLocalDateWithFormatter(date: LocalDate, dateTimeFormatter: DateTimeFormatter) : LocalDate {
    //Convert the date to a long in millis using a dateformmater
    val dateInMillis = LocalDate.parse(date.format(dateTimeFormatter), dateTimeFormatter)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    //Convert the millis to a localDate object
    return Instant
        .ofEpochMilli(dateInMillis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}


@RequiresApi(Build.VERSION_CODES.O)
fun dateToString(date: LocalDate): String {
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.getDefault())
    val dateInMillis = convertMillisToLocalDateWithFormatter(date, dateFormatter)
    return dateFormatter.format(dateInMillis)
}

@RequiresApi(Build.VERSION_CODES.O)
fun dateToTimestampString(date: LocalDate): String {
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", Locale.getDefault())
    val instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant()
    val timestamp = Timestamp.from(instant)
    return dateFormatter.format(timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
}
