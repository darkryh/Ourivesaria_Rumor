package com.ead.project.ourivesariarumor.presentation.shopping_cart

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ead.project.ourivesariarumor.presentation.shopping_cart.components.ShoppingCartPlaceHolder

@Composable
fun ShoppingCartScreen(
    modifier: Modifier = Modifier
) {
    val tempList = listOf<String>()
    LazyColumn(
        modifier = modifier
    ) {

    }
    if (tempList.isEmpty()) {
        ShoppingCartPlaceHolder(
            modifier = modifier
        )
    }
}