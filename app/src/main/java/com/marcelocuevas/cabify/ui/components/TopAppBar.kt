package com.marcelocuevas.cabify.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelocuevas.cabify.ui.theme.AlphaNearOpaque
import com.marcelocuevas.cabify.ui.theme.CabifyTheme
import com.marcelocuevas.cabify.R

@Composable
fun CabifyTopAppBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
) {
    Column(modifier = modifier.statusBarsPadding()) {
        TopAppBar(
            backgroundColor = CabifyTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
            contentColor = CabifyTheme.colors.textSecondary,
            elevation = 0.dp
        ) {
            Text(
                text = title,
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
@Composable
fun PreviewDestinationBar() {
    CabifyTheme {
        CabifyTopAppBar()
    }
}
