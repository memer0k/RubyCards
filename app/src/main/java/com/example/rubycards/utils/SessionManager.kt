package com.example.rubycards.utils

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREFS_NAME = "ruby_cards_prefs"
    private const val KEY_AUTH_TOKEN = "auth_token"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_USER_EMAIL = "user_email"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveSession(context: Context, token: String, userName: String, userEmail: String) {
        getPrefs(context).edit().apply {
            putString(KEY_AUTH_TOKEN, token)
            putString(KEY_USER_NAME, userName)
            putString(KEY_USER_EMAIL, userEmail)
            apply()
        }
    }

    fun getAuthToken(context: Context): String? {
        return getPrefs(context).getString(KEY_AUTH_TOKEN, null)
    }

    fun getUserName(context: Context): String? {
        return getPrefs(context).getString(KEY_USER_NAME, "Пользователь")
    }

    fun getUserEmail(context: Context): String? {
        return getPrefs(context).getString(KEY_USER_EMAIL, null)
    }

    fun isLoggedIn(context: Context): Boolean {
        return getAuthToken(context) != null
    }

    fun logout(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}