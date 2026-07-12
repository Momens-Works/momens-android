package com.momens.android.presentation.task.edit.component
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.type.MomensStatusEditType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun TaskEditStatusEdit(
    type: MomensStatusEditType,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val backgroundColor = if (isSelected) MomensTheme.colors.primary10 else MomensTheme.colors.white
    val contentColor = if (isSelected) MomensTheme.colors.primary100 else MomensTheme.colors.gray500

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = type.iconRes),
            tint = contentColor,
            contentDescription = null,
        )

        Text(
            text = type.label,
            color = contentColor,
            style = MomensTheme.typography.captionM11,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFE8E8E)
@Composable
private fun TaskEditStatusEditPreview() {
    MomensTheme() {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            TaskEditStatusEdit(
                type = MomensStatusEditType.BACKLOG,
                isSelected = true,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.BACKLOG,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.TODO,
                isSelected = true,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.TODO,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.IN_PROGRESS,
                isSelected = true,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.IN_PROGRESS,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.DONE,
                isSelected = true,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.DONE,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.CANCELED,
                isSelected = true,
            )

            TaskEditStatusEdit(
                type = MomensStatusEditType.CANCELED,
            )
        }
    }
}


