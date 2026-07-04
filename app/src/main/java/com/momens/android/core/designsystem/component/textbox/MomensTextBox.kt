package com.momens.android.core.designsystem.component.textbox

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensTextBox(
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int? = null,
    iconColor: Color = MomensTheme.colors.primary100,
    textColor: Color = MomensTheme.colors.primary100,
    isArrowVisible: Boolean = false,
    onArrowClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.primary10,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            iconResId?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    tint = iconColor,
                )
            }

            Text(
                text = text,
                style = MomensTheme.typography.captionM11,
                color = textColor,
                modifier = Modifier.weight(1f),
            )

            if (isArrowVisible) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = null,
                    tint = MomensTheme.colors.gray500,
                    modifier = Modifier.clickable(onClick = onArrowClick),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensTextBoxPreview() {
    MomensTheme {
        Column(
            modifier = Modifier
                .width(320.dp)
                .height(720.dp)
                .padding(top = 50.dp)
        ) {
            MomensTextBox(
                text = "텍스트 테스트입니다"
            )

            Spacer(modifier = Modifier.height(10.dp))

            MomensTextBox(
                text = "텍스트 테스트입니다",
                iconResId = R.drawable.ic_minsu
            )

            Spacer(modifier = Modifier.height(10.dp))

            MomensTextBox(
                text = "텍스트 테스트입니다",
                iconResId = R.drawable.ic_minsu,
                iconColor = MomensTheme.colors.gray500,
                textColor = MomensTheme.colors.gray500,
                isArrowVisible = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            MomensTextBox(
                text = "텍스트테스트입니다텍스트 테스트입니다텍스트 테스트입니다텍스트 테스트입니다텍스트 테스트입니다텍스트 테스트입니다텍스트 테스트입니다텍스트 테스트입니다텍스트 테스트입니다"
            )
        }
    }
}
