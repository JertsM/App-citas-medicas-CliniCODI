package com.example.clinicodi.screens.login.sideBar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.clinicodi.ApiService.RetrofitClient.api
import com.example.clinicodi.Dataclass.CitaRequest
import com.example.clinicodi.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.toString

fun convertirFecha(fecha: String): String {
    val partes = fecha.split("/")
    return "${partes[2]}-${partes[1]}-${partes[0]}"
}

@Composable
fun calendarioScreen() {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val sharedPreferences = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
    val emailUsuario = sharedPreferences.getString("email", "") ?: ""

    LaunchedEffect(Unit) {
        Locale.setDefault(Locale("es", "ES"))
    }
    val calendario = rememberDatePickerState()
    val diaSeleccionado = calendario.selectedDateMillis?.let {
        SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES")).format(Date(it))
    }
    var horaSeleccionada by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    val availableHour = listOf(
        "09:00", "09:30", "10:00", "10:30",
        "11:00", "11:30", "12:00", "12:30",
        "16:00", "16:30", "17:00", "17:30"
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

        Surface(
            shape = RoundedCornerShape(14.dp),
            color = colorResource(R.color.azul_claro),
            modifier = Modifier.fillMaxWidth()
        ) {
            DatePicker(
                state = calendario,
                showModeToggle = false,
                colors = DatePickerDefaults.colors(
                    containerColor = Color.Transparent, // Usamos el color del Surface
                    titleContentColor = Color.White,
                    headlineContentColor = Color.White,
                    weekdayContentColor = Color.White.copy(alpha = 0.8f),
                    subheadContentColor = Color.White.copy(alpha = 0.8f),
                    navigationContentColor = Color.White,
                    yearContentColor = Color.White,
                    selectedYearContentColor = colorResource(R.color.azul_claro),
                    selectedYearContainerColor = Color.White,
                    dayContentColor = Color.White,
                    selectedDayContainerColor = Color.White,
                    selectedDayContentColor = colorResource(R.color.azul_claro),
                    todayContentColor = Color.White,
                    todayDateBorderColor = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selecciona una hora",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.azul_claro),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 3
        ) {
            availableHour.forEach { hour ->
                val isSelected = horaSeleccionada == hour

                FilterChip(
                    selected = isSelected,
                    onClick = { horaSeleccionada = hour },
                    label = { Text(hour) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = colorResource(R.color.azul_claro),
                        selectedLabelColor = Color.White,
                        disabledLabelColor = Color.White,
                        containerColor = colorResource(R.color.azul_claro),
                        labelColor = Color.White
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isSelected,
                        borderColor = colorResource(R.color.azul_claro),
                        selectedBorderColor = colorResource(R.color.azul_claro)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (
            calendario.selectedDateMillis != null &&
            horaSeleccionada != null
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.azul_muy_claro)
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Resumen de la cita",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Fecha: $diaSeleccionado",
                        color = Color.DarkGray
                    )
                    Text(
                        text = "Hora: $horaSeleccionada",
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val cita = CitaRequest(
                                emailUsuario = emailUsuario,
                                fecha = convertirFecha(diaSeleccionado.toString()),
                                hora = horaSeleccionada.toString()
                            )

                            scope.launch {
                                try {
                                    val response = api.crearCita(cita)

                                    if (response.isSuccessful) {
                                        Toast.makeText(
                                            context,
                                            "Cita confirmada correctamente",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Ya tienes una cita pendiente",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "Error de conexión: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.azul_claro),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Confirmar cita")
                    }
                }
            }
        }
    }
}