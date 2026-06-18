package com.example.clinicodi.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clinicodi.Dataclass.RegisterRequest
import com.example.clinicodi.R

val  fuenteClinicodi = FontFamily(
    Font(R.font.britishletter)
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun loginScreen(navController: NavController) {

    var nombreUsuario by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "CliniCodi",
            fontSize = 75.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fuenteClinicodi,
            color = colorResource(R.color.azul_claro)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tu salud, más conectada",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                navController.navigate("inicio_sesion")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.azul_claro)
            )
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {
                val request = RegisterRequest(
                    nombreUsuario = nombreUsuario,
                    email = email,
                    password = password
                )
                navController.navigate("registrar")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(
                1.dp,
                colorResource(R.color.azul_claro)
            )
        ) {
            Text(
                text = "Registrarse",
                color = colorResource(R.color.azul_claro),
                fontSize = 16.sp
            )
        }
    }
}