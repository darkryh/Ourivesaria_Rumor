package com.ead.project.ourivesariarumor.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ead.project.ourivesariarumor.presentation.main.components.RowItems

@Composable
fun RowItemsSection(
    title : String,
    items : List<Any>,
    modifier : Modifier = Modifier,
    withItem : Dp,
    heightItem : Dp
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                )
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "show all.",
                color = MaterialTheme.colorScheme.primary
            )
        }
        RowItems(modifier = modifier, items = items, withItem = withItem, heightItem = heightItem)
    }

}