package com.example.rubycards.network

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    val message: String,
    val user: UserDto?,
    @SerializedName("auth_token") val authToken: String?
)

data class UserDto(
    val id: Int,
    val name: String,
    val email: String
)

data class RegisterRequest(
    val user: RegisterUser
)

data class RegisterUser(
    val name: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)