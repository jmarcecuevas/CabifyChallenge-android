package com.marcelocuevas.cabify.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.ui.components.*
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import com.marcelocuevas.cabify.utils.formatPrice
import com.marcelocuevas.cabify.utils.imageUrl
import com.marcelocuevas.cabify.utils.promotionDescription

private val CardWidth = 170.dp
private val CardPadding = 16.dp

@Composable
fun ProductItem(
    product: Product,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    index: Int,
    gradient: List<Color>,
    gradientWidth: Float,
    scroll: Int,
    modifier: Modifier = Modifier
) {
    val left = index * with(LocalDensity.current) {
        (CardWidth + CardPadding).toPx()
    }
    val defaultPadding = dimensionResource(id = R.dimen.padding_default)
    CabifyCard(modifier = modifier.padding(bottom = defaultPadding)) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = left - (scroll / 3f)
                Box(
                    modifier = modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .offsetGradientBackground(gradient, gradientWidth, gradientOffset)
                )
                CabifyImage(
                    imageUrl = product.imageUrl(),
                    contentDescription = null,
                    modifier = modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = defaultPadding, end = defaultPadding)
            ) {
                InfoPill(text = product.promotionDescription())
                Text(
                    text = product.name,
                    maxLines = 2,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1,
                    color = CabifyTheme.colors.textSecondary,
                )
                Spacer(modifier = modifier.height(4.dp))
                Row(Modifier.padding(bottom = defaultPadding)) {
                    Text(
                        text = formatPrice(product.price),
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        color = CabifyTheme.colors.textPrimary,
                        modifier = modifier
                            .weight(1f)
                    )
                    QuantitySelector(
                        count = product.quantity,
                        decreaseItemCount = {
                            onDecreaseClick(product.code, product.quantity)
                        },
                        increaseItemCount = {
                            onIncreaseClick(product.code, product.quantity)
                        }
                    )
                }
            }
        }
    }
}

@Preview("default")
@Composable
private fun PreviewProductItemView() {
    CabifyTheme {
        val testProduct = Product(
            code = "VOUCHER",
            name = "Cabify Voucher",
            price = 5.00,
            quantity = 0
        )
        ProductItem(
            product = testProduct,
            index = 0,
            gradient = CabifyTheme.colors.gradient21,
            gradientWidth = gradientWidth,
            scroll = 0,
            onDecreaseClick = { _, _ ->},
            onIncreaseClick = { _,_ ->  }
        )
    }
}
