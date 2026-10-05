package com.example.rubycards.network

import com.google.gson.annotations.SerializedName

// То, что мы отправляем при регистрации
data class RegisterRequest(
    val user: RegisterUser
)

data class RegisterUser(
    val name: String,
    val email: String,
    val password: String
)

// То, что мы отправляем при логине
data class LoginRequest(
    val email: String,
    val password: String
)

// То, что сервер возвращает нам в ответ
data class AuthResponse(
    val message: String?,
    val error: String?,
    val user: UserDto?
)

data class UserDto(
    val id: Int,
    val name: String,
    val email: String
)