package com.marcelocuevas.cabify.ui.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.ui.components.CabifyDivider
import com.marcelocuevas.cabify.ui.components.CabifyImage
import com.marcelocuevas.cabify.ui.components.QuantitySelector
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import com.marcelocuevas.cabify.utils.formatPrice
import com.marcelocuevas.cabify.utils.imageUrl
import com.marcelocuevas.cabify.utils.promotionDescription

@Composable
fun OrderItem(
    order: OrderItemAndProduct,
    showPriceWithoutDiscount: Boolean,
    removeOrder: (String) -> Unit,
    onIncreaseClick: (String, Int) -> Unit,
    onDecreaseClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val product = order.product
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(CabifyTheme.colors.uiBackground)
            .padding(horizontal = 24.dp)

    ) {
        val (divider, image, name, promotion, priceSpacer, price, oldPrice, remove, quantity) = createRefs()
        createVerticalChain(name, promotion, priceSpacer, price, chainStyle = ChainStyle.Packed)
        CabifyImage(
            imageUrl = product.imageUrl(),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 4.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = product.name,
            style = MaterialTheme.typography.subtitle1,
            fontSize = 17.sp,
            color = CabifyTheme.colors.textSecondary,
            modifier = Modifier.constrainAs(name) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = remove.start,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )
        IconButton(
            onClick = { removeOrder(product.code) },
            modifier = Modifier
                .constrainAs(remove) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                tint = CabifyTheme.colors.iconSecondary,
                contentDescription = stringResource(id = R.string.remove_icon_content_description)
            )
        }
        Text(
            text = product.promotionDescription(),
            style = MaterialTheme.typography.body1,
            color = CabifyTheme.colors.textHelp,
            modifier = Modifier.constrainAs(promotion) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = parent.end,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )
        Spacer(
            Modifier
                .height(8.dp)
                .constrainAs(priceSpacer) {
                    linkTo(top = promotion.bottom, bottom = price.top)
                }
        )
        Text(
            text = formatPrice(order.total),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.SemiBold,
            color = CabifyTheme.colors.textPrimary,
            modifier = Modifier.constrainAs(price) {
                linkTo(
                    start = image.end,
                    end = price.end,
                    startMargin = 16.dp,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )
        if (showPriceWithoutDiscount) {
            Text(
                text = formatPrice(order.subtotal),
                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                color = CabifyTheme.colors.uiBorder,
                modifier = Modifier.constrainAs(oldPrice) {
                    baseline.linkTo(price.baseline)
                    start.linkTo(price.end)
                    end.linkTo(quantity.start)
                }
            )
        }
        QuantitySelector(
            count = product.quantity,
            decreaseItemCount = { onDecreaseClick(product.code, product.quantity) },
            increaseItemCount = { onIncreaseClick(product.code, product.quantity) },
            modifier = Modifier.constrainAs(quantity) {
                baseline.linkTo(price.baseline)
                end.linkTo(parent.end)
            }
        )
        CabifyDivider(
            Modifier.constrainAs(divider) {
                linkTo(start = parent.start, end = parent.end)
                top.linkTo(parent.bottom)
            }
        )
    }
}

