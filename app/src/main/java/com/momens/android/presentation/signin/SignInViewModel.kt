package com.momens.android.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.momens.android.core.common.state.UiState
import com.momens.android.data.signin.repository.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository,
) : ViewModel() {

    private val _signInState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val signInState: StateFlow<UiState<Unit>> = _signInState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect: SharedFlow<SignInSideEffect> = _sideEffect.asSharedFlow()

    fun onGoogleSignInClick() {
        if (_signInState.value == UiState.Loading) return

        _signInState.value = UiState.Loading

        viewModelScope.launch {
            _sideEffect.emit(SignInSideEffect.LaunchGoogleSignIn)
        }
    }

    fun onGoogleSignInResult(result: Result<String>) {
        result.fold(
            onSuccess = ::signInWithGoogle,
            onFailure = {
                _signInState.value = UiState.Failure
            },
        )
    }

    private fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            signInRepository.signInWithGoogle(idToken)
                .onSuccess {
                    _signInState.value = UiState.Success(Unit)
                    _sideEffect.emit(SignInSideEffect.NavigateToSignal)
                }
                .onFailure {
                    _signInState.value = UiState.Failure
                }
        }
    }
}
