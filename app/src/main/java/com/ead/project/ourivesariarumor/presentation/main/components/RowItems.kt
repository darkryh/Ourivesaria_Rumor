package com.ead.project.ourivesariarumor.presentation.main.components

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ead.project.ourivesariarumor.app.data.util.system.launchActivity
import com.ead.project.ourivesariarumor.domain.model.Category
import com.ead.project.ourivesariarumor.domain.model.Product
import com.ead.project.ourivesariarumor.domain.model.CategoryItem
import com.ead.project.ourivesariarumor.presentation.home.components.CategoryItem
import com.ead.project.ourivesariarumor.presentation.home.components.ProductItem
import com.ead.project.ourivesariarumor.presentation.product.ProductActivity
import com.ead.project.ourivesariarumor.presentation.product.components.ProductCategoryItem

@Composable
fun RowItems(
    modifier: Modifier = Modifier,
    items : List<Any>,
    withItem : Dp= 0.dp,
    heightItem : Dp = 0.dp,
    reverseLayout: Boolean = false,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End
) {
    val context = LocalContext.current
    LazyRow(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
    ) {
        item { Spacer(modifier = Modifier.width(8.dp)) }
        items(items) { item ->
            when(item) {
                is Product -> {
                    ProductItem(
                        product = item,
                        onClick = {
                            context.launchActivity(
                                intent = Intent(
                                    context,
                                    ProductActivity::class.java
                                ).apply { putExtra(ProductActivity.PRODUCT_PREVIEW,item) }
                            )
                        },
                        modifier = Modifier
                            .width(withItem)
                            .height(heightItem)
                    )
                }
                is CategoryItem -> {
                    CategoryItem(
                        modifier = Modifier
                            .width(withItem)
                            .height(heightItem),
                        categoryItem = item,
                        onClick = {}
                    )
                }
                is Category -> {
                    CategoryItem(
                        modifier = Modifier
                            .width(withItem)
                            .height(heightItem),
                        category = item,
                        onClick = {}
                    )
                }
                is Pair<*, *> -> {
                    if (item.first is Long && item.second is String) {
                        ProductCategoryItem(
                            modifier = modifier
                                .width(withItem)
                                .height(heightItem),
                            item = Pair(item.first as Long,item.second as String)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}