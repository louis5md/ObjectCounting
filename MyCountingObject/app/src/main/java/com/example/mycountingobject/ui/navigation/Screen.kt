package com.example.mycountingobject.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Counting : Screen("counting")
    object Setting : Screen("setting")
    object Profile : Screen("profile")
}