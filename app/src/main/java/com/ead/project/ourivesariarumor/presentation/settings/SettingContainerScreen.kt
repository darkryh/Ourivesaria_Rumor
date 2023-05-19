package com.ead.project.ourivesariarumor.presentation.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingContainerScreen(
    modifier: Modifier,
    onFinishAction : () -> Unit,
    content: @Composable (Modifier) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { return@TopAppBar },
                navigationIcon = {
                    IconButton(onClick = onFinishAction) {
                        Icon(
                            imageVector =
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back Icon"
                        )
                    }
                }
            )},
        modifier = modifier
    ) {
        content(Modifier.padding(it).fillMaxSize())
    }
}