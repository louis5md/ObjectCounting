package com.example.mycountingobject

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import com.example.mycountingobject.ui.screen.ProfileScreen
import com.example.mycountingobject.ui.screen.SettingScreen
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
            if (currentRoute == Screen.Home.route) {
                TopAppBar(title = { Text(text = "Overview") })
            }
        },
        floatingActionButton = {
            if(currentRoute == Screen.Home.route){
                FloatingButton(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,
        bottomBar = {
            if (currentRoute != Screen.Counting.route) {
                BottomBar(navController = navController)
            }},
        modifier = modifier,
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Counting.route){
                CountingScreen()
            }
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


@Composable
private fun FloatingButton(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    FloatingActionButton(
        shape = CircleShape,
        onClick = {
            navController.navigate(Screen.Counting.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
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
                title = stringResource(R.string.menu_setting),
                icon = Icons.Default.Settings,
                screen = Screen.Setting
            ),
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
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