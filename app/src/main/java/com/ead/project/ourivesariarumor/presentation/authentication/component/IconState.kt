package com.ead.project.ourivesariarumor.presentation.authentication.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun IconState(
    modifier: Modifier = Modifier,
    defaultIcon : ImageVector,
    icon : ImageVector?,
    isLoading : Boolean,
    tint : Color = LocalContentColor.current
) {
    if (isLoading) {
        CircularProgressIndicator(modifier = modifier)
    }
    else {
        Icon(
            modifier = modifier,
            imageVector = icon?:defaultIcon,
            contentDescription = null,
            tint = tint
        )
    }
}

@Composable
fun IconState(
    modifier: Modifier = Modifier,
    defaultPainter: Painter,
    painter : Painter?,
    isLoading : Boolean,
    tint : Color = LocalContentColor.current
) {
    if (isLoading) {
        CircularProgressIndicator(modifier = modifier)
    }
    else {
        Icon(
            modifier = modifier,
            painter = painter?:defaultPainter,
            contentDescription = null,
            tint = tint
        )
    }
}