package com.momens.android.presentation.signal.state

import androidx.compose.runtime.Immutable
import com.momens.android.core.designsystem.component.type.SignalTagType
import com.momens.android.presentation.signal.model.SignalAccordionType
import com.momens.android.presentation.signal.model.SignalCardUiModel
import com.momens.android.presentation.signal.model.SignalEvidenceUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

@Immutable
data class SignalState(
    val signals: ImmutableList<SignalCardUiModel> = persistentListOf(),
    val evidencesBySignalId: ImmutableMap<Long, ImmutableList<SignalEvidenceUiModel>> = persistentMapOf(),
) {
    companion object {
        val Fake = SignalState(
            signals = persistentListOf(
                SignalCardUiModel(
                    id = 1L,
                    type = SignalTagType.RISK,
                    title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                    description = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    insightText = "내용이 들어갈 공간입니다",
                ),
                SignalCardUiModel(
                    id = 2L,
                    type = SignalTagType.CHANGE,
                    title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                    description = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    insightText = "내용이 들어갈 공간입니다",
                ),
                SignalCardUiModel(
                    id = 3L,
                    type = SignalTagType.CHANGE,
                    title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                    description = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    insightText = "내용이 들어갈 공간입니다",
                ),
                SignalCardUiModel(
                    id = 4L,
                    type = SignalTagType.RISK,
                    title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                    description = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    insightText = "내용이 들어갈 공간입니다",
                ),
                SignalCardUiModel(
                    id = 5L,
                    type = SignalTagType.CHANGE,
                    title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                    description = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    insightText = "내용이 들어갈 공간입니다",
                ),
                SignalCardUiModel(
                    id = 6L,
                    type = SignalTagType.CHANGE,
                    title = "Android 13+ 권한 요청 플로우에서 이탈 가능성 발견",
                    description = "MVP 완료율과 온보딩 품질에 영향을 줄 수 있습니다.",
                    insightText = "내용이 들어갈 공간입니다",
                ),
            ),
            evidencesBySignalId = persistentMapOf(
                // 근거 3개 이상 -> 아코디언 기본 접힘 상태 확인용 (화면명세서 8-2)
                1L to persistentListOf(
                    SignalEvidenceUiModel(
                        id = 11L,
                        source = SignalAccordionType.FIGMA,
                        time = "00분 전",
                        target = "권한 요청 화면",
                        change = "권한 요청 단계 이탈률이 오른 것으로 보임",
                        impact = "회원가입 완료율이 떨어질 수 있음",
                    ),
                    SignalEvidenceUiModel(
                        id = 12L,
                        source = SignalAccordionType.FILE,
                        time = "00분 전",
                        target = "권한 요청 화면 기획서",
                        change = "권한 요청 문구가 변경됨",
                        impact = "요청 이해도에 영향을 줄 수 있음",
                    ),
                    SignalEvidenceUiModel(
                        id = 13L,
                        source = SignalAccordionType.GITHUB,
                        time = "00분 전",
                        target = "권한 요청 PR",
                        change = "런타임 권한 분기 로직 추가됨",
                        impact = "테스트 커버리지 확인 필요",
                    ),
//                    SignalEvidenceUiModel(
//                        id = 14L,
//                        source = SignalAccordionType.GITHUB,
//                        time = "00분 전",
//                        target = "권한 요청 PR",
//                        change = "런타임 권한 분기 로직 추가됨",
//                        impact = "테스트 커버리지 확인 필요",
//                    ),
                ),
                // 근거 3개 미만 -> 아코디언 기본 펼침 상태 확인용 (화면명세서 8-1)
                2L to persistentListOf(
                    SignalEvidenceUiModel(
                        id = 21L,
                        source = SignalAccordionType.SLACK,
                        time = "12분 전",
                        target = "#release 채널",
                        change = "권한 정책 변경 공지",
                        impact = "QA 일정 조정 필요",
                    ),
                    SignalEvidenceUiModel(
                        id = 22L,
                        source = SignalAccordionType.GITHUB,
                        time = "00분 전",
                        target = "권한 요청 PR",
                        change = "런타임 권한 분기 로직 추가됨",
                        impact = "테스트 커버리지 확인 필요",
                    ),
                ),
            ),
        )
    }
}

sealed interface SignalSideEffect {
    data class ShowActionSnackbar(
        val message: String,
        val description: String,
        val onAction: () -> Unit,
    ) : SignalSideEffect

    data class ShowSnackbar(val message: String) : SignalSideEffect

    data object NavigateToTask : SignalSideEffect
}
