package com.example.rubycards.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rubycards.ui.theme.BackgroundGray
import com.example.rubycards.ui.theme.RubyButton
import com.example.rubycards.ui.theme.RubyCard

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundGray).padding(16.dp)
    ) {
        Text(
            text = "Мои курсы",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            item {
                RubyCard {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Основы Ruby", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Уроков: 6-8", style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(16.dp))

                        RubyButton(text = "Продолжить обучение", onClick = { /* TODO */ })
                    }
                }
            }
        }
    }
}