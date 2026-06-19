package com.example.clinicodi.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clinicodi.R
import com.example.clinicodi.screens.login.sideBar.calendarioScreen
import com.example.clinicodi.screens.login.sideBar.acercade.acercaDe
import com.example.clinicodi.screens.login.sideBar.avisosScreen
import com.example.clinicodi.screens.login.sideBar.inicio.InicioScreen
import com.example.clinicodi.screens.login.sideBar.miscitas.misCitasScreen
import com.example.clinicodi.screens.login.sideBar.perfil.perfilScreen
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun menuScreen(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var selectedOption by remember {
        mutableStateOf("Inicio")
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "CliniCodi",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fuenteClinicodi,
                    color = colorResource(R.color.azul_claro),
                    modifier = Modifier.padding(16.dp)
                )

                HorizontalDivider(color = colorResource(R.color.azul_muy_claro))

                DrawerItem("Inicio") {
                    selectedOption = "Inicio"
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Calendario") {
                    selectedOption = "Calendario"
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Mis citas") {
                    selectedOption = "Mis citas"
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Avisos") {
                    selectedOption = "Avisos"
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Acerca de") {
                    selectedOption = "Acerca de"
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Perfil") {
                    selectedOption = "Perfil"
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Cerrar sesión") {
                    scope.launch {
                        drawerState.close()
                            navController.navigate("login") {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                    }
                }
            }
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = selectedOption,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fuenteClinicodi,
                            fontSize = 36.sp,
                            letterSpacing = 2.sp,
                            style = LocalTextStyle.current.copy(
                                shadow = androidx.compose.ui.graphics.Shadow(
                                    color = colorResource(R.color.azul_muy_claro).copy(alpha = 0.8f),
                                    offset = androidx.compose.ui.geometry.Offset(0f, 0f),
                                    blurRadius = 10f
                                )
                            )
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(R.color.azul_claro),
                        navigationIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú"
                            )
                        }
                    }
                )
            }
        ) { padding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp)
            ) {

                when (selectedOption) {

                    "Inicio" -> InicioScreen(
                        onOptionClick = { option ->
                            selectedOption = option
                        }
                    )

                    "Calendario" -> calendarioScreen()

                    "Mis citas" -> misCitasScreen(navController)

                    "Avisos" -> avisosScreen()

                    "Acerca de" -> acercaDe()

                    "Perfil" -> perfilScreen()
                }
            }
        }
    }
}

@Composable
fun DrawerItem(
    title: String,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
        label = { Text(title) },
        selected = false,
        onClick = onClick,
    )
}
