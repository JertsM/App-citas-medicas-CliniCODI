package com.example.clinicodi.screens.login

import android.annotation.SuppressLint
import android.view.PixelCopy.request
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import com.example.clinicodi.R
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clinicodi.ApiService.RetrofitClient
import com.example.clinicodi.Dataclass.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@Composable
fun inicioSesionScreen (navController: NavController) {

    var introducirCorreo by remember { mutableStateOf("") }
    var introducirPass by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val request = LoginRequest(
        email = introducirCorreo,
        password = introducirPass
    )

    val colorCuadrosTexto2 = Color(R.color.azul_muy_claro)

    var passwordVisible by remember { mutableStateOf(false) }

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
            text = "Inicio de sesión",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = introducirCorreo,
            onValueChange = { introducirCorreo = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.azul_muy_claro),
                focusedIndicatorColor = colorCuadrosTexto2,
                focusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = introducirPass,
            onValueChange = { introducirPass = it },
            label = { Text("Contraseña") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.azul_muy_claro),
                focusedIndicatorColor = colorCuadrosTexto2,
                focusedContainerColor = Color.White
            ),
            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector =
                            if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {

                scope.launch {
                    try {
                        val response = withContext(Dispatchers.IO) {
                            RetrofitClient.api.login(request)
                        }
                        if (response.isSuccessful) {
                            navController.navigate("menu")
                        } else {
                            Toast.makeText(
                                context,
                                "Error. Credenciales incorrectas",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "Exception: ${e.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            },
            modifier = Modifier
                .height(50.dp),
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
    }
}