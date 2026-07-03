package com.momens.android.core.designsystem.component.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleToggleable
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensCheckBox(
    checked: Boolean,
    label: String,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val iconRes = if (checked) R.drawable.ic_checkbox_fill else R.drawable.ic_checkbox_empty
    val iconTint = if (checked) MomensTheme.colors.primary100 else MomensTheme.colors.gray300

    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleToggleable(
                value = checked,
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
            color = MomensTheme.colors.gray800,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensCheckBoxPreview() {
    MomensTheme {
        var firstChecked by remember { mutableStateOf(false) }
        var secondChecked by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MomensCheckBox(
                checked = firstChecked,
                label = "어쩌구어쩌구 반영",
                onCheckedChange = { firstChecked = it },
            )

            MomensCheckBox(
                checked = secondChecked,
                label = "어쩌구어쩌구 반영",
                onCheckedChange = { secondChecked = it },
            )
        }
    }
}
