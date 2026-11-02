package com.example.rubycards.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubycards.utils.SessionManager

// Модель блока
data class BlockItem(
    val id: Int,
    val title: String,
    val description: String
)

@Composable
fun MainScreen(onLogout: () -> Unit) {
    val context = LocalContext.current
    val userName = SessionManager.getUserName(context)

    // Список блоков
    val blocks = listOf(
        BlockItem(1, "Введение в руби", "Основы языка Ruby и его особенности"),
        BlockItem(2, "Основы Ruby", "Переменные, методы, классы и объекты"),
        BlockItem(3, "Работа с данными", "Массивы, хэши, файлы и базы данных"),
        BlockItem(4, "Веб-разработка", "Rails, Sinatra и создание веб-приложений"),
        BlockItem(5, "Практика", "Реальные проекты и задачи")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header с приветствием
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Привет, $userName!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE53935),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Выбери блок для изучения",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Блоки
        blocks.forEach { block ->
            BlockCard(
                block = block,
                onClick = {
                    // Пока ничего не делаем — добавим навигацию в следующей части
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка выхода
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))
        ) {
            Text("Выйти из аккаунта", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Компонент карточки блока
@Composable
fun BlockCard(block: BlockItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = block.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = block.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}