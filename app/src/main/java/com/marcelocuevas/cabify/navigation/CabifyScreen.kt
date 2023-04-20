package com.marcelocuevas.cabify.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object OrderDetail : Screen("orderDetail")
    object Checkout : Screen("checkout")
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}
