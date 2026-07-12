package com.momens.android.presentation.brief

import androidx.compose.runtime.Immutable
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilter
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterKey
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class BriefUiState(
    val project: BriefProjectUiModel,
    val signalSummary: BriefSignalSummaryUiModel,
    val priorities: ImmutableList<BriefPriorityUiModel>,
)

@Immutable
data class BriefProjectUiModel(
    val id: String,
    val name: String,
    val targetDate: String,
    val progress: Float,
    val summary: String,
)

@Immutable
data class BriefSignalSummaryUiModel(
    val summary: String?,
    val filters: ImmutableList<BriefSignalSummaryFilter>,
    val selectedFilterKey: String,
    val items: ImmutableList<BriefSignalItemUiModel>,
    val nextCursor: String?,
    val isExpanded: Boolean,
    val isLoadingMore: Boolean = false,
) {
    val totalCount: Int
        get() = filters.firstOrNull { it.key == BriefSignalSummaryFilterKey.ALL }?.count ?: 0

    val hasMore: Boolean
        get() = nextCursor != null
}

@Immutable
data class BriefSignalItemUiModel(
    val id: String,
    val typeKey: String,
    val title: String,
)

@Immutable
data class BriefPriorityUiModel(
    val rank: Int,
    val title: String,
    val taskId: String,
)

internal val SampleBriefUiState = BriefUiState(
    project = BriefProjectUiModel(
        id = "30d9e9fe-f43b-4097-a88e-dc19f0a5b025",
        name = "Q2 Activation Readiness",
        targetDate = "6월 30일",
        progress = 0.64f,
        summary = "목표일까지 Q2 Activation Readiness 범위의 " +
            "회원 가입 MVP를 안정적으로 릴리즈한다.",
    ),
    signalSummary = BriefSignalSummaryUiModel(
        summary = "Android 권한 요청 이슈가 발견되었으며, 소셜 로그인은 MVP 범위에서 제외되었습니다. " +
            "이메일 회원가입과 온보딩 이탈 개선이 우선적으로 필요합니다.",
        filters = persistentListOf(
            BriefSignalSummaryFilter(
                key = BriefSignalSummaryFilterKey.ALL,
                label = "All",
                count = 6,
            ),
            BriefSignalSummaryFilter(
                key = BriefSignalSummaryFilterKey.CHANGE,
                label = "VOC",
                count = 1,
            ),
            BriefSignalSummaryFilter(
                key = BriefSignalSummaryFilterKey.RISK,
                label = "Risk",
                count = 1,
            ),
            BriefSignalSummaryFilter(
                key = BriefSignalSummaryFilterKey.DECISION,
                label = "Decision",
                count = 2,
            ),
            BriefSignalSummaryFilter(
                key = BriefSignalSummaryFilterKey.QUESTION,
                label = "Question",
                count = 2,
            ),
        ),
        selectedFilterKey = BriefSignalSummaryFilterKey.ALL,
        items = persistentListOf(
            BriefSignalItemUiModel(
                id = "5c1a2b34-56d7-4e89-9f01-234a5b6c7d8e",
                typeKey = BriefSignalSummaryFilterKey.CHANGE,
                title = "권한 요청 반복 문의",
            ),
            BriefSignalItemUiModel(
                id = "6f3d8a61-4de7-4c01-9d2b-16fdf182e9a1",
                typeKey = BriefSignalSummaryFilterKey.DECISION,
                title = "소셜 로그인은 MVP 범위에서 제외",
            ),
            BriefSignalItemUiModel(
                id = "3b9e0d12-78f4-4a56-8c01-9d2e3f4a5b6c",
                typeKey = BriefSignalSummaryFilterKey.RISK,
                title = "Android 13+ 권한 요청 플로우 이탈 가능성",
            ),
        ),
        nextCursor = "MjAyNi0wNy0wM1QwMDowMDowMFp8M2I5ZTBkMTItNzhmNC00YTU2LThjMDEtOWQyZTNmNGE1YjZj",
        isExpanded = false,
    ),
    priorities = persistentListOf(
        BriefPriorityUiModel(
            rank = 1,
            title = "이메일 회원가입 완료율 개선",
            taskId = "27afd507-9c7f-4f0d-a2be-fcdab2477b19",
        ),
        BriefPriorityUiModel(
            rank = 2,
            title = "Android 13+ 권한 요청 플로우 정비",
            taskId = "4c8e1f23-a567-4b89-9c01-2d3e4f5a6b7c",
        ),
        BriefPriorityUiModel(
            rank = 3,
            title = "온보딩 이탈 구간 계측 추가",
            taskId = "9d0a2b34-c678-4d90-8e12-3f4a5b6c7d8e",
        ),
        BriefPriorityUiModel(
            rank = 4,
            title = "소셜 로그인 제외 범위 QA",
            taskId = "1e2f3a45-b789-4c01-9d23-4a5b6c7d8e9f",
        ),
    ),
)
