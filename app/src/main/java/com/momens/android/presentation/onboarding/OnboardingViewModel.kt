package com.momens.android.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.local.onboarding.OnboardingManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingManager: OnboardingManager,
) : ViewModel() {

    private val _sideEffect = MutableSharedFlow<OnboardingSideEffect>()
    val sideEffect: SharedFlow<OnboardingSideEffect> = _sideEffect.asSharedFlow()

    fun completeOnboarding() {
        viewModelScope.launch {
            onboardingManager.saveHasSeenOnboarding(hasSeen = true)
            _sideEffect.emit(OnboardingSideEffect.NavigateToSignal)
        }
    }
}
