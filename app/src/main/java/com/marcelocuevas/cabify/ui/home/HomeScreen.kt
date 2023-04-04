package com.marcelocuevas.cabify.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marcelocuevas.cabify.ui.components.CabifySurface
import com.marcelocuevas.cabify.ui.components.CabifyTopAppBar
import com.marcelocuevas.cabify.ui.components.ShimmerGridItem
import com.marcelocuevas.cabify.ui.components.bottomsheet.SheetContentCollapsed
import com.marcelocuevas.cabify.uistate.HomeUiState
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

private val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp
//private val sheetPeekHeight = 92.dp
private val sheetPeekHeight = 0.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

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
        HomeContent(
            uiState = uiState.value
        )
    }
}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier

) {
    CabifySurface(modifier = modifier.fillMaxWidth()) {
        Box(modifier = modifier.padding(16.dp)) {
            ProductsGrid(
                uiState = uiState,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductsGrid(
    uiState: HomeUiState,
    modifier: Modifier = Modifier
){
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(170.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(uiState.products) {
            ShimmerGridItem(isLoading = false, contentAfterLoading = {
                ProductItemView(
                    product = it ,
                    index = 0,
                    gradient = CabifyTheme.colors.gradient6_1,
                    gradientWidth = gradientWidth ,
                    scroll = 0
                )
            }, modifier = modifier
                .fillMaxWidth()
                .padding(16.dp))
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    CabifyTheme {
        //HomeScreen()
    }
}


