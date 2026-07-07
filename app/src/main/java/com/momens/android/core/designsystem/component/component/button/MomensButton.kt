package com.momens.android.core.designsystem.component.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import androidx.compose.ui.tooling.preview.Preview
import com.momens.android.core.designsystem.component.type.MomensButtonType


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

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
        ),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
    ) {
        Text(text = text, style = typographyStyle)
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




