package com.ead.project.ourivesariarumor.presentation.login.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ead.project.ourivesariarumor.R

@Composable
fun LoginBackground(
    modifier: Modifier = Modifier,
    tint : Color = MaterialTheme.colorScheme.inverseSurface
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (icon1,icon2,icon3,
            icon4,icon5,icon6,
            icon7,icon8,icon9,icon10,
            icon11,icon12,icon13) = createRefs()

        Icon(
            modifier = Modifier
                .constrainAs(icon1) {
                    top.linkTo(parent.top, margin = 64.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                },
            painter = painterResource(id = R.drawable.ic_diamond_ring),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon2) {
                    top.linkTo(parent.top, margin = 48.dp)
                    end.linkTo(parent.end, margin = 45.dp)
                },
            painter = painterResource(id = R.drawable.ic_necklace),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon3) {
                    top.linkTo(icon1.bottom, margin = 32.dp)
                    start.linkTo(icon1.end, margin = 48.dp)
                },
            painter = painterResource(id = R.drawable.ic_jewelry),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon4) {
                    top.linkTo(icon2.bottom, margin = 164.dp)
                    end.linkTo(icon2.start, margin = 8.dp)
                },
            painter = painterResource(id = R.drawable.ic_diamond_accuracy),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon5) {
                    bottom.linkTo(parent.bottom, margin = 48.dp)
                    start.linkTo(parent.start, margin = 56.dp)
                },
            painter = painterResource(id = R.drawable.ic_earrings),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon6) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                },
            painter = painterResource(id = R.drawable.ic_diamond),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon7) {
                    bottom.linkTo(icon5.top, margin = 32.dp)
                    start.linkTo(icon5.end, margin = 48.dp)
                },
            painter = painterResource(id = R.drawable.ic_wristwatch),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon8) {
                    bottom.linkTo(icon6.top, margin = 48.dp)
                    end.linkTo(icon6.start, margin = 64.dp)
                },
            painter = painterResource(id = R.drawable.ic_bangle),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon9) {
                    bottom.linkTo(icon7.top, margin = 64.dp)
                    end.linkTo(icon7.end, margin = 48.dp)
                },
            painter = painterResource(id = R.drawable.ic_jewlery_star),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon10) {
                    bottom.linkTo(icon6.top, margin = 56.dp)
                    end.linkTo(icon6.end, margin = 16.dp)
                },
            painter = painterResource(id = R.drawable.ic_wedding_rings),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon11) {
                    bottom.linkTo(icon8.top, margin = 86.dp)
                    start.linkTo(icon8.end, margin = 16.dp)
                },
            painter = painterResource(id = R.drawable.ic_pearls),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon12) {
                    top.linkTo(icon3.bottom, margin = 48.dp)
                    end.linkTo(icon3.end, margin = 32.dp)
                },
            painter = painterResource(id = R.drawable.ic_bracelet),
            contentDescription = null,
            tint = tint
        )
        Icon(
            modifier = Modifier
                .constrainAs(icon13) {
                    bottom.linkTo(icon4.top, margin = 48.dp)
                    end.linkTo(icon4.start, margin = 0.dp)
                },
            painter = painterResource(id = R.drawable.ic_gem),
            contentDescription = null,
            tint = tint
        )
    }
}