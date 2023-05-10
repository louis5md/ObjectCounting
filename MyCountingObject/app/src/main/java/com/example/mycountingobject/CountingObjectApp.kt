package com.example.mycountingobject

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycountingobject.ui.navigation.NavigationItem
import com.example.mycountingobject.ui.navigation.Screen
import com.example.mycountingobject.ui.screen.CountingScreen
import com.example.mycountingobject.ui.screen.OverviewScreen
import com.example.mycountingobject.ui.theme.MyCountingObjectTheme

@Composable
fun CountingObjectApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Overview.route) {
                TopAppBar(title = { Text(text = "Overview") })
            }
        },
        bottomBar = { BottomBar(navController = navController)},
        modifier = modifier,
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Overview.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Counting.route){
                CountingScreen()
            }
            composable(Screen.Overview.route){
                OverviewScreen()
            }
        }
    }
}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_overview),
                icon = Icons.Default.Home,
                screen = Screen.Overview
            ),
            NavigationItem(
                title = stringResource(R.string.menu_counting),
                icon = Icons.Default.Add,
                screen = Screen.Counting
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}


@Preview(
    name = "tampilan utama",
    showBackground = true)
@Composable
fun CountingObjectAppPreview() {
    MyCountingObjectTheme {
        CountingObjectApp()
    }
}