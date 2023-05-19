package com.ead.project.ourivesariarumor.presentation.register.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import java.time.LocalDate


@Composable
fun DateField(
    hint : String,
    date: LocalDate?,
    modifier: Modifier = Modifier,
    onFocusChange : (FocusState) -> Unit
) {
    Box(
        modifier = modifier
            .defaultMinSize(
                minHeight = 48.dp
            )
            .onFocusChanged { onFocusChange(it) }
            .border(BorderStroke(1.dp,MaterialTheme.colorScheme.inverseSurface), RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .padding(
                horizontal = 16.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text =
            if (date != null) "${date.dayOfMonth}/" +
                    "${date.monthValue}/" +
                    "${date.year}"
            else hint
        )
    }
}