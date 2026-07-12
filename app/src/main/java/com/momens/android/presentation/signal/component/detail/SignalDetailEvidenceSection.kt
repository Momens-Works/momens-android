package com.momens.android.presentation.signal.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.signal.component.detail.accodion.SignalAccordion
import com.momens.android.presentation.signal.model.SignalAccordionItem
import com.momens.android.presentation.signal.model.SignalAccordionType
import com.momens.android.presentation.signal.model.SignalEvidenceUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val EVIDENCE_COLLAPSE_THRESHOLD = 3

@Composable
fun SignalDetailEvidenceSection(
    evidences: ImmutableList<SignalEvidenceUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = "근거",
            style = MomensTheme.typography.bodyM12,
            color = MomensTheme.colors.gray400,
        )

        Spacer(modifier = Modifier.height(8.dp))

        EvidenceAccordionList(
            evidences = evidences,
            modifier = Modifier.weight(1f, fill = false),
        )
    }
}

@Composable
private fun EvidenceAccordionList(
    evidences: ImmutableList<SignalEvidenceUiModel>,
    modifier: Modifier = Modifier,
) {
    val initiallyExpanded = evidences.size < EVIDENCE_COLLAPSE_THRESHOLD

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = evidences,
            key = { it.id },
        ) { evidence ->
            SignalAccordion(
                type = evidence.source,
                time = evidence.time,
                initiallyExpanded = initiallyExpanded,
                items = persistentListOf(
                    SignalAccordionItem(
                        title = "대상",
                        value = evidence.target,
                    ),
                    SignalAccordionItem(
                        title = "변화",
                        value = evidence.change,
                    ),
                    SignalAccordionItem(
                        title = "영향",
                        value = evidence.impact,
                    ),
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalDetailEvidenceSectionPreview() {
    MomensTheme {
        SignalDetailEvidenceSection(
            evidences = persistentListOf(
                SignalEvidenceUiModel(
                    id = 1L,
                    source = SignalAccordionType.FIGMA,
                    time = "00분 전",
                    target = "권한 요청 화면",
                    change = "권한 요청 단계 이탈률이 오른 것으로 보임",
                    impact = "회원가입 완료율이 떨어질 수 있음",
                ),
                SignalEvidenceUiModel(
                    id = 2L,
                    source = SignalAccordionType.FILE,
                    time = "00분 전",
                    target = "권한 요청 화면 기획서",
                    change = "권한 요청 문구가 변경됨",
                    impact = "요청 이해도에 영향을 줄 수 있음",
                ),
            ),
        )
    }
}
