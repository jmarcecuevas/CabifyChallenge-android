package com.marcelocuevas.cabify.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

internal val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ProductsGrid(
    uiState: HomeUiState,
    isRefreshing: Boolean,
    pullRefreshState: PullRefreshState,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
){
    when (uiState) {
        is HomeUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = CabifyTheme.colors.brand,
                    strokeWidth = 3.dp
                )
            }
        }
        is HomeUiState.Success -> {
            Box(
                Modifier.pullRefresh(pullRefreshState)
            ) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(170.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.products) {
                        ProductItem(
                            product = it,
                            onIncreaseClick = onIncreaseClick,
                            onDecreaseClick = onDecreaseClick,
                            index = 0,
                            gradient = CabifyTheme.colors.gradient3_2,
                            gradientWidth = gradientWidth ,
                            scroll = 0
                        )
                    }
                }
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    backgroundColor = CabifyTheme.colors.uiBackground
                )
            }
        }
    }
}
