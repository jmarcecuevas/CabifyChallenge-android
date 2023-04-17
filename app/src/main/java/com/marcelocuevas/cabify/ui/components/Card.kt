package com.marcelocuevas.cabify.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

@Composable
fun CabifyCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    color: Color = CabifyTheme.colors.uiBackground,
    contentColor: Color = CabifyTheme.colors.textPrimary,
    border: BorderStroke? = null,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    CabifySurface(
        modifier = modifier,
        shape = shape,
        color = color,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        content = content
    )
}

@Preview("default")
@Composable
private fun CardPreview() {
    CabifyTheme {
        CabifyCard {
            Text(text = "Demo", modifier = Modifier.padding(16.dp))
        }
    }
}
