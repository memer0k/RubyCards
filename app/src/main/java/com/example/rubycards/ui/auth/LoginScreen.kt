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
import com.example.rubycards.network.LoginRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }

    var showSuccess by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    val scale = remember { Animatable(0f) }

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

    // --- ЭКРАН УСПЕХА ---
    if (showSuccess) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isSuccess) {
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
                    text = "Добро пожаловать!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE53935),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Вы успешно вошли в RubyCards",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            } else {
                CircularProgressIndicator(
                    color = Color(0xFFE53935),
                    strokeWidth = 3.dp,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Входим в аккаунт...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
        return
    }

    // --- ОБЫЧНЫЙ ЭКРАН ВХОДА ---
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

        // Цвета для полей ввода — чёрный текст
        val inputColors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedBorderColor = Color(0xFFE53935),
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color(0xFFE53935),
            focusedLabelColor = Color(0xFFE53935),
            unfocusedLabelColor = Color.Gray
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it.trim() },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
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
            enabled = true,
            colors = inputColors,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
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

                resultMessage = ""
                focusManager.clearFocus()
                showSuccess = true
                isSuccess = false

                coroutineScope.launch {
                    try {
                        val request = LoginRequest(email, password)
                        val response = RetrofitClient.apiService.login(request)

                        if (response.isSuccessful) {
                            isSuccess = true
                        } else {
                            showSuccess = false
                            resultMessage = "Неверный Email или пароль"
                        }
                    } catch (e: Exception) {
                        showSuccess = false
                        resultMessage = "Ошибка подключения: ${e.localizedMessage}"
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateToRegister) {
            Text("Нет аккаунта? Зарегистрироваться")
        }
    }
}