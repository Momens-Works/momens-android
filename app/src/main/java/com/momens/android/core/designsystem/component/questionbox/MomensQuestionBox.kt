package com.momens.android.core.designsystem.component.questionbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensQuestionBox(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_question),
            contentDescription = null,
            tint = MomensTheme.colors.primary100,
            modifier = Modifier.size(24.dp),
        )

        Text(
            text = text,
            style = MomensTheme.typography.bodyM12,
            color = MomensTheme.colors.gray800,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensQuestionBoxPreview() {
    MomensTheme {
        MomensQuestionBox(
            modifier = Modifier.padding(0.dp),
            text = "약한 비밀번호 기준을 사용자에게 얼마나 구체적으로 알려줘야할 지 결정이 필요해보임",
        )
    }
}
