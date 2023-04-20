package com.marcelocuevas.cabify.ui.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marcelocuevas.cabify.ui.components.CabifySurface
import com.marcelocuevas.cabify.ui.components.CabifyTopAppBar
import com.marcelocuevas.cabify.ui.components.CabifyDivider
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.ui.components.CabifyCard
import com.marcelocuevas.cabify.ui.components.InformationView
import com.marcelocuevas.cabify.utils.formatPrice
import com.marcelocuevas.cabify.utils.promotionDescription

@Composable
fun OrdersRoute(
    modifier: Modifier = Modifier,
    onCheckoutClick: () -> Unit,
    viewModel: OrdersViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    OrderScreen(
        uiState = uiState,
        removeOrder = viewModel::removeOrder,
        onIncreaseClick = viewModel::onIncreaseItemClicked,
        onDecreaseClick = viewModel::onDecreaseItemClicked,
        onCheckoutClick = {
            viewModel.emptyTheCart()
            onCheckoutClick()
        },
        modifier = modifier
    )
}

@Composable
private fun OrderScreen(
    uiState: OrdersUiState,
    removeOrder: (String) -> Unit,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    onCheckoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CabifySurface(modifier = modifier.fillMaxSize()) {
        Column {
            CabifyTopAppBar(
                title = stringResource(id = R.string.orders_screen_title),
            )
            OrderContent(
                uiState = uiState,
                removeOrder = removeOrder,
                onIncreaseClick = onIncreaseClick,
                onDecreaseClick = onDecreaseClick,
                onCheckoutClick = onCheckoutClick
            )
        }
    }
}

@Composable
private fun OrderContent(
    uiState: OrdersUiState,
    removeOrder: (String) -> Unit,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    onCheckoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is OrdersUiState.Loading -> { LoadingState() }
        is OrdersUiState.NoOrders -> { NoOrdersState() }
        is OrdersUiState.HasOrders -> {
            HasOrdersState(
                uiState = uiState,
                removeOrder = removeOrder,
                onIncreaseClick = onIncreaseClick,
                onDecreaseClick = onDecreaseClick,
                onCheckoutClick = onCheckoutClick,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun LoadingState() {
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

@Composable
private fun NoOrdersState(
    modifier: Modifier = Modifier
) {
    InformationView(
        modifier = modifier,
        iconResId = R.drawable.ic_empty_cart,
        titleResId = R.string.cart_empty_title,
        descriptionResId = R.string.cart_empty_description,
        contentDescriptionResId = R.string.cart_empty_description
    )
}

@Composable
private fun HasOrdersState(
    uiState: OrdersUiState.HasOrders,
    removeOrder: (String) -> Unit,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    onCheckoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val defaultPadding = dimensionResource(R.dimen.padding_default)
    val resources = LocalContext.current.resources
    val productCountFormattedString = remember(uiState.itemsAddedQuantity, resources) {
        resources.getQuantityString(
            R.plurals.cart_order_count,
            uiState.itemsAddedQuantity, uiState.itemsAddedQuantity
        )
    }
    Column {
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                Text(
                    text = stringResource(R.string.cart_order_header, productCountFormattedString),
                    style = MaterialTheme.typography.h6,
                    fontSize = 18.sp,
                    color = CabifyTheme.colors.brand,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .heightIn(min = 56.dp)
                        .padding(horizontal = 24.dp, vertical = 4.dp)
                        .wrapContentHeight()
                )
            }
            items(uiState.orders) { order ->
                OrderItem(
                    order = order,
                    removeOrder = removeOrder,
                    onIncreaseClick = onIncreaseClick,
                    onDecreaseClick = onDecreaseClick,
                    showPriceWithoutDiscount = order.hasDiscount
                )
            }
            item {
                Column(modifier) {
                    Text(
                        text = stringResource(R.string.cart_summary_header),
                        style = MaterialTheme.typography.h6,
                        fontSize = 18.sp,
                        color = CabifyTheme.colors.brand,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .heightIn(min = 56.dp)
                            .wrapContentHeight()
                    )
                    Row(modifier = Modifier.padding(horizontal = 24.dp)) {
                        Text(
                            text = stringResource(R.string.cart_subtotal_label),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth(Alignment.Start)
                                .alignBy(LastBaseline)
                        )
                        Text(
                            text = formatPrice(uiState.subtotal),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.alignBy(LastBaseline)
                        )
                    }
                }
            }
            items(uiState.orders.filter { it.hasDiscount }) {
                Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
                    Text(
                        text = it.product.promotionDescription(),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                            .alignBy(LastBaseline)
                    )
                    Text(
                        text = "-${formatPrice(it.discountObtained)}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.alignBy(LastBaseline)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                CabifyDivider()
                Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
                    Text(
                        text = stringResource(R.string.cart_total_label),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Black,
                        color = CabifyTheme.colors.brand,
                        fontSize = 19.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = defaultPadding)
                            .wrapContentWidth(Alignment.End)
                            .alignBy(LastBaseline)
                    )
                    Text(
                        text = formatPrice(uiState.total),
                        fontWeight = FontWeight.Black,
                        color = CabifyTheme.colors.brand,
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = 19.sp,
                        modifier = Modifier.alignBy(LastBaseline)
                    )
                }
            }
        }
        CheckoutBar(
            uiState = uiState,
            onCheckoutClick = onCheckoutClick,
            modifier = modifier)
    }
}

@Composable
fun CheckoutBar(
    uiState: OrdersUiState.HasOrders,
    onCheckoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(CabifyTheme.colors.uiBackground),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val defaultPadding = dimensionResource(id = R.dimen.padding_default)
        CabifyCard(
            modifier
                .padding(defaultPadding)
                .height(78.dp)
                .fillMaxSize(),
            color = CabifyTheme.colors.brand
        ) {
            Box(
                modifier
                    .fillMaxSize()
                    .padding(start = defaultPadding, end = defaultPadding)
                    .clickable {
                        onCheckoutClick()
                    }
            ) {
                Text(
                    text = stringResource(id = R.string.checkout_button_text),
                    color = CabifyTheme.colors.textInteractive,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 15.sp,
                    modifier = modifier.align(Alignment.Center)
                )
                Text(
                    text = formatPrice(uiState.total),
                    color = CabifyTheme.colors.textInteractive,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    modifier = modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}
