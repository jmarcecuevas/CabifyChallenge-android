package com.marcelocuevas.cabify.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marcelocuevas.cabify.ui.components.CabifySurface
import com.marcelocuevas.cabify.ui.components.CabifyTopAppBar
import com.marcelocuevas.cabify.ui.components.bottomsheet.SheetContentCollapsed
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

private val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp
private val sheetPeekHeight = 0.dp

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        onIncreaseClick = { productId, quantity ->
            viewModel.onIncreaseItemClicked(productId, quantity)
        },
        onDecreaseClick = { productId, quantity ->
            viewModel.onDecreaseItemCount(productId, quantity)
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    HandleBottomSheetState(
        uiState = uiState,
        bottomSheetState = bottomSheetScaffoldState
    )

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = CabifyTheme.colors.uiBackground,
        sheetElevation = 16.dp,
        sheetGesturesEnabled = false,
        topBar = { CabifyTopAppBar() },
        sheetContent = {
            SheetContentCollapsed {
                if (uiState is HomeUiState.Success) {
                    OrderContentView(uiState)
                }
            }
        },
        sheetPeekHeight = sheetPeekHeight,
        scaffoldState = bottomSheetScaffoldState
    ) {
        HomeContent(
            uiState = uiState,
            bottomSheetState = bottomSheetScaffoldState,
            onIncreaseClick = onIncreaseClick,
            onDecreaseClick = onDecreaseClick
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HandleBottomSheetState(
    uiState: HomeUiState,
    bottomSheetState: BottomSheetScaffoldState
) {
    if (uiState is HomeUiState.Success) {
        LaunchedEffect(uiState) {
            when (uiState.shouldShowOrdersBottomSheet) {
                true -> {
                    if (bottomSheetState.bottomSheetState.isCollapsed) {
                        bottomSheetState.bottomSheetState.expand()
                    }
                }
                false -> {
                    if (bottomSheetState.bottomSheetState.isExpanded) {
                        bottomSheetState.bottomSheetState.collapse()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeContent(
    uiState: HomeUiState,
    bottomSheetState: BottomSheetScaffoldState,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier

) {
    CabifySurface(modifier = modifier.fillMaxWidth()) {
        Box(modifier = modifier.padding(16.dp)) {
            ProductsGrid(
                uiState = uiState,
                bottomSheetState = bottomSheetState,
                modifier = modifier,
                onIncreaseClick = onIncreaseClick,
                onDecreaseClick = onDecreaseClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
private fun ProductsGrid(
    uiState: HomeUiState,
    bottomSheetState: BottomSheetScaffoldState,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    when (uiState) {
        is HomeUiState.Loading -> {
            print("asd")
        }
        is HomeUiState.Success -> {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(170.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.products) {
                    ProductItemView(
                        product = it,
                        onIncreaseClick = onIncreaseClick,
                        onDecreaseClick = onDecreaseClick,
                        index = 0,
                        gradient = CabifyTheme.colors.gradient3_1,
                        gradientWidth = gradientWidth ,
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
        //HomeScreen()
    }
}


