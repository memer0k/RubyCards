package com.example.rubycards.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
}