package com.marcelocuevas.cabify

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcelocuevas.cabify.framework.CabifyAppState
import com.marcelocuevas.cabify.framework.CartScreen
import com.marcelocuevas.cabify.framework.Screen
import com.marcelocuevas.cabify.framework.rememberCabifyAppState
import com.marcelocuevas.cabify.ui.home.HomeRoute
import com.marcelocuevas.cabify.ui.home.HomeViewModel

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
                HomeRoute(viewModel = homeViewModel)
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
        }
    }
}
