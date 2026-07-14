package com.momens.android.core.designsystem.component.tag

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensTaskTag(
    type: MomensStatusEditType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = MomensTheme.colors.primary10,
                shape = RoundedCornerShape(20.dp),
            )
            .border(
                border = BorderStroke(width = 1.dp, color = MomensTheme.colors.primary50),
                shape = RoundedCornerShape(20.dp),
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(type.iconRes),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MomensTheme.colors.primary100,
        )

        Text(
            text = type.label,
            color = MomensTheme.colors.primary100,
            style = MomensTheme.typography.captionB11,
        )
    }
}

@Preview
@Composable
private fun MomensTaskTagPreview() {
    MomensTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            MomensTaskTag(
                type = MomensStatusEditType.TODO,
            )

            MomensTaskTag(
                type = MomensStatusEditType.IN_PROGRESS,
            )

            MomensTaskTag(
                type = MomensStatusEditType.DONE,
            )

            MomensTaskTag(
                type = MomensStatusEditType.CANCELED,
            )

            MomensTaskTag(
                type = MomensStatusEditType.BACKLOG,
            )
        }
    }
}
