package com.example.clinicodi.ApiService

import com.example.clinicodi.Dataclass.CitaRequest
import com.example.clinicodi.Dataclass.CitaResponse
import com.example.clinicodi.Dataclass.LoginRequest
import com.example.clinicodi.Dataclass.LoginResponse
import com.example.clinicodi.Dataclass.MensajeResponse
import com.example.clinicodi.Dataclass.PerfilResponse
import com.example.clinicodi.Dataclass.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("usuarios/perfil")
    suspend fun obtenerPerfil(
        @Query("email") email: String
    ): Response<PerfilResponse>

    @GET("citas/mis-citas")
    suspend fun obtenerMisCitas(
        @Query("email") email: String
    ): Response<List<CitaResponse>>

    @PUT("citas/cancelar/{idCita}")
    suspend fun cancelarCita(
        @Path("idCita") idCita: Int,
        @Query("email") email: String
    ): Response<ResponseBody>

    @PUT("citas/modificar/{idCita}")
    suspend fun modificarCita(
        @Path("idCita") idCita: Int,
        @Body citaRequest: CitaRequest
    ): Response<MensajeResponse>
}