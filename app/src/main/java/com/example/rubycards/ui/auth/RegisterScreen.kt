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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.rubycards.ui.theme.RubyButton
import com.example.rubycards.network.RetrofitClient
import com.example.rubycards.network.RegisterRequest
import com.example.rubycards.network.RegisterUser
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Регистрация", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            // Обычная клавиатура, где первая буква заглавная (по умолчанию для имен)
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it.trim() }, // trim() убирает случайные пробелы по краям
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            // Включаем специальную клавиатуру для почты (с символом @)
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it.trim() },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (resultMessage.isNotEmpty()) {
            Text(
                text = resultMessage,
                color = if (resultMessage.contains("успешна")) Color(0xFF4CAF50) else MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            RubyButton(
                text = "Создать аккаунт",
                onClick = {
                    // 1. Проверка на пустые поля
                    if (name.isBlank() || email.isBlank() || password.isBlank()) {
                        resultMessage = "Пожалуйста, заполните все поля"
                        return@RubyButton
                    }

                    // 2. Проверка правильности формата Email
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        resultMessage = "Пожалуйста, введите корректный Email"
                        return@RubyButton
                    }

                    // 3. Проверка надежности пароля
                    val isPasswordStrong = password.length >= 8 &&
                            password.any { it.isLetter() } &&
                            password.any { it.isDigit() }

                    if (!isPasswordStrong) {
                        resultMessage = "Пароль должен содержать минимум 8 символов, включая буквы и цифры"
                        return@RubyButton
                    }

                    // Если все проверки пройдены, отправляем запрос на сервер
                    isLoading = true
                    resultMessage = ""
                    focusManager.clearFocus()

                    coroutineScope.launch {
                        try {
                            val request = RegisterRequest(RegisterUser(name, email, password))
                            val response = RetrofitClient.apiService.register(request)

                            if (response.isSuccessful) {
                                resultMessage = "Регистрация успешна!"
                                onNavigateToLogin()
                            } else {
                                // Если сервер вернул ошибку (например, такой email уже есть)
                                resultMessage = "Ошибка: сервер вернул код ${response.code()}"
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

        TextButton(onClick = onNavigateToLogin, enabled = !isLoading) {
            Text("Уже есть аккаунт? Войти")
        }
    }
}