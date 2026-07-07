package com.momens.android.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.momens.android.core.designsystem.component.type.MomensButtonType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: MomensButtonType = MomensButtonType.GRAY,
) {
    val backgroundColor = type.background()
    val contentColor = type.textColor()
    val typographyStyle = type.textStyle()

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .noRippleClickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            color = contentColor,
            style = typographyStyle,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFE8E8E)
@Composable
private fun MomensButtonPreview() {
    MomensTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            MomensButton(text = "버튼", type = MomensButtonType.GRAY, onClick = {})
            MomensButton(text = "버튼", type = MomensButtonType.PRIMARY, onClick = {})
            MomensButton(text = "버튼", type = MomensButtonType.BLACK, onClick = {})
            MomensButton(text = "버튼", type = MomensButtonType.WHITE, onClick = {})
        }
    }
}
