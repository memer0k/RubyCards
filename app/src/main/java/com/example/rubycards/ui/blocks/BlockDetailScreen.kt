package com.example.rubycards.ui.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubycards.ui.blocks.content.IntroToRubyContent

@Composable
fun BlockDetailScreen(
    blockName: String,
    onBack: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) } // 0 = Теория, 1 = Практика

    val tabs = listOf("Теория", "Практика")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // --- HEADER ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.clickable { onBack() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад",
                    tint = Color(0xFFE53935)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Назад",
                    color = Color(0xFFE53935),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            IconButton(onClick = { isFavorite = !isFavorite }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "В избранное",
                    tint = if (isFavorite) Color(0xFFFFC107) else Color(0xFFE53935),
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        // --- НАЗВАНИЕ БЛОКА ---
        Text(
            text = blockName,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE53935),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // --- ВКЛАДКИ ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF5F5F5)),
            horizontalArrangement = Arrangement.Center
        ) {
            tabs.forEachIndexed { index, tab ->
                val isSelected = selectedTab == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) Color(0xFFE53935) else Color.Transparent)
                        .clickable { selectedTab = index }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab,
                        color = if (isSelected) Color.White else Color.Gray,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- КОНТЕНТ ---
        when (selectedTab) {
            0 -> IntroToRubyContent()  // <-- Подключаем новый файл
            1 -> PracticeContent()
        }
    }
}

@Composable
private fun PracticeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🔒 Практика",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Практика будет доступна после изучения теории",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}