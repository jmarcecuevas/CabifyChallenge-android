package com.marcelocuevas.cabify.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = modifier.padding(top = 16.dp, bottom = 16.dp),
            color = color,
            elevation = 0.dp,
            content = {
                Text(
                    text = text,
                    style = MaterialTheme.typography.caption,
                    fontSize = 13.sp,
                    color = textColor,
                    modifier = Modifier.padding(4.dp)
                )
            }
        )
    }
}

@Preview("dafault")
@Composable
fun PreviewInfoPill() {
    CabifyTheme {
        InfoPill(text = "Promotion")
    }
}
