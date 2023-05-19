package com.ead.project.ourivesariarumor.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.transform.RoundedCornersTransformation
import com.ead.project.ourivesariarumor.domain.model.Product
import com.ead.project.ourivesariarumor.presentation.main.components.ImageLoader

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    cornersDp : Dp = 16.dp
) {
    val dpToFloat = with(LocalDensity.current) { cornersDp.toPx() }
    Column(
        modifier = modifier
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(cornersDp))
            .clip(RoundedCornerShape(cornersDp))
            .clickable(onClick = onClick),
    ) {
        ImageLoader(
            modifier = Modifier
                .fillMaxWidth()
                .weight(9f)
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(cornersDp))
                .padding(32.dp),
            url = product.image?:"",
            builder = { transformations(RoundedCornersTransformation(dpToFloat)) },
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                )
                .weight(2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f)
            )
            Text(
                text = "${product.currency.symbol}${product.price}",
                fontSize = 18.sp
            )
        }
    }
}