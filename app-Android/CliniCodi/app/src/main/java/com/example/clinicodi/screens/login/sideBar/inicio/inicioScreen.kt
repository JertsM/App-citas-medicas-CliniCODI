package com.example.clinicodi.screens.login.sideBar.inicio

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(onOptionClick: (String) -> Unit) {

    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences(
        "usuario",
        Context.MODE_PRIVATE
    )

    val nombreUsuario = sharedPreferences.getString("nombreUsuario", "Usuario") ?: "Usuario"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {

        Text(
            text = "Hola, $nombreUsuario",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D2B45)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Bienvenido de nuevo a CliniCODI",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF8F7)
            ),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Tu salud es lo primero",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D2B45)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Gestiona tus citas médicas de forma rápida, sencilla y segura.",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Acceso rápido",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D2B45)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InicioCard(
                titulo = "Pedir cita",
                descripcion = "Reserva una cita",
                modifier = Modifier.weight(1f),
                onClick = {
                    onOptionClick("Calendario")
                }
            )

            InicioCard(
                titulo = "Mis citas",
                descripcion = "Consulta tus citas",
                modifier = Modifier.weight(1f),
                onClick = {
                    onOptionClick("Mis citas")
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        InicioCard(
            titulo = "Mi perfil",
            descripcion = "Consulta tus datos personales",
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onOptionClick("Perfil")
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Cuidamos de ti",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 15.sp,
            color = Color.Gray
        )
    }
}