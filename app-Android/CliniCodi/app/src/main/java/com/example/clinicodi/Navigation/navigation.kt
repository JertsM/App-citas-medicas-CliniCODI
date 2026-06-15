package com.example.clinicodi.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clinicodi.ViewModel.UsuariosViewModel
import com.example.clinicodi.screens.login.inicioSesionScreen
import com.example.clinicodi.screens.login.loginScreen
import com.example.clinicodi.screens.login.menuScreen
import com.example.clinicodi.screens.login.registerScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val usuariosViewModel: UsuariosViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable ("login") {
            loginScreen(navController)
        }
        composable ("registrar"){
            registerScreen(navController)
        }
        composable ("inicio_sesion") {
            inicioSesionScreen(navController)
        }
        composable ("menu") {
            menuScreen(navController)
        }
    }


}