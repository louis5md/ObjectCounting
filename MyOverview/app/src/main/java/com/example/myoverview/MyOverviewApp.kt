package com.example.myoverview

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myoverview.ui.component.BottomBar
import com.example.myoverview.ui.navigation.Screen
import com.example.myoverview.ui.screen.OverviewScreen
import com.example.myoverview.ui.screen.ProfileScreen
import com.example.myoverview.ui.screen.SettingScreen
import com.example.myoverview.ui.theme.MyOverviewTheme

@Composable
fun MyOverviewApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Home.route) {
                TopAppBar(title = { Text(text = "Overview") })
            }
        },
        bottomBar = {
            BottomBar(navController = navController)
            },
        modifier = modifier,
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                OverviewScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
            composable(Screen.Setting.route){
                SettingScreen()
            }
        }
    }
}


@Preview(
    name = "tampilan utama",
    showBackground = true)
@Composable
fun CountingObjectAppPreview() {
    MyOverviewTheme() {
        MyOverviewApp()
    }
}