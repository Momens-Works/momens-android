package com.momens.android.presentation.signal.component.detail.accodion

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun SignalAccordionHeader(
    title: String,
    time: String,
    expanded: Boolean,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 90f else -90f,
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.gray200,
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = if (expanded) 0.dp else 8.dp,
                    bottomEnd = if (expanded) 0.dp else 8.dp,
                ),
            )
            .noRippleClickable(onClick = onClick)
            .padding(
                horizontal = 18.dp,
                vertical = 8.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = title,
            color = MomensTheme.colors.gray900,
            style = MomensTheme.typography.bodyB14,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = time,
            color = MomensTheme.colors.gray500,
            style = MomensTheme.typography.bodyB12,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(R.drawable.ic_next),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .rotate(rotation),
            tint = MomensTheme.colors.black,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalAccordionHeaderPreview() {
    MomensTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SignalAccordionHeader(
                title = "피그마",
                time = "30분 전",
                expanded = false,
                iconResId = R.drawable.ic_figma,
                onClick = {},
            )

            SignalAccordionHeader(
                title = "피그마",
                time = "30분 전",
                expanded = true,
                iconResId = R.drawable.ic_figma,
                onClick = {},
            )
        }
    }
}
