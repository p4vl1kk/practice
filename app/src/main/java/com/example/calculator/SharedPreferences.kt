package com.example.calculator

import android.content.Context
import android.content.SharedPreferences

object ThemePreference {
    private const val PREFS_NAME = "theme_prefs"
    private const val THEME_KEY = "app_theme"

    fun getTheme(context: Context): Boolean {
        val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(THEME_KEY, false) // false по умолчанию (светлая тема)
    }

    fun setTheme(context: Context, isDarkTheme: Boolean) {
        val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(preferences.edit()) {
            putBoolean(THEME_KEY, isDarkTheme)
            apply()
        }
    }
}