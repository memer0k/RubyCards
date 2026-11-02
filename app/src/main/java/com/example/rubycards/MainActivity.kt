package com.example.rubycards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.rubycards.ui.auth.LoginScreen
import com.example.rubycards.ui.auth.RegisterScreen
import com.example.rubycards.ui.blocks.BlockDetailScreen
import com.example.rubycards.ui.main.MainScreen
import com.example.rubycards.ui.profile.ProfileScreen
import com.example.rubycards.ui.settings.SettingsScreen
import com.example.rubycards.ui.theme.RubyCardsTheme
import com.example.rubycards.utils.SessionManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RubyCardsTheme {
                val isLoggedIn = SessionManager.isLoggedIn(this)

                var currentScreen by remember {
                    mutableStateOf(if (isLoggedIn) "main" else "login")
                }
                // Переменная для хранения названия выбранного блока
                var selectedBlockName by remember { mutableStateOf("") }

                when (currentScreen) {
                    "login" -> LoginScreen(
                        onNavigateToRegister = { currentScreen = "register" },
                        onLoginSuccess = { currentScreen = "main" }
                    )
                    "register" -> RegisterScreen(
                        onNavigateToLogin = { currentScreen = "login" },
                        onRegisterSuccess = { currentScreen = "main" }
                    )
                    "main" -> MainScreen(
                        onLogout = {
                            SessionManager.logout(this)
                            currentScreen = "login"
                        },
                        onProfileClick = { currentScreen = "profile" },
                        onSettingsClick = { currentScreen = "settings" },
                        onBlockClick = { blockName ->
                            selectedBlockName = blockName
                            currentScreen = "block_detail"
                        }
                    )
                    "profile" -> ProfileScreen(
                        onBack = { currentScreen = "main" }
                    )
                    "settings" -> SettingsScreen(
                        onBack = { currentScreen = "main" }
                    )
                    "block_detail" -> BlockDetailScreen(
                        blockName = selectedBlockName,
                        onBack = { currentScreen = "main" }
                    )
                }
            }
        }
    }
}