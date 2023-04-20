package com.marcelocuevas.cabify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marcelocuevas.cabify.ui.checkout.CheckoutRoute
import com.marcelocuevas.cabify.ui.home.HomeRoute
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
    navController: NavHostController
) {
    CabifyTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeRoute(
                    onOrderButtonClick = {
                        navController.navigate(Screen.OrderDetail.route)
                    }
                )
            }
            composable(Screen.OrderDetail.route) {
                OrdersRoute(
                    onCheckoutClick = {
                        navController.navigate(Screen.Checkout.route) {
                            popUpToTop(navController)
                        }
                    }
                )
            }
            composable(Screen.Checkout.route) {
                CheckoutRoute()
            }
        }
    }
}
