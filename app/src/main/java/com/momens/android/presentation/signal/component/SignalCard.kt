package com.momens.android.presentation.signal.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.component.tag.MomensSignalTag
import com.momens.android.core.designsystem.component.textbox.MomensTextBox
import com.momens.android.core.designsystem.effect.momensUiShadow
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.core.designsystem.type.SignalTagType

@Composable
fun SignalCard(
    type: SignalTagType,
    title: String,
    description: String,
    insightText: String,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(20.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .momensUiShadow(shape = shape)
            .background(
                color = MomensTheme.colors.white,
                shape = shape
            )
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        MomensSignalTag(type = type)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            style = MomensTheme.typography.bodyB16,
            color = MomensTheme.colors.black,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = description,
            style = MomensTheme.typography.bodyM12,
            color = MomensTheme.colors.gray500,
        )

        Spacer(modifier = Modifier.height(16.dp))

        MomensTextBox(
            text = insightText,
            iconResId = R.drawable.ic_minsu
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalCardPreview() {
    MomensTheme {
        SignalCard(
            type = SignalTagType.RISK,
            title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
            description = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
            insightText = "내용이 들어갈 공간입니다",
            modifier = Modifier.padding(16.dp),
        )
    }
}
