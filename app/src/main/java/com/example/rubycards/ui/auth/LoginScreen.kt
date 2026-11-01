package com.example.rubycards.ui.auth

import android.util.Patterns
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.rubycards.network.LoginRequest
import com.example.rubycards.network.RetrofitClient
import com.example.rubycards.ui.theme.RubyButton
import com.example.rubycards.utils.SessionManager
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    // Цвета для полей ввода — чёткий чёрный текст
    val inputColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        disabledTextColor = Color.Gray,
        focusedBorderColor = Color(0xFFE53935),
        unfocusedBorderColor = Color.Gray,
        disabledBorderColor = Color.LightGray,
        cursorColor = Color(0xFFE53935),
        focusedLabelColor = Color(0xFFE53935),
        unfocusedLabelColor = Color.DarkGray,
        focusedPlaceholderColor = Color.LightGray,
        unfocusedPlaceholderColor = Color.LightGray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Вход в RubyCards", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it.trim() },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            colors = inputColors,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            colors = inputColors,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (resultMessage.isNotEmpty()) {
            Text(
                text = resultMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            RubyButton(
                text = "Войти",
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        resultMessage = "Пожалуйста, заполните все поля"
                        return@RubyButton
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        resultMessage = "Неверный формат Email"
                        return@RubyButton
                    }

                    isLoading = true
                    resultMessage = ""
                    focusManager.clearFocus()

                    coroutineScope.launch {
                        try {
                            val request = LoginRequest(email, password)
                            val response = RetrofitClient.apiService.login(request)

                            if (response.isSuccessful) {
                                val body = response.body()
                                if (body != null && body.authToken != null && body.user != null) {
                                    SessionManager.saveSession(
                                        context = context,
                                        token = body.authToken,
                                        userName = body.user.name,
                                        userEmail = body.user.email
                                    )
                                    onLoginSuccess()
                                } else {
                                    resultMessage = "Ошибка: неожиданный ответ сервера"
                                }
                            } else {
                                val errorBody = response.errorBody()?.string()
                                var errorMessage = "Неверный email или пароль"
                                try {
                                    val jsonObject = org.json.JSONObject(errorBody)
                                    if (jsonObject.has("error")) {
                                        errorMessage = jsonObject.getString("error")
                                    }
                                } catch (_: Exception) {
                                    errorMessage = "Ошибка ${response.code()}"
                                }
                                resultMessage = errorMessage
                            }
                        } catch (e: Exception) {
                            resultMessage = "Ошибка подключения: ${e.localizedMessage}"
                        } finally {
                            isLoading = false
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateToRegister, enabled = !isLoading) {
            Text("Нет аккаунта? Зарегистрироваться")
        }
    }
}