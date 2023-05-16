package com.example.myoverview.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Setting : Screen("setting")
    object Profile : Screen("profile")
}