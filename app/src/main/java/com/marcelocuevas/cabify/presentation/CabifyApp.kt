package com.marcelocuevas.cabify.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcelocuevas.cabify.presentation.home.HomeScreen
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcelocuevas.cabify.presentation.home.HomeViewModel

@Composable
fun CabifyApp(
    appState: CabifyAppState = rememberCabifyAppState()
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    CabifyTheme {
        NavHost(
            navController = appState.navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreen(viewModel = homeViewModel)
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
        }
    }
}
