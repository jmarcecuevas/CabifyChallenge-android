package com.marcelocuevas.cabify.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object OrderDetail : Screen("orderDetail")
}
