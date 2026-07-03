package com.momens.android.core.designsystem.component.sectiontitle

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensSectionTitle(
    title: String,
    count: String,
    modifier: Modifier = Modifier,
    emphasized: Boolean = false,
    @DrawableRes iconRes: Int? = null,
) {
    val textStyle = if (emphasized) MomensTheme.typography.bodyB14 else MomensTheme.typography.bodyM14
    val contentColor = if (emphasized) MomensTheme.colors.gray900 else MomensTheme.colors.gray700

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        iconRes?.let { res ->
            Icon(
                imageVector = ImageVector.vectorResource(id = res),
                contentDescription = null,
                tint = MomensTheme.colors.primary100,
                modifier = Modifier.size(24.dp),
            )
        }

        Text(
            text = title,
            style = textStyle,
            color = contentColor,
        )

        Box(
            modifier = Modifier
                .size(2.dp)
                .background(
                    color = contentColor,
                    shape = CircleShape,
                ),
        )

        Text(
            text = count,
            style = textStyle,
            color = contentColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensSectionTitlePreview() {
    MomensTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MomensSectionTitle(
                title = "제목",
                count = "4",
            )

            MomensSectionTitle(
                title = "제목",
                count = "4",
                emphasized = true,
            )

            MomensSectionTitle(
                title = "제목",
                count = "4",
                emphasized = true,
                iconRes = R.drawable.ic_canceled,
            )
        }
    }
}
