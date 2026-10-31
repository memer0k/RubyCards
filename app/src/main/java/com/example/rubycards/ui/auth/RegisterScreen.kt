package com.example.rubycards.ui.auth

import android.util.Patterns
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rubycards.ui.theme.RubyButton
import com.example.rubycards.network.RetrofitClient
import com.example.rubycards.network.RegisterRequest
import com.example.rubycards.network.RegisterUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }

    // showSuccess = запрос идёт ИЛИ успех
    var showSuccess by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) } // true когда запрос завершён успешно

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    // Анимация появления галочки
    val scale = remember { Animatable(0f) }

    // Переход на главный экран через 2 секунды после успеха
    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500)
            )
            delay(2000)
            onNavigateToMain()
        }
    }

    // --- ЭКРАН УСПЕХА (показывается сразу при нажатии кнопки) ---
    if (showSuccess) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isSuccess) {
                // Успех — показываем галочку
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE53935),
                    modifier = Modifier.size(96.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Успех",
                        tint = Color.White,
                        modifier = Modifier
                            .size(56.dp)
                            .padding(20.dp)
                            .scale(scale.value)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Регистрация успешна!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE53935),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Добро пожаловать в RubyCards",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            } else {
                // Запрос ещё идёт — показываем спиннер
                CircularProgressIndicator(
                    color = Color(0xFFE53935),
                    strokeWidth = 3.dp,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Создаём аккаунт...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
        return
    }

    // --- ОБЫЧНЫЙ ЭКРАН РЕГИСТРАЦИИ ---
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
            enabled = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it.trim() },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
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
            enabled = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (resultMessage.isNotEmpty()) {
            Text(
                text = resultMessage,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        RubyButton(
            text = "Создать аккаунт",
            onClick = {
                // Валидация
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

                // СРАЗУ показываем экран успеха со спиннером
                resultMessage = ""
                focusManager.clearFocus()
                showSuccess = true
                isSuccess = false

                coroutineScope.launch {
                    try {
                        val request = RegisterRequest(RegisterUser(name, email, password))
                        val response = RetrofitClient.apiService.register(request)

                        if (response.isSuccessful) {
                            isSuccess = true // Показываем галочку
                        } else {
                            // Ошибка — возвращаемся на экран регистрации
                            showSuccess = false
                            val errorBody = response.errorBody()?.string()
                            var errorMessage = "Ошибка регистрации"

                            if (errorBody != null) {
                                try {
                                    val jsonObject = JSONObject(errorBody)
                                    if (jsonObject.has("errors")) {
                                        val errorsArray = jsonObject.getJSONArray("errors")
                                        val messages = mutableListOf<String>()
                                        for (i in 0 until errorsArray.length()) {
                                            messages.add(errorsArray.getString(i))
                                        }
                                        errorMessage = messages.joinToString("\n")
                                    }
                                } catch (_: Exception) {
                                    errorMessage = "Ошибка: ${response.code()}"
                                }
                            }
                            resultMessage = errorMessage
                        }
                    } catch (e: Exception) {
                        showSuccess = false
                        resultMessage = "Ошибка подключения: ${e.localizedMessage}"
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateToLogin) {
            Text("Уже есть аккаунт? Войти")
        }
    }
}