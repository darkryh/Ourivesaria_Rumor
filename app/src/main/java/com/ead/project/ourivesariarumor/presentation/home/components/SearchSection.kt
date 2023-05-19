package com.ead.project.ourivesariarumor.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ead.project.ourivesariarumor.presentation.main.components.SearcherLayoutField

@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        SearcherLayoutField(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClick
        )
    }
}