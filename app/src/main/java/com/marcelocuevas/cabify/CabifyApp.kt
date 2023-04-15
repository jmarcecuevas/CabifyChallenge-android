package com.marcelocuevas.cabify

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marcelocuevas.cabify.framework.CabifyAppState
import com.marcelocuevas.cabify.framework.Screen
import com.marcelocuevas.cabify.framework.rememberCabifyAppState
import com.marcelocuevas.cabify.ui.home.HomeRoute
import com.marcelocuevas.cabify.ui.home.HomeViewModel
import com.marcelocuevas.cabify.ui.order.OrdersRoute

@Composable
fun CabifyApp() {
    val navController = rememberNavController()
    CabifyNavHost(
        navController = navController
    )
}

@Composable
fun CabifyNavHost(
    appState: CabifyAppState = rememberCabifyAppState(),
    navController: NavHostController
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    CabifyTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeRoute(
                    viewModel = homeViewModel,
                    onOrderButtonClick = {
                        navController.navigate(Screen.OrderDetail.route)
                    }
                )
            }
            composable(Screen.OrderDetail.route) {
                OrdersRoute()
            }
        }
    }
}
