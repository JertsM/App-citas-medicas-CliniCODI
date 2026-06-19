package com.example.clinicodi.screens.login.sideBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.clinicodi.Dataclass.Aviso

@Composable
fun avisosScreen() {
    var textoAviso by remember { mutableStateOf("") }

    val avisos = remember {
        mutableStateListOf(
            Aviso(
                autor = "MÉDICO",
                rol = "Doctor",
                fecha = "Hoy",
                mensaje = "¡Buenos días! Recordad que mañana no habrá consultas por mantenimiento."
            ),
            Aviso(
                autor = "MÉDICO",
                rol = "Doctor",
                fecha = "3 jun",
                mensaje = "Ya están disponibles las citas para la próxima semana."
            ),
            Aviso(
                autor = "SISTEMA",
                rol = "Admin",
                fecha = "1 jun",
                mensaje = "Nueva actualización: Ahora puedes ver tus resultados de laboratorio directamente desde la app."
            ),
            Aviso(
                autor = "MÉDICO",
                rol = "Doctor",
                fecha = "28 may",
                mensaje = "Recordatorio: La campaña de vacunación contra la gripe comienza el próximo lunes."
            ),
            Aviso(
                autor = "SISTEMA",
                rol = "Seguridad",
                fecha = "25 may",
                mensaje = "Aviso de seguridad: No compartas tus credenciales de acceso con nadie."
            ),
            Aviso(
                autor = "ADMIN",
                rol = "Administración",
                fecha = "20 may",
                mensaje = "Horario de verano: A partir del 1 de julio, el centro cerrará a las 20:00h."
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FBFF))
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(avisos) { aviso ->
                AvisoCard(aviso)
            }
        }
    }
}
