package com.marcelocuevas.cabify.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Badge
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.ui.components.CabifyCard
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import com.marcelocuevas.cabify.utils.formatPrice

@Composable
fun OrderContentView(
    uiState: HomeUiState.Success,
    onOrderButtonClick: () -> Unit
) {
    val defaultPadding = dimensionResource(id = R.dimen.padding_default)
    CabifyCard(
        Modifier
            .padding(defaultPadding)
            .fillMaxSize(),
        color = CabifyTheme.colors.brand
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = defaultPadding, end = defaultPadding)
                .clickable { onOrderButtonClick() }
        ) {
            Text(
                text = stringResource(id = R.string.see_orders_label),
                color = CabifyTheme.colors.textInteractive,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            Text(
                text = formatPrice(uiState.subtotal),
                color = CabifyTheme.colors.textInteractive,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.CenterEnd)
            )

            BadgedBox(
                modifier = Modifier.align(Alignment.CenterStart),
                badge = {
                    Badge(
                        backgroundColor = CabifyTheme.colors.brandSecondary
                    ){
                        val badgeNumber = uiState.qtyItemsAdded.toString()
                        Text(
                            badgeNumber,
                            modifier = Modifier.semantics {
                                contentDescription = "New shopping cart"
                            }
                        )
                    }
                }) {
                Icon(
                    Icons.Filled.ShoppingCart,
                    tint = CabifyTheme.colors.uiBackground,
                    contentDescription = "Shopping cart"
                )
            }
        }
    }
}

@Preview
@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewOrderContentView() {
    val uiState = HomeUiState.Success(
        products = emptyList(),
        orders = emptyList()
    )
    CabifyTheme {
        OrderContentView(
            uiState = uiState,
            onOrderButtonClick = {}
        )
    }
}
