package com.momens.android.core.designsystem.component.sectiontitle

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.component.dot.MomensDot
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensSectionTitle(
    title: String,
    count: String,
    modifier: Modifier = Modifier,
    isEmphasized: Boolean = false,
    @DrawableRes iconRes: Int? = null,
    iconColor: Color = MomensTheme.colors.primary100,
) {
    val textStyle = if (isEmphasized) MomensTheme.typography.bodyB14 else MomensTheme.typography.bodyM14
    val contentColor = if (isEmphasized) MomensTheme.colors.gray900 else MomensTheme.colors.gray700

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        iconRes?.let { res ->
            Icon(
                imageVector = ImageVector.vectorResource(id = res),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp),
            )
        }

        Text(
            text = title,
            style = textStyle,
            color = contentColor,
        )

        MomensDot(
            color = contentColor,
        )

        Text(
            text = count.toString(),
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
                count = 4.toString(),
            )

            MomensSectionTitle(
                title = "제목",
                count = 4.toString(),
                isEmphasized = true,
            )

            MomensSectionTitle(
                title = "제목",
                count = 4.toString(),
                isEmphasized = true,
                iconRes = R.drawable.ic_canceled,
            )
        }
    }
}
