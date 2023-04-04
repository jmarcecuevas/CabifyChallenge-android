package com.marcelocuevas.cabify.framework

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart/{cart_id}") {
        fun createRoute(cart_id: String) = "cart/$cart_id"
    }
}

@Composable
fun rememberCabifyAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    CabifyAppState(navController, context)
}

class CabifyAppState(
    val navController: NavHostController,
    private val context: Context
) {


}