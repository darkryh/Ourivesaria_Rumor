package com.ead.project.ourivesariarumor.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.domain.model.Category
import com.ead.project.ourivesariarumor.domain.model.CategoryItem

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryItem: CategoryItem,
    onClick : () -> Unit,
    shape : Shape = CircleShape
) {
    Box(
        modifier = modifier
            .border(
                BorderStroke(0.5.dp, MaterialTheme.colorScheme.surfaceVariant),
                shape
            )
            .background(MaterialTheme.colorScheme.surface, shape)
            .clip(shape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    painter = painterResource(id = categoryItem.drawableRes),
                    contentDescription = categoryItem.contentDescription
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = categoryItem.title,
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }

}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onClick : () -> Unit,
    shape : Shape = CircleShape
) {
    Box(
        modifier = modifier
            .border(
                BorderStroke(0.5.dp, MaterialTheme.colorScheme.surfaceVariant),
                shape
            )
            .background(MaterialTheme.colorScheme.surface, shape)
            .clip(shape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.ic_diamond),
                    contentDescription = category.description
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = category.title,
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }

}