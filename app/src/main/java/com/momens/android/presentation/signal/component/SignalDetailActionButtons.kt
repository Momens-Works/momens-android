package com.momens.android.presentation.signal.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.button.MomensCtaButton
import com.momens.android.core.designsystem.component.type.MomensCtaType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun SignalDetailActionButtons(
    onDeleteClick: () -> Unit,
    onRegisterTaskClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        MomensCtaButton(
            onClick = onDeleteClick,
            type = MomensCtaType.CANCEL,
            modifier = Modifier.weight(4f),
        ) {
            Text(
                text = "삭제",
                style = MomensTheme.typography.bodyB16,
                color = MomensTheme.colors.white,
            )
        }

        MomensCtaButton(
            onClick = onRegisterTaskClick,
            type = MomensCtaType.DEFAULT,
            modifier = Modifier.weight(9f),
        ) {
            Text(
                text = "태스크 등록",
                style = MomensTheme.typography.bodyB16,
                color = MomensTheme.colors.white,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalDetailActionButtonsPreview() {
    MomensTheme {
        SignalDetailActionButtons(
            onDeleteClick = {},
            onRegisterTaskClick = {},
        )
    }
}
