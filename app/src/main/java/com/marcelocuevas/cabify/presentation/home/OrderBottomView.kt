package com.marcelocuevas.cabify.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcelocuevas.cabify.presentation.components.CabifyCard
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

@Composable
fun OrderContentView() {
    CabifyCard(
        Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 32.dp)
            .fillMaxSize(),
        color = CabifyTheme.colors.brand
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Ver mi pedido",
                color = CabifyTheme.colors.textInteractive,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            Text(
                text = "45.00 €",
                color = CabifyTheme.colors.textInteractive,
                style = MaterialTheme.typography.caption,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterEnd)
            )

            BadgedBox(
                modifier = Modifier.align(Alignment.CenterStart),
                badge = {
                    Badge(
                        modifier = Modifier.offset(y=10.dp),
                        backgroundColor = CabifyTheme.colors.brandSecondary
                    ){
                        val badgeNumber = "2"
                        Text(
                            badgeNumber,
                            modifier = Modifier.semantics {
                                contentDescription = " new shopping cart"
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
@Composable
fun Preview() {
    OrderContentView()
}