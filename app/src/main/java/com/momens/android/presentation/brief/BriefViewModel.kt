package com.momens.android.presentation.brief

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.common.extension.updateSuccess
import com.momens.android.core.common.state.UiState
import com.momens.android.data.brief.repository.BriefRepository
import com.momens.android.core.local.ProjectManager
import androidx.lifecycle.viewModelScope
import com.momens.android.core.common.extension.updateSuccess
import com.momens.android.core.common.state.UiState
import com.momens.android.data.brief.repository.BriefRepository
import com.momens.android.presentation.brief.model.BriefSignalSummaryFilterType
import com.momens.android.presentation.brief.model.toApiFilter
import com.momens.android.presentation.brief.model.toUiModel
import com.momens.android.presentation.brief.state.BriefUiState
import com.momens.android.presentation.brief.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class BriefViewModel @Inject constructor(
    projectManager: ProjectManager,
) : ViewModel() {
    private val _uiState = MutableStateFlow<BriefUiState>(SampleBriefUiState)
class BriefViewModel @Inject constructor(
    private val briefRepository: BriefRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<BriefUiState>>(UiState.Loading)

    val uiState: StateFlow<UiState<BriefUiState>> = _uiState.asStateFlow()

    init {
        Timber.tag(BRIEF_LOG_TAG).i("BriefViewModel init")
        loadBrief()
    }

    fun loadBrief(projectId: String = DEFAULT_PROJECT_ID) {
        _uiState.value = UiState.Loading
        Timber.tag(BRIEF_LOG_TAG).i("getBrief 요청 시작: projectId=$projectId")

        viewModelScope.launch {
            briefRepository.getBrief(projectId = projectId)
                .onSuccess { response ->
                    Timber.tag(BRIEF_LOG_TAG).i(
                        "getBrief 응답 수신: projectId=$projectId, response=$response",
                    )
                    _uiState.value = UiState.Success(response.toUiState())
                }
                .onFailure { throwable ->
                    Timber.tag(BRIEF_LOG_TAG).e(
                        throwable,
                        "getBrief 요청 실패: projectId=$projectId",
                    )
                    _uiState.value = UiState.Failure
                }
        }
    }

    val projectContext = projectManager.observeProjectContext().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = projectManager.currentProjectContext,
    )

    fun selectSignalFilter(filterType: BriefSignalSummaryFilterType) {
        val currentState = (_uiState.value as? UiState.Success)?.data ?: return
        val filter = filterType.toApiFilter()

        Timber.tag(BRIEF_LOG_TAG).i(
            "getSignalSummary 요청 시작: projectId=${currentState.project.id}, " +
                "filter=$filter, cursor=null",
        )

        viewModelScope.launch {
            briefRepository.getSignalSummary(
                projectId = currentState.project.id,
                filter = filter,
                cursor = null,
                limit = SIGNAL_SUMMARY_PAGE_SIZE,
            ).onSuccess { response ->
                Timber.tag(BRIEF_LOG_TAG).i(
                    "getSignalSummary 응답 수신: projectId=${currentState.project.id}, " +
                        "filter=$filter, cursor=null, response=$response",
                )
                _uiState.updateSuccess { state ->
                    state.copy(
                        signalSummary = state.signalSummary.copy(
                            selectedFilterType = filterType,
                            items = response.items.map { item -> item.toUiModel() }.toImmutableList(),
                            nextCursor = response.nextCursor,
                            isExpanded = false,
                        ),
                    )
                }
            }.onFailure { throwable ->
                Timber.tag(BRIEF_LOG_TAG).e(
                    throwable,
                    "getSignalSummary 요청 실패: projectId=${currentState.project.id}, " +
                        "filter=$filter, cursor=null",
                )
            }
        }
    }

    fun loadMoreSignalSummary() {
        val currentState = (_uiState.value as? UiState.Success)?.data ?: return
        val signalSummary = currentState.signalSummary
        val nextCursor = signalSummary.nextCursor

        if (nextCursor == null) {
            updateSignalSummaryExpanded(isExpanded = true)
            return
        }

        val filter = signalSummary.selectedFilterType.toApiFilter()

        Timber.tag(BRIEF_LOG_TAG).i(
            "getSignalSummary 더보기 요청 시작: projectId=${currentState.project.id}, " +
                "filter=$filter, cursor=$nextCursor",
        )

        viewModelScope.launch {
            briefRepository.getSignalSummary(
                projectId = currentState.project.id,
                filter = filter,
                cursor = nextCursor,
                limit = SIGNAL_SUMMARY_PAGE_SIZE,
            ).onSuccess { response ->
                Timber.tag(BRIEF_LOG_TAG).i(
                    "getSignalSummary 더보기 응답 수신: projectId=${currentState.project.id}, " +
                        "filter=$filter, cursor=$nextCursor, response=$response",
                )
                _uiState.updateSuccess { state ->
                    state.copy(
                        signalSummary = state.signalSummary.copy(
                            items = (
                                state.signalSummary.items +
                                    response.items.map { item -> item.toUiModel() }
                                ).toImmutableList(),
                            nextCursor = response.nextCursor,
                            isExpanded = true,
                        ),
                    )
                }
            }.onFailure { throwable ->
                Timber.tag(BRIEF_LOG_TAG).e(
                    throwable,
                    "getSignalSummary 더보기 요청 실패: projectId=${currentState.project.id}, " +
                        "filter=$filter, cursor=$nextCursor",
                )
            }
        }
    }

    fun foldSignalSummary() {
        updateSignalSummaryExpanded(isExpanded = false)
    }

    private fun updateSignalSummaryExpanded(isExpanded: Boolean) {
        _uiState.updateSuccess { currentState ->
            currentState.copy(
                signalSummary = currentState.signalSummary.copy(isExpanded = isExpanded),
            )
        }
    }

    companion object {
        private const val BRIEF_LOG_TAG = "BriefApi"
        private const val DEFAULT_PROJECT_ID = "a0000000-0000-4000-8000-000000000003"
        private const val SIGNAL_SUMMARY_PAGE_SIZE = 20
    }
}
