package com.marcelocuevas.cabify.ui.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

private val BOTTOM_SHEET_HEIGHT = 78.dp

@Composable
fun SheetContentCollapsed(
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(BOTTOM_SHEET_HEIGHT)
            .background(CabifyTheme.colors.uiBackground),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
