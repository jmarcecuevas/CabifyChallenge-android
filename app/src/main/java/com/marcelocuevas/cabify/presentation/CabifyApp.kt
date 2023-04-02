package com.marcelocuevas.cabify.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcelocuevas.cabify.presentation.home.HomeScreen
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

@Composable
fun CabifyApp(
    appState: CabifyAppState = rememberCabifyAppState()
) {
    CabifyTheme {
        NavHost(
            navController = appState.navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
        }
    }
}
