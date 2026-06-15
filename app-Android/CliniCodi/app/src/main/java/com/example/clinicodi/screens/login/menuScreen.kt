package com.example.clinicodi.screens.login

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
                    selectedOption = "Cerrar sesión"
                    scope.launch { drawerState.close() }
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

                    "Inicio" -> {
                        Text("Pantalla de Inicio")
                    }

                    "Calendario" -> {
                        // Forzamos el idioma a español para el DatePicker
                        Locale.setDefault(Locale("es", "ES"))
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

                            // Usamos FlowRow para que las horas se organicen en varias columnas
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
                                                // Aquí irá la llamada al backend
                                            },
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(14.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = colorResource(R.color.azul_claro)
                                            )
                                        ) {
                                            Text("Confirmar cita")
                                        }
                                    }
                                }
                            }
                        }
                    }

                    "Mis citas" -> {
                        Text("Pantalla de citas")
                    }

                    "Avisos" -> {
                        Text("Pantalla de Avisos")
                    }

                    "Acerca de" -> {

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

                    "Perfil" -> {
                        Text("Pantalla de Perfil")
                    }

                    "Cerrar sesión" -> {
                        navController.navigate("login") {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
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
