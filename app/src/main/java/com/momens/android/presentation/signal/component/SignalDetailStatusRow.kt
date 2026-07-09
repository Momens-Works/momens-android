package com.momens.android.presentation.signal.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.tag.MomensSignalTag
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.core.designsystem.type.SignalTagType

@Composable
fun SignalDetailStatusRow(
    type: SignalTagType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MomensSignalTag(type = type)

        Spacer(modifier = Modifier.width(9.dp))

        Text(
            text = type.statusText,
            style = MomensTheme.typography.bodyM12,
            color = MomensTheme.colors.gray400,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalDetailStatusRowPreview() {
    MomensTheme {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SignalDetailStatusRow(type = SignalTagType.RISK)
            SignalDetailStatusRow(type = SignalTagType.CHANGE)
            SignalDetailStatusRow(type = SignalTagType.DECISION)
            SignalDetailStatusRow(type = SignalTagType.QUESTION)
        }
    }
}
