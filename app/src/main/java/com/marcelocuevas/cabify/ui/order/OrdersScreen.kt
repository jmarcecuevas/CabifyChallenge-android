package com.marcelocuevas.cabify.ui.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
import com.marcelocuevas.cabify.utils.formatPrice
import com.marcelocuevas.cabify.utils.promotionDescription

@Composable
fun OrdersRoute(
    modifier: Modifier = Modifier,
    viewModel: OrdersViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    OrderScreen(
        uiState = uiState,
        removeProduct = viewModel::removeOrder,
        onIncreaseClick = viewModel::onIncreaseItemClicked,
        onDecreaseClick = viewModel::onDecreaseItemClicked,
        modifier = modifier
    )
}

@Composable
private fun OrderScreen(
    uiState: OrdersUiState,
    removeProduct: (String) -> Unit,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    CabifySurface(modifier = modifier.fillMaxSize()) {
        Column {
            CabifyTopAppBar(
                title = stringResource(id = R.string.orders_screen_title),
            )
            OrderContent(
                uiState = uiState,
                removeProduct = removeProduct,
                onIncreaseClick = onIncreaseClick,
                onDecreaseClick = onDecreaseClick,
            )
        }
    }
}

@Composable
private fun OrderContent(
    uiState: OrdersUiState,
    removeProduct: (String) -> Unit,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is OrdersUiState.Loading -> { LoadingState() }
        is OrdersUiState.NoOrders -> { NoOrdersState() }
        is OrdersUiState.HasOrders -> {
            HasOrdersState(
                uiState = uiState,
                removeProduct = removeProduct,
                onIncreaseClick = onIncreaseClick,
                onDecreaseClick = onDecreaseClick,
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
    Column(
        modifier = modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.ic_empty_cart),
            contentDescription = stringResource(id = R.string.cart_empty_description),
            tint = CabifyTheme.colors.brand
        )
        Text(
            modifier = Modifier.padding(vertical = 7.dp),
            text = stringResource(id = R.string.cart_empty_title),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = CabifyTheme.colors.textSecondary
            )
        )
        Text(
            text = stringResource(id = R.string.cart_empty_description),
            minLines = 2,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = CabifyTheme.colors.uiBorder
            )
        )
    }
}

@Composable
private fun HasOrdersState(
    uiState: OrdersUiState.HasOrders,
    removeProduct: (String) -> Unit,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources
    val productCountFormattedString = remember(uiState.itemsAddedQuantity, resources) {
        resources.getQuantityString(
            R.plurals.cart_order_count,
            uiState.orders.size, uiState.itemsAddedQuantity
        )
    }
    LazyColumn(modifier) {
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
                removeProduct = removeProduct,
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
                    fontSize = 18.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                        .wrapContentWidth(Alignment.End)
                        .alignBy(LastBaseline)
                )
                Text(
                    text = formatPrice(uiState.total),
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 18.sp,
                    modifier = Modifier.alignBy(LastBaseline)
                )
            }
            CabifyDivider()
        }
    }
}
