package com.example.clinicodi.screens.login.sideBar.miscitas

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clinicodi.Dataclass.CitaResponse
import com.example.clinicodi.ApiService.RetrofitClient
import com.example.clinicodi.R
import kotlinx.coroutines.launch

@Composable
fun misCitasScreen(navController: NavController) {

    val context = LocalContext.current
    val api = RetrofitClient.api
    val coroutineScope = rememberCoroutineScope()

    var citas by remember { mutableStateOf<List<CitaResponse>>(emptyList()) }

    val sharedPreferences = context.getSharedPreferences(
        "usuario",
        Context.MODE_PRIVATE
    )

    val emailUsuario = sharedPreferences.getString("email", "") ?: ""

    LaunchedEffect(Unit) {
        if (emailUsuario.isNotEmpty()) {
            try {
                val response = api.obtenerMisCitas(emailUsuario)
                Log.d("MIS_CITAS", "Email guardado: $emailUsuario")

                if (response.isSuccessful) {
                    citas = response.body() ?: emptyList()
                } else {
                    Log.e("MIS_CITAS", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("MIS_CITAS", "Excepción al obtener citas: ${e.message}")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Mis citas",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.azul_claro)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (citas.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No tienes citas pendientes",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(citas, key = { it.idCita }) { cita ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(R.color.azul_muy_claro)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Información de la cita",
                                style = MaterialTheme.typography.titleMedium,
                                color = colorResource(R.color.azul),
                                fontWeight = FontWeight.Bold
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text(
                                text = "Fecha: ${cita.fecha}",
                                fontWeight = FontWeight.Medium,
                                color = Color.DarkGray
                            )
                            Text(
                                text = "Hora: ${cita.hora}",
                                fontWeight = FontWeight.Medium,
                                color = Color.DarkGray
                            )
                            Text(
                                text = "Estado: ${cita.estado}",
                                color = colorResource(R.color.azul),
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        coroutineScope.launch {
                                            try {
                                                val response = api.cancelarCita(
                                                    cita.idCita,
                                                    emailUsuario
                                                )

                                                if (response.isSuccessful) {
                                                    Toast.makeText(
                                                        context,
                                                        "Cita cancelada",
                                                        Toast.LENGTH_SHORT
                                                    ).show()

                                                    citas = citas.filter {
                                                        it.idCita != cita.idCita
                                                    }
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Error al cancelar cita",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            } catch (e: Exception) {
                                                Toast.makeText(
                                                    context,
                                                    "Error: ${e.message}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White,
                                        contentColor = Color.Red
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red)
                                ) {
                                    Text("Cancelar")
                                }

                                Button(
                                    onClick = {
                                        navController.navigate("modificarCita/${cita.idCita}")
                                    },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(R.color.azul_claro),
                                        contentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("Modificar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}