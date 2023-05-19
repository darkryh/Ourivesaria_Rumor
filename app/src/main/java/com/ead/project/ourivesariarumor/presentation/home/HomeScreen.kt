package com.ead.project.ourivesariarumor.presentation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ead.project.ourivesariarumor.presentation.home.components.RowItemsSection
import com.ead.project.ourivesariarumor.presentation.home.components.SearchSection
import com.ead.project.ourivesariarumor.presentation.main.components.RowItems

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            SearchSection(
                modifier = Modifier
                    .padding(
                        vertical = 16.dp,
                        horizontal = 16.dp
                    ),
                onClick = {

                }
            )
            RowItems(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(82.dp),
                items = categories,
                heightItem = 64.dp,
                withItem = 160.dp
            )
            RowItemsSection(
                title = "Top products",
                items = topProductsItems,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(
                        vertical = 8.dp
                    ),
                withItem = 200.dp,
                heightItem = 250.dp
            )
            RowItemsSection(
                title = "New products",
                items = topProductsItems,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(
                        vertical = 8.dp
                    ),
                withItem = 200.dp,
                heightItem = 250.dp
            )
            RowItemsSection(
                title = "Refilled products",
                items = topProductsItems,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(
                        vertical = 8.dp
                    ),
                withItem = 200.dp,
                heightItem = 250.dp
            )
        }
    }
}