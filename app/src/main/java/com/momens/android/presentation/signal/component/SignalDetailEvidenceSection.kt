package com.momens.android.presentation.signal.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.core.designsystem.component.accordion.MomensAccordion
import com.momens.android.core.designsystem.component.type.MomensAccordionItem
import com.momens.android.core.designsystem.component.type.MomensAccordionType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.signal.model.SignalEvidenceUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val EVIDENCE_COLLAPSE_THRESHOLD = 3

@Composable
fun SignalDetailEvidenceSection(
    evidences: ImmutableList<SignalEvidenceUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "근거",
            style = MomensTheme.typography.bodyM12,
            color = MomensTheme.colors.gray400,
        )

        Spacer(modifier = Modifier.height(8.dp))

        EvidenceAccordionList(evidences = evidences)
    }
}

@Composable
private fun EvidenceAccordionList(
    evidences: ImmutableList<SignalEvidenceUiModel>,
    modifier: Modifier = Modifier,
) {
    val initiallyExpanded = evidences.size < EVIDENCE_COLLAPSE_THRESHOLD

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        evidences.forEach { evidence ->
            MomensAccordion(
                type = evidence.source,
                time = evidence.time,
                initiallyExpanded = initiallyExpanded,
                items = persistentListOf(
                    MomensAccordionItem(
                        title = "대상",
                        value = evidence.target,
                    ),
                    MomensAccordionItem(
                        title = "변화",
                        value = evidence.change,
                    ),
                    MomensAccordionItem(
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
                    source = MomensAccordionType.FIGMA,
                    time = "00분 전",
                    target = "권한 요청 화면",
                    change = "권한 요청 단계 이탈률이 오른 것으로 보임",
                    impact = "회원가입 완료율이 떨어질 수 있음",
                ),
                SignalEvidenceUiModel(
                    id = 2L,
                    source = MomensAccordionType.FILE,
                    time = "00분 전",
                    target = "권한 요청 화면 기획서",
                    change = "권한 요청 문구가 변경됨",
                    impact = "요청 이해도에 영향을 줄 수 있음",
                ),
            ),
        )
    }
}
