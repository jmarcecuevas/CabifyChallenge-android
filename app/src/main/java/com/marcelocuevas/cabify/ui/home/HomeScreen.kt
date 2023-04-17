package com.marcelocuevas.cabify.ui.home

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.ui.components.CabifySurface
import com.marcelocuevas.cabify.ui.components.CabifyTopAppBar
import com.marcelocuevas.cabify.ui.components.ConnectivityStatus
import com.marcelocuevas.cabify.ui.components.bottomsheet.SheetContentCollapsed
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

private val sheetPeekHeight = 0.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onOrderButtonClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    val pullRefreshState = rememberPullRefreshState(
        isRefreshing, { viewModel.refresh() })

    HomeScreen(
        uiState = uiState,
        isRefreshing = isRefreshing,
        pullRefreshState = pullRefreshState,
        onIncreaseClick = { productId, quantity ->
            viewModel.onIncreaseItemClicked(productId, quantity)
        },
        onDecreaseClick = { productId, quantity ->
            viewModel.onDecreaseItemClicked(productId, quantity)
        },
        onOrderButtonClick = onOrderButtonClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    isRefreshing: Boolean,
    pullRefreshState: PullRefreshState,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    onOrderButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    HandleBottomSheetState(
        uiState = uiState,
        bottomSheetState = bottomSheetScaffoldState
    )
    val sheetElevation = dimensionResource(id = R.dimen.sheet_elevation)

    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = CabifyTheme.colors.uiBackground,
        sheetElevation = sheetElevation,
        sheetGesturesEnabled = false,
        topBar = { CabifyTopAppBar() },
        sheetContent = {
            SheetContentCollapsed {
                if (uiState is HomeUiState.Success) {
                    OrderContentView(
                        uiState = uiState,
                        onOrderButtonClick = onOrderButtonClick
                    )
                }
            }
        },
        sheetPeekHeight = sheetPeekHeight,
        scaffoldState = bottomSheetScaffoldState
    ) {
        HomeContent(
            uiState = uiState,
            isRefreshing = isRefreshing,
            pullRefreshState = pullRefreshState,
            onIncreaseClick = onIncreaseClick,
            onDecreaseClick = onDecreaseClick
        )
    }
}

@OptIn(ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalCoroutinesApi::class)
@Composable
private fun HomeContent(
    uiState: HomeUiState,
    isRefreshing: Boolean,
    pullRefreshState: PullRefreshState,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val defaultPadding = dimensionResource(R.dimen.padding_default)
    CabifySurface(modifier = modifier.fillMaxWidth()) {
        Column {
            ConnectivityStatus()
            Box(modifier = modifier.padding(defaultPadding)) {
                ProductsGrid(
                    uiState = uiState,
                    isRefreshing = isRefreshing,
                    pullRefreshState = pullRefreshState,
                    onIncreaseClick = onIncreaseClick,
                    onDecreaseClick = onDecreaseClick
                )
            }
        }
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

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    CabifyTheme {
        HomeRoute(
            onOrderButtonClick = {}
        )
    }
}
