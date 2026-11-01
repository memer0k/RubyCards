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
import com.example.rubycards.network.RegisterRequest
import com.example.rubycards.network.RegisterUser
import com.example.rubycards.network.RetrofitClient
import com.example.rubycards.ui.theme.RubyButton
import com.example.rubycards.utils.SessionManager
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    var name by remember { mutableStateOf("") }
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
        Text(text = "Регистрация", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            colors = inputColors,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))

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
                color = if (resultMessage.contains("успешна")) Color(0xFF4CAF50) else MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

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
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        resultMessage = "Пожалуйста, введите корректный Email"
                        return@RubyButton
                    }

                    val hasCyrillic = password.any { it in 'а'..'я' || it in 'А'..'Я' || it == 'ё' || it == 'Ё' }
                    val hasLatinLetter = password.any { it in 'a'..'z' || it in 'A'..'Z' }
                    val hasDigit = password.any { it.isDigit() }

                    if (password.length < 8 || !hasLatinLetter || !hasDigit) {
                        resultMessage = "Пароль: мин. 8 символов, латиница и цифры"
                        return@RubyButton
                    }
                    if (hasCyrillic) {
                        resultMessage = "Пароль не может содержать русские буквы"
                        return@RubyButton
                    }

                    isLoading = true
                    resultMessage = ""
                    focusManager.clearFocus()

                    coroutineScope.launch {
                        try {
                            val request = RegisterRequest(RegisterUser(name, email, password))
                            val response = RetrofitClient.apiService.register(request)

                            if (response.isSuccessful) {
                                val body = response.body()
                                if (body != null && body.authToken != null && body.user != null) {
                                    SessionManager.saveSession(
                                        context = context,
                                        token = body.authToken,
                                        userName = body.user.name,
                                        userEmail = body.user.email
                                    )
                                    onRegisterSuccess()
                                } else {
                                    resultMessage = "Ошибка: неожиданный ответ сервера"
                                }
                            } else {
                                val errorBody = response.errorBody()?.string()
                                var errorMessage = "Ошибка регистрации"
                                try {
                                    val jsonObject = org.json.JSONObject(errorBody)
                                    if (jsonObject.has("details")) {
                                        val details = jsonObject.getJSONObject("details")
                                        val messages = mutableListOf<String>()

                                        if (details.has("email")) {
                                            val emailErrors = details.getJSONArray("email")
                                            for (i in 0 until emailErrors.length()) {
                                                val error = emailErrors.getString(i)
                                                messages.add(when {
                                                    error.contains("уже") || error.contains("taken") -> "Этот email уже зарегистрирован"
                                                    error.contains("неверный") || error.contains("invalid") -> "Неверный формат email"
                                                    else -> error
                                                })
                                            }
                                        }

                                        if (details.has("password")) {
                                            val passwordErrors = details.getJSONArray("password")
                                            for (i in 0 until passwordErrors.length()) {
                                                messages.add(passwordErrors.getString(i))
                                            }
                                        }

                                        if (messages.isNotEmpty()) {
                                            errorMessage = messages.joinToString("\n")
                                        }
                                    } else if (jsonObject.has("error")) {
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
        TextButton(onClick = onNavigateToLogin, enabled = !isLoading) {
            Text("Уже есть аккаунт? Войти")
        }
    }
}