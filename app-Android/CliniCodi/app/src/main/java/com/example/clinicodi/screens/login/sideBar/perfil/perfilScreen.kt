package com.example.clinicodi.screens.login.sideBar.perfil

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clinicodi.ApiService.RetrofitClient
import com.example.clinicodi.Dataclass.PerfilResponse
import com.example.clinicodi.R

@Composable
fun perfilScreen() {


    val context = LocalContext.current
    val api = RetrofitClient.api

    var perfil by remember { mutableStateOf<PerfilResponse?>(null) }

    val sharedPreferences = context.getSharedPreferences(
        "usuario",
        Context.MODE_PRIVATE
    )

    val emailUsuario = sharedPreferences.getString("email", "") ?: ""

    LaunchedEffect(Unit) {
        if (emailUsuario.isNotEmpty()) {
            try {
                val response = api.obtenerPerfil(emailUsuario)

                if (response.isSuccessful) {
                    perfil = response.body()
                }
            } catch (e: Exception) {
                // Manejar error si es necesario
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFF2F2F2))
                .border(2.dp, colorResource(R.color.azul_muy_claro), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(80.dp),
                tint = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        perfil?.let { usuario ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Información Personal",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.azul_claro),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Nombre de usuario",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = usuario.nombreUsuario,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Correo Electrónico",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = usuario.email,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}