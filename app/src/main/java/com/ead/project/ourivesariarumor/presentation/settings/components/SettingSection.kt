package com.ead.project.ourivesariarumor.presentation.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingSection(
    modifier: Modifier,
    title : String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        Text(text = title)
        Surface(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp)
                .height(1.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            content = {return@Surface}
        )
    }
}