package com.marcelocuevas.cabify.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.ui.theme.CabifyTheme

@Composable
fun InformationView(
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    @StringRes titleResId: Int,
    @StringRes descriptionResId: Int,
    @StringRes contentDescriptionResId: Int,
) {
    val defaultPadding = dimensionResource(R.dimen.padding_default)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CabifyTheme.colors.uiBackground)
            .padding(start = defaultPadding, end = defaultPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = modifier.size(100.dp),
            painter = painterResource(id = iconResId),
            contentDescription = stringResource(id = contentDescriptionResId),
            tint = CabifyTheme.colors.brand
        )
        Text(
            modifier = modifier.padding(vertical = 7.dp),
            text = stringResource(id = titleResId),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = CabifyTheme.colors.textSecondary
            )
        )
        Text(
            text = stringResource(id = descriptionResId),
            minLines = 2,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = CabifyTheme.colors.uiBorder
            )
        )
    }
}
