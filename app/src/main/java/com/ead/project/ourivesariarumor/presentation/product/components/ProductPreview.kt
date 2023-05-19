package com.ead.project.ourivesariarumor.presentation.product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ead.project.ourivesariarumor.app.data.util.system.round
import com.ead.project.ourivesariarumor.domain.model.Product
import com.ead.project.ourivesariarumor.presentation.main.components.ImageLoader

@Composable
fun ProductPreview(
    product: Product,
    quantity : Int,
    modifier: Modifier = Modifier
) {
    val resultToPay = (product.price * quantity).round(2)
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ImageLoader(
                modifier = Modifier
                    .height(256.dp)
                    .width(256.dp),
                url = product.image?:""
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    horizontal = 32.dp
                ),
            reverseLayout = true
        ) {
            item {
                Text(
                    text = "${resultToPay}${product.currency.symbol}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}