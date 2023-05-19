package com.ead.project.ourivesariarumor.presentation.product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.domain.model.Product

@Composable
fun ProductDetailsPreview(
    product: Product,
    modifier: Modifier = Modifier,
    quantity : Int,
    onMinusAction : () -> Unit,
    onPlusAction : () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            )
            .padding(32.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(8f)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = product.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .weight(1f),
                text = product.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Column(
            modifier = Modifier
                .weight(6f)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(32.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = onMinusAction) {
                    Icon(
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp),
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseSurface
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = quantity.toString(),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = onPlusAction) {
                    Icon(
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp),
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseSurface
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Cart")
            }
        }
    }

}