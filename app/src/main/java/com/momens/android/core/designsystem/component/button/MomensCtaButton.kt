package com.momens.android.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.common.extension.noRippleClickable
import com.momens.android.core.designsystem.component.type.MomensCtaType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensCtaButton(
    modifier: Modifier = Modifier,
    type: MomensCtaType = MomensCtaType.DEFAULT,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val backgroundColor = if (!enabled) {
        MomensTheme.colors.gray300
    } else {
        when (type) {
            MomensCtaType.DEFAULT -> MomensTheme.colors.primary100
            MomensCtaType.LOGIN -> MomensTheme.colors.white
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .noRippleClickable(
                enabled = enabled,
                onClick = onClick,
            )
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview(widthDp = 320)
@Composable
private fun MomensCtaButtonPreview() {
    MomensTheme {

    }
}
