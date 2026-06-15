package com.example.clinicodi.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.clinicodi.Dataclass.Usuarios

class UsuariosViewModel: ViewModel() {
    val usuarios = mutableStateListOf<Usuarios>()
}