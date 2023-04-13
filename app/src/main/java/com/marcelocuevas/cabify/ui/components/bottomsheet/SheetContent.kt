package com.marcelocuevas.cabify.ui.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

@Composable
fun SheetContentCollapsed(
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .background(CabifyTheme.colors.uiBackground),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
