package com.momens.android.presentation.brief.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.progressbar.MomensProgressBar
import com.momens.android.core.designsystem.component.textbox.MomensTextBox
import com.momens.android.core.designsystem.theme.MomensTheme
import kotlin.math.roundToInt

@Composable
fun BriefSummaryCard(
    title: String,
    targetDate: String,
    progress: Float,
    summary: String,
    modifier: Modifier = Modifier,
) {
    val progressPercent = (progress.coerceIn(0f, 1f) * 100).roundToInt()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MomensTheme.colors.primary100,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = title,
            color = MomensTheme.colors.white,
            style = MomensTheme.typography.bodyB16,
            modifier = Modifier.fillMaxWidth(),
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                BriefSummaryLabel(
                    label = "목표",
                    value = targetDate,
                )

                BriefSummaryLabel(
                    label = "진행률",
                    value = "$progressPercent%",
                    labelColor = MomensTheme.colors.white,
                    valueColor = MomensTheme.colors.white,
                )
            }

            MomensProgressBar(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
            )
        }
// 디썜들에게 물어보기 !
        MomensTextBox(
            text = summary,
        )
    }
}

@Composable
private fun BriefSummaryLabel(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    labelColor: Color = MomensTheme.colors.gray100,
    valueColor: Color = MomensTheme.colors.primary10,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            color = labelColor,
            style = MomensTheme.typography.captionM10,
        )

        Text(
            text = value,
            color = valueColor,
            style = MomensTheme.typography.captionB10,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BriefSummaryCardPreview() {
    MomensTheme {
        Box(
            modifier = Modifier.padding(30.dp),
        ) {
            BriefSummaryCard(
                title = "Q2 Activation Readiness",
                targetDate = "6월 30일",
                progress = 0.64f,
                summary = "목표일까지 Q2 Activation Readiness 범위의\n회원 가입 MVP를 안정적으로 릴리즈한다.",
            )
        }
    }
}
