package com.momens.android.core.designsystem.component.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.button.MomensButton
import com.momens.android.core.designsystem.component.type.MomensButtonType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun PeopleListItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val backgroundColor =
        if (isSelected) MomensTheme.colors.primary10 else MomensTheme.colors.white

    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 10.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeopleIcon()

        Text(
            text = text,
            modifier = Modifier.weight(1f),
            color = MomensTheme.colors.black,
            style = MomensTheme.typography.bodyB14,
        )

        if (isSelected) {
            MomensButton(
                text = "삭제",
                onClick = onClick,
                type = MomensButtonType.WHITE,
            )
        }
    }
}

@Composable
private fun PeopleIcon(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(34.dp)
            .background(
                color = MomensTheme.colors.white,
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_person),
            contentDescription = null,
            tint = MomensTheme.colors.primary50,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Preview(widthDp = 320)
@Composable
private fun PeopleListItemPreview() {
    MomensTheme {
        Column(verticalArrangement = Arrangement.spacedBy(17.dp)) {
            PeopleListItem(
                text = "강채원",
                onClick = {},
                isSelected = true,
            )

            PeopleListItem(
                text = "강채원",
                onClick = {},
            )
        }
    }
}
