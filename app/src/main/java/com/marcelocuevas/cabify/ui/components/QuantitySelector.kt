package com.marcelocuevas.cabify.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

@Composable
fun QuantitySelector(
    count: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        CabifyGradientTintedIconButton(
            imageVector = Icons.Default.Remove,
            onClick = { decreaseItemCount() },
            contentDescription = stringResource(R.string.label_decrease),
            modifier = Modifier.align(Alignment.CenterVertically),
        )
        Crossfade(
            targetState = count,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "$it",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                color = CabifyTheme.colors.textPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(min = 18.dp)
            )
        }
        CabifyGradientTintedIconButton(
            imageVector = Icons.Default.Add,
            onClick = { increaseItemCount() },
            contentDescription = stringResource(R.string.label_increase),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview("default")
@Composable
fun QuantitySelectorPreview() {
    CabifyTheme {
        CabifySurface {
            QuantitySelector(1, {}, {})
        }
    }
}

@Preview("RTL")
@Composable
fun QuantitySelectorPreviewRtl() {
    CabifyTheme {
        CabifySurface {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                QuantitySelector(1, {}, {})
            }
        }
    }
}
