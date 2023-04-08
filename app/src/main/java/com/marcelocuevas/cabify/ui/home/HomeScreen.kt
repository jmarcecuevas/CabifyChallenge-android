package com.marcelocuevas.cabify.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marcelocuevas.cabify.ui.components.CabifySurface
import com.marcelocuevas.cabify.ui.components.CabifyTopAppBar
import com.marcelocuevas.cabify.ui.components.ShimmerGridItem
import com.marcelocuevas.cabify.ui.components.bottomsheet.SheetContentCollapsed
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.network.Result

private val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp
//private val sheetPeekHeight = 92.dp
private val sheetPeekHeight = 0.dp

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        viewModel = viewModel,
        uiState = uiState,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreen(
    viewModel: HomeViewModel,
    uiState: Result<List<Product>>,
    modifier: Modifier = Modifier
) {
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
            uiState = uiState
        )
    }
}

@Composable
private fun HomeContent(
    uiState: Result<List<Product>>,
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
    uiState: Result<List<Product>>,
    modifier: Modifier = Modifier
){
    var isLoading by remember { mutableStateOf(true) }
    when (uiState) {
        is Result.Loading -> {}
        is Result.Success -> {
        isLoading = false
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(170.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.data?.size ?: 3) {
                    ShimmerGridItem(isLoading = uiState.data == null, contentAfterLoading = {
                        ProductItemView(
                            product = uiState.data!![it],
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
        is Result.Error -> print("")
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


