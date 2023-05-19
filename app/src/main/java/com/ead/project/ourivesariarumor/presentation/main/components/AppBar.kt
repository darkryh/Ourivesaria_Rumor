package com.ead.project.ourivesariarumor.presentation.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    onNavigationIconClick : () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = { return@TopAppBar },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "toggle drawer")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview(){
    AppBar {}
}