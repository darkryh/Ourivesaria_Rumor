package com.ead.project.ourivesariarumor.presentation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ead.project.ourivesariarumor.presentation.home.components.RowItemsSection
import com.ead.project.ourivesariarumor.presentation.home.components.SearchSection
import com.ead.project.ourivesariarumor.presentation.main.components.RowItems

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val products = viewModel.products.collectAsState(initial = emptyList())

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
                items = products.value,
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
                items = products.value,
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
                items = products.value,
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