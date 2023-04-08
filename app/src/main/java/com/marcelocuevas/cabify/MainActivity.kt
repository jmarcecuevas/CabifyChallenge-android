package com.marcelocuevas.cabify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.marcelocuevas.cabify.CabifyApp
import com.marcelocuevas.cabify.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CabifyApp()
        }

//        viewModel.fetchProducts()
//
//        viewModel.productsData.observe(this) {
//            Log.e("asd", it.toString())
//        }
    }
}
