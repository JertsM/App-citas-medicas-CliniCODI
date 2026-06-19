package com.example.clinicodi.screens.login.sideBar.acercade

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicodi.screens.login.fuenteClinicodi

@Composable
fun acercaDe() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "CliniCODI es una marca que se especializa en la creación de plataformas de gestión de clínicas médicas " +
                    "tanto para los profesionales de la salud como para los propios pacientes.\n \nLa aplicación permite a los usuarios registrarse," +
                    " iniciar sesión, consultar información personal, visualizar un calendario de disponibilidad y reservar citas" +
                    " de forma rápida y sencilla. Por su parte, el sistema mantiene un control centralizado de " +
                    "la información mediante una base de datos que almacena los usuarios y las reservas realizadas.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = "CliniCodi",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fuenteClinicodi,
            color = Color.Gray,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }

}