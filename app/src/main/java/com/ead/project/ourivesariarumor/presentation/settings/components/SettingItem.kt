package com.ead.project.ourivesariarumor.presentation.settings.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingItem(
    modifier : Modifier,
    title : String,
    description : String,
    contentDescription : String?,
    @DrawableRes drawableId : Int,
    shape: Shape = RoundedCornerShape(32.dp),
    onClick : () -> Unit
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, shape)
            .clip(shape)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, CircleShape)
                .padding(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp),
                painter = painterResource(id = drawableId),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}