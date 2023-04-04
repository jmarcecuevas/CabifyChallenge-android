package com.marcelocuevas.cabify.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

@Composable
fun InfoPill(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = CabifyTheme.colors.brandSecondary,
    textColor: Color = CabifyTheme.colors.textSecondary
){
    if (text.isNotEmpty()) {
        CabifyCard(
            modifier = modifier.padding(all = 16.dp),
            color = color,
            elevation = 0.dp,
            content = {
                Text(
                    text = text,
                    style = MaterialTheme.typography.caption,
                    color = textColor,
                    modifier = Modifier.padding(4.dp)
                )
            }
        )
    }
}

@Preview("dafault")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewInfoPill() {
    CabifyTheme {
        InfoPill(text = "Promotion")
    }
}
