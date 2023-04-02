package com.marcelocuevas.cabify.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelocuevas.cabify.ui.theme.AlphaNearOpaque
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

@Composable
fun CabifyTopAppBar(modifier: Modifier = Modifier) {
    Column(modifier = modifier.statusBarsPadding()) {
        TopAppBar(
            backgroundColor = CabifyTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
            contentColor = CabifyTheme.colors.textSecondary,
            elevation = 0.dp
        ) {
            Text(
                text = "Cabify Store",
                style = MaterialTheme.typography.h6,
                color = CabifyTheme.colors.textSecondary,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
        CabifyDivider()
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDestinationBar() {
    CabifyTheme {
        CabifyTopAppBar()
    }
}
