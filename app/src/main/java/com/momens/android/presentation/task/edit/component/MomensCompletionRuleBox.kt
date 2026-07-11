package com.momens.android.presentation.task.edit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.common.extension.noRippleToggleable
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensCompletionRuleBox(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val iconRes = if (isChecked) R.drawable.ic_checkbox_fill else R.drawable.ic_checkbox_empty
    val iconTint = if (isChecked && enabled) MomensTheme.colors.primary50 else MomensTheme.colors.gray300
    val labelColor = if (enabled) MomensTheme.colors.gray800 else MomensTheme.colors.gray300

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.gray100,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(18.dp)
                .background(
                    color = MomensTheme.colors.gray200,
                    shape = RoundedCornerShape(6.dp),
                ),
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .noRippleToggleable(
                    value = isChecked,
                    onValueChange = onCheckedChange,
                    enabled = enabled,
                    role = Role.Checkbox,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconRes),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp),
            )

            Text(
                text = label,
                style = MomensTheme.typography.bodyM12,
                color = labelColor,
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
            contentDescription = null,
            tint = MomensTheme.colors.gray800,
            modifier = Modifier
                .size(14.dp)
                .noRippleClickable(
                    enabled = enabled,
                    onClick = onClearClick,
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensCompletionRuleBoxPreview() {
    MomensTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // entered + checked
            MomensCompletionRuleBox(
                label = "어쩌구어쩌구 반영",
                isChecked = true,
                onCheckedChange = {},
                onClearClick = {},
            )

            // entered + unchecked (텍스트는 여전히 진한 색)
            MomensCompletionRuleBox(
                label = "어쩌구어쩌구 반영",
                isChecked = false,
                onCheckedChange = {},
                onClearClick = {},
            )

            // not entered (이때만 텍스트/아이콘이 흐려짐)
            MomensCompletionRuleBox(
                label = "어쩌구어쩌구 반영",
                isChecked = false,
                onCheckedChange = {},
                onClearClick = {},
                enabled = false,
            )
        }
    }
}
