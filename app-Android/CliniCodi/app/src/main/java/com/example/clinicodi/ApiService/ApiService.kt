package com.example.clinicodi.ApiService

import com.example.clinicodi.Dataclass.CitaRequest
import com.example.clinicodi.Dataclass.LoginRequest
import com.example.clinicodi.Dataclass.LoginResponse
import com.example.clinicodi.Dataclass.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    
    @POST("auth/registrar")
    suspend fun registrar(
        @Body request: RegisterRequest
    ): Response<ResponseBody>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("citas/crear")
    suspend fun crearCita(
        @Body citaRequest: CitaRequest
    ): Response<ResponseBody>
}