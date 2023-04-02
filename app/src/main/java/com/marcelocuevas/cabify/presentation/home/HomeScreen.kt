package com.marcelocuevas.cabify.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelocuevas.cabify.data.Product
import com.marcelocuevas.cabify.presentation.components.CabifySurface
import com.marcelocuevas.cabify.presentation.components.CabifyTopAppBar
import com.marcelocuevas.cabify.presentation.components.bottomsheet.SheetContentCollapsed
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

private val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp
private val sheetPeekHeight = 92.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = CabifyTheme.colors.uiBackground,
        sheetElevation = 16.dp,
        sheetGesturesEnabled = false,
        topBar = { CabifyTopAppBar() },
        sheetContent = {
            SheetContentCollapsed {
                OrderContentView()
            }
        },
        sheetPeekHeight = sheetPeekHeight
    ) {
        ProductsGrid()
    }
}

@Composable
private fun ProductsGrid(modifier: Modifier = Modifier) {
    CabifySurface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = modifier.padding(16.dp)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(3) { product ->
                    val testProduct = Product("Cabify Coffee Mug", "20.00 €", "https://www.julieseatsandtreats.com/wp-content/uploads/2020/06/Rainbow-Ice-Cream-14-of-16.jpg")
                    ProductItemView(
                        product = testProduct,
                        index = 0,
                        gradient = CabifyTheme.colors.gradient6_1,
                        gradientWidth = gradientWidth,
                        scroll = 0
                    )
                }
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    CabifyTheme {
        HomeScreen()
    }
}


