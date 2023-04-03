package com.marcelocuevas.cabify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcelocuevas.cabify.presentation.CabifyApp
import com.marcelocuevas.cabify.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CabifyApp()
        }

        viewModel.fetchProducts()

        viewModel.productsData.observe(this) {
            Log.e("asd", it.toString())
        }


    }
}
