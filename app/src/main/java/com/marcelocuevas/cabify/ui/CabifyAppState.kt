package com.marcelocuevas.cabify.framework

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object OrderDetail : Screen("orderDetail") {
        //fun createRoute(order_id: String) = "order"
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