package com.ead.project.ourivesariarumor.presentation.product.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductCategoryItem(
    modifier: Modifier = Modifier,
    item : Pair<Long,String>,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    Column(
        modifier = modifier
            .border(
                BorderStroke(0.5.dp, MaterialTheme.colorScheme.surfaceVariant),
                shape
            )
            .background(MaterialTheme.colorScheme.surface, shape)
            .clip(shape)
            .clickable {  },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.second,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}