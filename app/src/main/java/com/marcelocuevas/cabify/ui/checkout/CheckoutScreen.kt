package com.marcelocuevas.cabify.ui.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.marcelocuevas.cabify.R
import com.marcelocuevas.cabify.ui.components.CabifyTopAppBar
import com.marcelocuevas.cabify.ui.components.InformationView

@Composable
fun CheckoutRoute(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        CabifyTopAppBar(title = stringResource(id = R.string.checkout_topbar_title))
        InformationView(
            modifier = modifier,
            iconResId = R.drawable.ic_check,
            titleResId = R.string.checkout_title,
            descriptionResId = R.string.checkout_description,
            contentDescriptionResId = R.string.checkout_content_description
        )
    }
}