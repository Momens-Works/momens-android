package com.momens.android.presentation.signal.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.type.SignalTagType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.signal.model.SignalCardUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SignalList(
    signals: ImmutableList<SignalCardUiModel>,
    onSignalClick: (SignalCardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = signals,
            key = { it.id },
        ) { signal ->
            SignalCard(
                type = signal.type,
                title = signal.title,
                impact = signal.impact,
                minsuSuggestion = signal.minsuSuggestion,
                onClick = { onSignalClick(signal) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalListPreview() {
    MomensTheme {
        SignalList(
            signals = persistentListOf(
                SignalCardUiModel(
                    id = "1",
                    type = SignalTagType.RISK,
                    title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                    impact = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    minsuSuggestion = "내용이 들어갈 공간입니다",
                ),
                SignalCardUiModel(
                    id = "2",
                    type = SignalTagType.CHANGE,
                    title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                    impact = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    minsuSuggestion = "내용이 들어갈 공간입니다",
                ),
            ),
            onSignalClick = {},
        )
    }
}
