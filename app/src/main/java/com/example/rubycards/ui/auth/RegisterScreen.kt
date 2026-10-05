package com.example.rubycards.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    // Состояния для отображения загрузки и сообщений (ошибок/успеха)
    var isLoading by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("") }

    // Scope для запуска корутин (фоновых задач)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
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
            enabled = !isLoading // Блокируем ввод во время загрузки
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Блок вывода сообщений (красный для ошибок, зеленый для успеха)
        if (resultMessage.isNotEmpty()) {
            Text(
                text = resultMessage,
                color = if (resultMessage.contains("успешна")) Color(0xFF4CAF50) else MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Если идет загрузка — показываем индикатор, если нет — кнопку
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            RubyButton(
                text = "Создать аккаунт",
                onClick = {
                    if (name.isBlank() || email.isBlank() || password.isBlank()) {
                        resultMessage = "Пожалуйста, заполните все поля"
                        return@RubyButton
                    }

                    isLoading = true
                    resultMessage = ""

                    // Запускаем сетевой запрос в фоне
                    coroutineScope.launch {
                        try {
                            val request = RegisterRequest(RegisterUser(name, email, password))
                            val response = RetrofitClient.apiService.register(request)

                            if (response.isSuccessful) {
                                resultMessage = "Регистрация успешна!"
                                // При успехе перенаправляем на экран логина
                                onNavigateToLogin()
                            } else {
                                // Если сервер вернул ошибку (например, email уже занят)
                                resultMessage = "Ошибка: сервер вернул код ${response.code()}"
                            }
                        } catch (e: Exception) {
                            // Если нет интернета или сервер выключен
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
