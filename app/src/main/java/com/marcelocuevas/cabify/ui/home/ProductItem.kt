package com.marcelocuevas.cabify.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.ui.components.*
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

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
    CabifyCard(
        modifier = modifier
            .padding(bottom = defaultPadding)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = defaultPadding, end = defaultPadding)

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
                ProductImage(
                    imageUrl = product.imageUrl,
                    contentDescription = null,
                    modifier = modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            InfoPill(text = product.promotionDescription)
            Text(
                text = product.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = CabifyTheme.colors.textSecondary,
                modifier = modifier.padding(horizontal = defaultPadding)
            )
            Spacer(modifier = modifier.height(4.dp))
            Row(Modifier.padding(bottom = defaultPadding)) {
                Text(
                    text = "${product.price} ${product.currency}",
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 18.sp,
                    color = CabifyTheme.colors.textHelp,
                    modifier = modifier
                        .padding(horizontal = defaultPadding)
                        .weight(1f)
                )
                QuantitySelector(
                    count = product.quantity,
                    decreaseItemCount = {
                        onDecreaseClick(product.code, product.quantity)},
                    increaseItemCount = {
                        onIncreaseClick(product.code, product.quantity)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    CabifySurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            placeholder = painterResource(R.drawable.placeholder),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewProductItemView() {
    CabifyTheme {
//        val product = Product("VOUCHER","Cabify Coffee Mug", "20.0", "20.00 €","€","2 x 1", "https://www.julieseatsandtreats.com/wp-content/uploads/2020/06/Rainbow-Ice-Cream-14-of-16.jpg")
//        ProductItemView(
//            product = product,
//            index = 0,
//            gradient = CabifyTheme.colors.gradient6_1,
//            gradientWidth = gradientWidth,
//            scroll = 0
//        )
    }
}

