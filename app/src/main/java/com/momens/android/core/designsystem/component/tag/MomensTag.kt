package com.momens.android.core.designsystem.component.tag


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.type.SignalTagType
import com.momens.android.core.designsystem.theme.MomensTheme

@Composable
fun MomensSignalTag(
    type: SignalTagType,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = type.background(),
                shape = RoundedCornerShape(4.dp),
            )
            .padding(horizontal = 12.dp, vertical = 2.dp),
    ) {
        Text(
            text = type.label,
            color = MomensTheme.colors.white,
            style = MomensTheme.typography.captionB10,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MomensTagPreview() {
    MomensTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MomensSignalTag(type = SignalTagType.RISK)
            MomensSignalTag(type = SignalTagType.DECISION)
            MomensSignalTag(type = SignalTagType.CHANGE)
            MomensSignalTag(type = SignalTagType.QUESTION)
        }
    }
}
