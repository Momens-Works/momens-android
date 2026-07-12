package com.momens.android.presentation.signal.component.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.momens.android.R
import com.momens.android.core.designsystem.component.bottomsheet.MomensBottomSheet
import com.momens.android.core.designsystem.component.textbox.MomensTextBox
import com.momens.android.core.designsystem.component.type.SignalTagType
import com.momens.android.core.designsystem.theme.MomensTheme
import com.momens.android.presentation.signal.model.SignalAccordionType
import com.momens.android.presentation.signal.model.SignalCardUiModel
import com.momens.android.presentation.signal.model.SignalEvidenceUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignalDetailBottomSheet(
    signal: SignalCardUiModel,
    evidences: ImmutableList<SignalEvidenceUiModel>,
    onDismiss: () -> Unit,
    onDeleteClick: () -> Unit,
    onRegisterTaskClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MomensBottomSheet(
        onDismiss = onDismiss,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
        ) {
            SignalDetailStatusRow(type = signal.type)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = signal.title,
                style = MomensTheme.typography.bodyB16,
                color = MomensTheme.colors.gray900,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = signal.impact,
                style = MomensTheme.typography.bodyM14,
                color = MomensTheme.colors.gray500,
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignalDetailEvidenceSection(
                evidences = evidences,
                modifier = Modifier.weight(1f, fill = false),
            )

            Spacer(modifier = Modifier.height(24.dp))

            MomensTextBox(
                text = signal.minsuSuggestion,
                iconResId = R.drawable.ic_minsu,
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignalDetailActionButtons(
                onDeleteClick = onDeleteClick,
                onRegisterTaskClick = onRegisterTaskClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalDetailBottomSheetPreview() {
    MomensTheme {
        SignalDetailBottomSheet(
            signal = SignalCardUiModel(
                id = "1",
                type = SignalTagType.RISK,
                title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                impact = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                minsuSuggestion = "내용이 들어갈 공간입니다",
            ),
            evidences = persistentListOf(
                SignalEvidenceUiModel(
                    sourceRefId = "1",
                    source = SignalAccordionType.FIGMA,
                    time = "00분 전",
                    target = "권한 요청 화면",
                    change = "권한 요청 단계 이탈률이 오른 것으로 보임",
                    impact = "회원가입 완료율이 떨어질 수 있음",
                ),
                SignalEvidenceUiModel(
                    sourceRefId = "2",
                    source = SignalAccordionType.FILE,
                    time = "00분 전",
                    target = "권한 요청 화면 기획서",
                    change = "권한 요청 문구가 변경됨",
                    impact = "요청 이해도에 영향을 줄 수 있음",
                ),
            ),
            onDismiss = {},
            onDeleteClick = {},
            onRegisterTaskClick = {},
        )
    }
}

@Preview(showBackground = true, name = "근거 3개 이상 - 기본 접힘")
@Composable
private fun SignalDetailBottomSheetCollapsedPreview() {
    MomensTheme {
        SignalDetailBottomSheet(
            signal = SignalCardUiModel(
                id = "1",
                type = SignalTagType.RISK,
                title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                impact = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                minsuSuggestion = "내용이 들어갈 공간입니다",
            ),
            evidences = persistentListOf(
                SignalEvidenceUiModel(
                    sourceRefId = "1",
                    source = SignalAccordionType.FIGMA,
                    time = "00분 전",
                    target = "권한 요청 화면",
                    change = "권한 요청 단계 이탈률이 오른 것으로 보임",
                    impact = "회원가입 완료율이 떨어질 수 있음",
                ),
                SignalEvidenceUiModel(
                    sourceRefId = "2",
                    source = SignalAccordionType.FILE,
                    time = "00분 전",
                    target = "권한 요청 화면 기획서",
                    change = "권한 요청 문구가 변경됨",
                    impact = "요청 이해도에 영향을 줄 수 있음",
                ),
                SignalEvidenceUiModel(
                    sourceRefId = "3",
                    source = SignalAccordionType.GITHUB,
                    time = "00분 전",
                    target = "권한 요청 PR",
                    change = "런타임 권한 분기 로직 추가됨",
                    impact = "테스트 커버리지 확인 필요",
                ),
            ),
            onDismiss = {},
            onDeleteClick = {},
            onRegisterTaskClick = {},
        )
    }
}
