package com.momens.android.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.type.MomensCtaType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensCtaButton(
    modifier: Modifier = Modifier,
    type: MomensCtaType = MomensCtaType.DEFAULT,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val backgroundColor = when (type) {
        MomensCtaType.DEFAULT -> MomensTheme.colors.primary100
        MomensCtaType.DISABLED -> MomensTheme.colors.gray300
        MomensCtaType.LOGIN -> MomensTheme.colors.white
    }

    val isEnabled = type != MomensCtaType.DISABLED

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .noRippleClickable(
                enabled = isEnabled,
                onClick = onClick,
            )
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview
@Composable
private fun MomensCtaButtonPreview() {
    MomensTheme {

    }
}
