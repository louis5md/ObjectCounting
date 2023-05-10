package com.example.mycountingobject.ui.navigation

sealed class Screen(val route: String) {
    object Overview : Screen("overview")
    object Counting : Screen("counting")
}